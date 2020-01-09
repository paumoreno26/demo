package co.com.utilitarios.email;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Nonnull;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import co.com.utilitarios.email.modelo.EmisorEmail;
import co.com.utilitarios.email.modelo.ObjetoEmail;
import co.com.utilitarios.exepcion.ExcepcionPeriferiaProceso;
import co.com.utilitarios.exepcion.modelo.TipoErrorProceso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Paula Moreno
 * Clase para el envio de correos electronicos
 */
public class EnviarEmail {

    private final static Logger LOGGER = LoggerFactory.getLogger(EnviarEmail.class);

	/**
	 * @author Paula Moreno
	 * @param emisor Objeto que contiene la informacion para configurar el correo.
	 * @param info Objeto que contiene la estructura para escribir y enviar el correo al destinatario.
	 * @return retorna un true para indicar que envio el Email.
	 * @throws ExcepcionPeriferiaProceso
	 */
	public static boolean sendMail(@Nonnull EmisorEmail emisor, ObjetoEmail info) throws ExcepcionPeriferiaProceso {
		
		LOGGER.debug("Se reciben los datos para la configuracion del emisor: " + emisor);
		LOGGER.debug("Se recibe la informacion para la estructura del correo: " + info);
		
		if (null == emisor.getEmail() || emisor.getEmail().isEmpty()){
            LOGGER.debug("No existe un email");
            throw TipoErrorProceso.ENVIAREMAIL_EMISOR.crearExcepcion("Correo Electronico");
        }
		if (null == emisor.getPassword() || emisor.getPassword().isEmpty()){
            LOGGER.debug("No existe un password");
            throw TipoErrorProceso.ENVIAREMAIL_EMISOR.crearExcepcion("Password");
        }
		if (null == emisor.getPuerto() || emisor.getPuerto().isEmpty()){
            LOGGER.debug("No existe un puerto");
            throw TipoErrorProceso.ENVIAREMAIL_EMISOR.crearExcepcion("Puerto");
        }
		if (null == emisor.getServidor() || emisor.getServidor().isEmpty()){
            LOGGER.debug("No existe un servidor");
            throw TipoErrorProceso.ENVIAREMAIL_EMISOR.crearExcepcion("Servidor");
        }
		if (null == emisor.getTls()){
            LOGGER.debug("No existe un tls");
            throw TipoErrorProceso.ENVIAREMAIL_EMISOR.crearExcepcion("TLS");
        }

		if (null == info.getDestinatario() || info.getDestinatario().isEmpty()){
            LOGGER.debug("No existe un email");
            throw TipoErrorProceso.ENVIAREMAIL_RECEPTOR.crearExcepcion(" Correo destino");
        }
		if (null == info.getAsunto() || info.getAsunto().isEmpty()){
            LOGGER.debug("No existe un asunto");
            throw TipoErrorProceso.ENVIAREMAIL_RECEPTOR.crearExcepcion("Asunto");
        }


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", emisor.getTls());
        props.put("mail.smtp.host", emisor.getServidor());
        props.put("mail.smtp.port", emisor.getPuerto());
        LOGGER.debug("Se genera el objeto para la configuracion del emisor: " + props);
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emisor.getEmail(), emisor.getPassword());
            }
        });
        
        LOGGER.debug("Se inicia la creacion del obejto con la informacion" + info);
        MimeMessage msg = new MimeMessage(session);

        try {
            msg.setFrom(emisor.getEmail());
        } catch (MessagingException e) {
        	LOGGER.debug("No existe un email");
            throw TipoErrorProceso.ENVIAREMAIL_COMPONER.crearExcepcion(e, "correo del emisor");
        }
        try {
            msg.addRecipients(Message.RecipientType.TO, info.getDestinatario());
        } catch (MessagingException e) {
        	LOGGER.debug("No existe un destinatario");
            throw TipoErrorProceso.ENVIAREMAIL_COMPONER.crearExcepcion(e, "correo del destinatario");
        }
        try {
            msg.addRecipients(Message.RecipientType.CC, info.getCc());
            LOGGER.debug("No existe un email");
        } catch (MessagingException e) {
            throw TipoErrorProceso.ENVIAREMAIL_COMPONER.crearExcepcion(e, "correo del copia");
        }
        try {
            msg.addRecipients(Message.RecipientType.BCC, info.getCco());
        } catch (MessagingException e) {
        	LOGGER.debug("No existe un email");
            throw TipoErrorProceso.ENVIAREMAIL_COMPONER.crearExcepcion(e, "correo copia oculta");
        }
        try {
            msg.setSubject(info.getAsunto());
        } catch (MessagingException e) {
        	LOGGER.debug("No existe un Asunto");
            throw TipoErrorProceso.ENVIAREMAIL_COMPONER.crearExcepcion(e, "Asunto del correo");
        }
        
        LOGGER.debug("Se realiza la condicion para adjuntar un archivo si lo hay");
        if (info.getAdjunto() != null) {
            BodyPart adjunto = new MimeBodyPart();
            try {
                adjunto.setDataHandler(new DataHandler(new FileDataSource(info.getAdjunto())));
            } catch (MessagingException e) {
            	LOGGER.debug("No existe el archivo");
                throw TipoErrorProceso.ENVIAREMAIL_ADJUNTO.crearExcepcion(e, "Bytes del archivo");
            }
            try {
                adjunto.setFileName(info.getAdjunto());
            } catch (MessagingException e) {
            	LOGGER.debug("No existe el archivo");
                throw TipoErrorProceso.ENVIAREMAIL_ADJUNTO.crearExcepcion(e, "Nombre del adjunto");
            }
            MimeMultipart multiParte = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();

            try {
                messageBodyPart.setText(info.getCuerpo());
            } catch (MessagingException e) {
            	LOGGER.debug("No existe el cuerpo");
                throw TipoErrorProceso.ENVIAREMAIL_ADJUNTO.crearExcepcion(e, "Cuerpo del correo");
            }
            try {
                multiParte.addBodyPart(adjunto);
            } catch (MessagingException e) {
            	LOGGER.debug("No existe el archivo");
                throw TipoErrorProceso.ENVIAREMAIL_ADJUNTO.crearExcepcion(e, "Parte del mensaje como adjunto");
            }
            try {
                multiParte.addBodyPart(messageBodyPart);
            } catch (MessagingException e) {
            	LOGGER.debug("No existe el mensaje");
                throw TipoErrorProceso.ENVIAREMAIL_ADJUNTO.crearExcepcion(e, "Contenido del mensaje como adjunto");
            }
            try {
                msg.setContent(multiParte);
            } catch (MessagingException e) {
            	LOGGER.debug("No existe el objeto");
                throw TipoErrorProceso.ENVIAREMAIL_ADJUNTO.crearExcepcion(e, "Marcando el mensaje con adjuntos");
            }
        }else{
            try {
                msg.setText(info.getCuerpo());
                LOGGER.debug("No existe el cuerpo");
            } catch (MessagingException e) {
                throw TipoErrorProceso.ENVIAREMAIL_COMPONER.crearExcepcion(e, "Cuerpo del correo");
            }
        }
        LOGGER.debug("Se genera el objeto para la estructura que lleva el correo:" + msg);
        try {
        	LOGGER.debug("Enviando el correo");
            Transport.send(msg);
        } catch (MessagingException e) {
        	LOGGER.debug("No se pudo enviar el correo");
            throw TipoErrorProceso.ENVIAREMAIL_ENVIANDO.crearExcepcion(e);
        }
        LOGGER.debug("El correo fue enviado");

		return true;

	}

}




