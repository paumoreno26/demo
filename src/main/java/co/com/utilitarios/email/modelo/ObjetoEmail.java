package co.com.utilitarios.email.modelo;

/***
 * @author Paula Moreno
 * Clase para el manejo de la estructura del correo
 */

public class ObjetoEmail {
	
	/***
	 * email del usuario que va a recibir el correo
	 */
	private String destinatario;
	/***
	 * email del usuario que va recibir copia del correo
	 */
	private String cc;
	/***
	 * email del usuario que va recibir copia oculta del correo
	 */
	private String cco;
	/***
	 * asusto que va a tener el correo
	 */
	private String asunto;
	/***
	 * cuerpo que va a tener el correo
	 */
	private String cuerpo;
	/***
	 * archivo adjunto que va a tener el correo si se desea
	 */
	private String adjunto;
	
	
	public ObjetoEmail(String cc, String cco, String asunto, String cuerpo, String adjunto, String destinatario) {
		super();
		this.destinatario = destinatario;
		this.cc = cc;
		this.cco = cco;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
		this.adjunto = adjunto;
	}
	
	
	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}


	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getCco() {
		return cco;
	}
	public void setCco(String cco) {
		this.cco = cco;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	public String getAdjunto() {
		return adjunto;
	}
	public void setAdjunto(String adjunto) {
		this.adjunto = adjunto;
	}
	
	


}
