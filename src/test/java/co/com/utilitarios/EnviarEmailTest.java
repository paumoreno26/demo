package co.com.utilitarios;

import static org.junit.Assert.*;

import org.junit.Test;

import co.com.utilitarios.email.EnviarEmail;
import co.com.utilitarios.email.modelo.EmisorEmail;
import co.com.utilitarios.email.modelo.ObjetoEmail;
import co.com.utilitarios.exepcion.ExcepcionPeriferiaProceso;

public class EnviarEmailTest {

	@Test
	public void enviar() throws ExcepcionPeriferiaProceso {
		
		EnviarEmail enviar = new EnviarEmail();
		
		String destinatario = "paula_moreno26@hotmail.com";
		String cc = "";
		String cco = "";
		String tema = "Prueba Email";
		String cuerpo = "Hola esto es una prueba de envio de un email";
		String adjunto = "Prueba.xlsx" ; 
		
		String email = "paulamoreno@cbit-online.com";
		String password = "Jack2019";
		String servidor = "smtp.gmail.com";
		Boolean ssl = true;
		String puerto = "25";
		
		assertTrue(enviar.sendMail(new EmisorEmail(email, password, servidor, puerto, ssl), 
				new ObjetoEmail(cc, cco, tema, cuerpo, adjunto, destinatario)));
		
	}


}
