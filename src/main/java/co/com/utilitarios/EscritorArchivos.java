package co.com.utilitarios;

import co.com.utilitarios.exepcion.ExcepcionPeriferiaProceso;
import co.com.utilitarios.exepcion.modelo.TipoErrorProceso;

import java.io.*;


/***
 * @author jhonnatangil
 * Clase utilitario para la escritura de archivos al host donde se ejecute la JVM
 */
public class EscritorArchivos {

    /***
     * Metodo para la escritura de un archivo a una ruta del host
     * @author jhonnatangil
     * @param bytes
     * @param rutaArchivo
     * @return boolean si el archivo quedo escrito en la ruta enviada
     * @throws ExcepcionPeriferiaProceso
     */
    public static boolean escribir(byte[] bytes, String rutaArchivo)throws ExcepcionPeriferiaProceso {
        File file = new File(rutaArchivo);
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw TipoErrorProceso.ESCRITORARCHIVOS_001.crearExcepcion("Archivo destino no disponible");
        }
        try {
            os.write(bytes);
        } catch (Exception e) {
            throw TipoErrorProceso.ESCRITORARCHIVOS_001.crearExcepcion("Escribiendo el archivo");
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                throw TipoErrorProceso.ESCRITORARCHIVOS_001.crearExcepcion("Cerrando el archivo a escribir");
            }
        }
        return true;
    }

}
