package co.com.utilitarios.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Nonnull;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import co.com.utilitarios.excel.modelo.ObjetoHoja;
import co.com.utilitarios.exepcion.ExcepcionPeriferiaProceso;
import co.com.utilitarios.exepcion.modelo.TipoErrorProceso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Paula Moreno
 * Clase para la escritura de Excel a partir de los datos necesario de escritura del mismo
 */
public class EscritorExcel {


    private final static Logger LOGGER = LoggerFactory.getLogger(EscritorExcel.class);

	/**
     * @author Jhonnatan Gil
	 * Metodo para generar el excel sin un objeto Hoja, se solicitan los datos que no deben ser nulos necesarios para la generacion del excel
	 * @param titulos
	 * @param datos
	 * @param nombreHoja
	 * @return
	 * @throws ExcepcionPeriferiaProceso
	 */
	public static ByteArrayInputStream generar(@Nonnull List<String> titulos, @Nonnull List<List<String>> datos, String nombreHoja) throws ExcepcionPeriferiaProceso{
	    LOGGER.debug("Se reciben los datos para escritura del excel : " + titulos + " --> datos "+ datos + " para la hoja --> " + nombreHoja);
		ObjetoHoja objetoHoja = new ObjetoHoja();
		objetoHoja.setDatos(datos);
		objetoHoja.setNombre(nombreHoja);
		objetoHoja.setTitulos(titulos);
        LOGGER.debug("Se genera el objeto para la escritura del excel : " + objetoHoja);
		return generar(objetoHoja);
	}

	/**
	 * @author Paula Moreno
	 * @param informacion Objeto que contiene la estructura de las hojas del archivo a escribir
	 * @return retorna el documento de excel en byte.
	 * @throws ExcepcionPeriferiaProceso
	 */
	public static ByteArrayInputStream generar(@Nonnull ObjetoHoja informacion) throws ExcepcionPeriferiaProceso{

	    LOGGER.debug("Se inicia la escritura del Excel con el objeto "  + informacion);
		// Validaciones de todos los elementos del objeto
		if (null == informacion.getNombre() || informacion.getNombre().isEmpty()){
            LOGGER.debug("No existe un nombre");
            throw TipoErrorProceso.ESCRITOREXCEL_001.crearExcepcion();
        }

		if (null == informacion.getDatos() || informacion.getDatos().isEmpty()){
            LOGGER.debug("No existen datos");
            throw TipoErrorProceso.ESCRITOREXCEL_002.crearExcepcion();
        }

		if (null == informacion.getTitulos() || informacion.getTitulos().isEmpty()){
            LOGGER.debug("No existen titulos");
            throw TipoErrorProceso.ESCRITOREXCEL_003.crearExcepcion();
        }

		Workbook workbook = new XSSFWorkbook();

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Sheet sheet = workbook.createSheet(informacion.getNombre());

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 12);

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		int rowNum = 0;
		Row rowsito = sheet.createRow(rowNum++);
		LOGGER.debug("Se inicia la iteracion de los titulos");
		for (int i = 0; i < informacion.getTitulos().size(); i++) {
			CellStyle accountingStyle = workbook.createCellStyle();
			Cell cell = rowsito.createCell(i);
			String valorCelda = informacion.getTitulos().get(i);
			cell.setCellValue(valorCelda);
			if (informacion.getColores().get(i) != null && !informacion.getColores().get(i).equals("")) {
				IndexedColors back = informacion.getColores().get(i);
				accountingStyle.setFillForegroundColor(back.getIndex());
			}
			accountingStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			accountingStyle.setFont(headerFont);
			cell.setCellStyle(accountingStyle);
			LOGGER.debug("Escribiendo el titulo : " + valorCelda);
		}

		LOGGER.debug("Inicia la iteracion de los datos");
		for (List<String> data : informacion.getDatos()) {
			int contador = 0;
			Row fila = sheet.createRow(rowNum++);
			LOGGER.debug("Escritura de los datos en la fila --> " + contador);
			for (String data2 : data) {
				fila.createCell(contador++).setCellValue(data2);
			}
		}

		try {
			workbook.write(out);
		} catch (IOException e) {
		    LOGGER.debug("EE004", e);
		    throw TipoErrorProceso.ESCRITOREXCEL_004.crearExcepcion();
		} finally {
			try {
			    LOGGER.debug("Cerrando el arraybyte");
				out.flush();
				out.close();
				workbook.close();
			} catch (IOException e) {
			    LOGGER.debug("EE005", e);
			    throw TipoErrorProceso.ESCRITOREXCEL_005.crearExcepcion();
			} finally {
				try {
				    LOGGER.debug("Cerrando el Libro");
					workbook.close();
				} catch (IOException e) {
				    LOGGER.debug("EE006", e);
				    throw TipoErrorProceso.ESCRITOREXCEL_006.crearExcepcion();
				}
			}
		}

		LOGGER.debug("Finaliza de forma correcta el proceso");
		return new ByteArrayInputStream(out.toByteArray());
	}
}
