package co.com.utilitarios;

import static org.junit.Assert.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.junit.Test;

import co.com.utilitarios.EscritorArchivos;
import co.com.utilitarios.excel.EscritorExcel;
import co.com.utilitarios.excel.modelo.ObjetoHoja;
import co.com.utilitarios.exepcion.ExcepcionPeriferiaProceso;

public class GenerarExcelTest {

	@Test
	public void GenerarByte() throws ExcepcionPeriferiaProceso, IOException {

		EscritorExcel escritorExcel = new EscritorExcel();

		List<String> titulos = Arrays.asList("nombre", "apellido", "ciudad", "edad", "RH", "Estado Civil");

		List<IndexedColors> colores = Arrays.asList(IndexedColors.RED, IndexedColors.BLUE, IndexedColors.BLUE,
				IndexedColors.YELLOW, IndexedColors.GREEN, IndexedColors.AQUA);

		List<List<String>> datos = Arrays.asList(Arrays.asList("Paula", "Moreno", "Bogota", "26", "A+", "Soltero"),
				Arrays.asList("Juan", "Reyes", "Bogota", "22", "A+", "Soltero"),
				Arrays.asList("Juan", "Silva", "Bogota", "24", "O+", "Soltero"),
				Arrays.asList("Raphael", "Martinez", "Bogota", "20", "AB-", "Soltero"),
				Arrays.asList("Camilo", "Soler", "Bogota", "25", "A+", "Soltero"),
				Arrays.asList("Diego", "Rodriguez", "Bogota", "30", "O-", "Soltero"));

		ByteArrayInputStream in = escritorExcel.generar(new ObjetoHoja("prueba", titulos, colores, datos));
		assertNotSame(in, null);
		GenerarExcel(in);
	}

	public void GenerarExcel(ByteArrayInputStream in) throws ExcepcionPeriferiaProceso, IOException {
		byte[] array = new byte[in.available()];
		in.read(array);
		assertTrue(EscritorArchivos.escribir(array, "Prueba.xlsx"));
	}

}
