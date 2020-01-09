package co.com.utilitarios.exepcion.modelo;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.MissingFormatArgumentException;

import co.com.utilitarios.exepcion.ExcepcionPeriferiaProceso;

/**
 * Codigos de error definidos para el proceso de los utilitarios
 */
public enum TipoErrorProceso implements ITipoError<Integer, TipoErrorProceso, ExcepcionPeriferiaProceso> {

    ESCRITOREXCEL_001    (1001, "No existe un nombre"),
    ESCRITOREXCEL_002    (1002, "No existen datos"),
    ESCRITOREXCEL_003    (1003, "No existen titulos"),
    ESCRITOREXCEL_004    (1004, "Error de IO escribiendo el libro"),
    ESCRITOREXCEL_005    (1005, "Error de IO cerrando el Bytearray"),
    ESCRITOREXCEL_006    (1006, "Error de IO cerrando el Libro"),
    ENVIAREMAIL_EMISOR   (2001, "No hay informacion del emisor, %s"),
    ENVIAREMAIL_RECEPTOR (2002, "No hay informacion del receptor, %s"),
    ENVIAREMAIL_COMPONER (2003, "Error al momento de agregar el [%s] al mensaje"),
    ENVIAREMAIL_ADJUNTO  (2004, "Error al momento de agregar el adjunto [%s]"),
    ENVIAREMAIL_ENVIANDO (2005, "Error enviando el mensaje"),
    ESCRITORARCHIVOS_001 (3001, "Error en la escirtura del archivo [%s]")    
    ;

    public final Integer codigo;
    private final String mensaje;
    private final Object[] args;

    private static class Valores{
        private final static Map<Integer, TipoErrorProceso> tipos = new LinkedHashMap<>();
    }

    TipoErrorProceso(int codigo, String mensaje, Object ... args){
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.args = args;

        Valores.tipos.put(codigo, this);
    }

    @Override
    public Integer getCodigo() {
        return codigo;
    }

    @Override
    public String getMensaje(Object... args) {
        if (args == null || args.length == 0) {
            args = this.args;
        }

        try {
            return String.format(mensaje, args);
        }
        catch (MissingFormatArgumentException ex) {
            throw new IllegalArgumentException(ex.getClass().getCanonicalName() + ": " + this.name() + ". " + ex.getMessage());
        }
    }

    @Override
    public Object[] getArgs() {
        return args;
    }

    @Override
    public Error<Integer, TipoErrorProceso> crearError(Object... args) {
        return new Error<Integer, TipoErrorProceso>(this, codigo, getMensaje(args));
    }

    @Override
    public ExcepcionPeriferiaProceso crearExcepcion(Object... args) {
        return new ExcepcionPeriferiaProceso(crearError(args));
    }

    public ExcepcionPeriferiaProceso crearExcepcion(Throwable causa, Object... args) {
        return new ExcepcionPeriferiaProceso(causa, crearError(args));
    }
}
