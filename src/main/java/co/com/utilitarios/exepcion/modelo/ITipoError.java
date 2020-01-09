package co.com.utilitarios.exepcion.modelo;

import co.com.utilitarios.exepcion.ExcepcionPeriferia;

/**
 * Interfaz para exponer los metodos generales
 * @author jhonnatangil
 * @param <K> Tipo de dato (Clase), para el manejo del codigo del error
 * @param <T> Tipo del error concreto que puede crear
 * @param <S> Tipo de excepcion concreta que puede crear {@link ExcepcionPeriferia}
 */
public interface ITipoError<K, T extends ITipoError<K, T, S>, S extends ExcepcionPeriferia> {

    public K getCodigo();

    public String getMensaje(Object ... args);

    public Object[] getArgs();

    public Error<K, T> crearError(Object ... args);

    public S crearExcepcion(Object ... args);

}
