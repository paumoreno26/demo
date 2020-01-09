package co.com.utilitarios.email.modelo;

/***
 * @author Paula Moreno
 * Clase para el manejo del emisor que envia el correo
 */

public class EmisorEmail {
	
	/***
	 * email del usuario que va enviar el correo
	 */
	private String email;
	/***
	 * password del usuario que va enviar el correo
	 */
	private String password;
	/***
	 * servidor por el cual se va enviar el correo
	 */
	private String servidor;
	/***
	 * puerto por el cual se va enviar el correo
	 */
	private String puerto;
	/***
	 * ssl por el cual se va enviar el correo
	 */
	private Boolean tls;
	
	
	public EmisorEmail(String email, String password, String servidor, String puerto, Boolean tls) {
		super();
		this.email = email;
		this.password = password;
		this.servidor = servidor;
		this.puerto = puerto;
		this.tls = tls;
	}
	

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getServidor() {
		return servidor;
	}
	public void setServidor(String servidor) {
		this.servidor = servidor;
	}
	public String getPuerto() {
		return puerto;
	}
	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	public Boolean getTls() {
		return tls;
	}

	public void setTls(Boolean tls) {
		this.tls = tls;
	}
}
