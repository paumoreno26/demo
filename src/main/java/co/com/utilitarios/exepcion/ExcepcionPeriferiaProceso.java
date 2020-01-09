package co.com.utilitarios.exepcion;

import co.com.utilitarios.exepcion.modelo.Error;
import co.com.utilitarios.exepcion.modelo.TipoErrorProceso;

/**
 * @author jhonnatangil
 * Clase para el manejo de las excepciones de tipo Proceso para todas las expceciones de {@link ExcepcionPeriferia}
 */
public class ExcepcionPeriferiaProceso extends ExcepcionPeriferia {

    private Error<Integer, TipoErrorProceso> error;

    public ExcepcionPeriferiaProceso(Throwable causa, Error<Integer, TipoErrorProceso> error) {
        super(error.getMensaje(), causa);
        this.error = error;
    }

    public ExcepcionPeriferiaProceso(Error<Integer, TipoErrorProceso> error) {
        super(error.getMensaje());
        this.error = error;
    }

    public Error<Integer, TipoErrorProceso> getError() {
        return error;
    }
}
