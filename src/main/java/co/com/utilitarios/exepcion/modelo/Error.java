package co.com.utilitarios.exepcion.modelo;

import co.com.utilitarios.exepcion.ExcepcionPeriferia;

/**
 * Identificacion de ocurrencia de un error concreto
 * @param <K> Clase de la clave para referencia de los errores
 * @param <T> Tipo de error {@link }
 */
public class Error<K, T extends ITipoError<K, T, ? extends ExcepcionPeriferia>> {

    private T error;
    private K codigo;
    private String mensaje;

    public Error(T error, K codigo, String mensaje){
        this.error = error;
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public T getError() {
        return error;
    }

    public void setError(T error) {
        this.error = error;
    }

    public K getCodigo() {
        return codigo;
    }

    public void setCodigo(K codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return getMensaje();
    }
}
