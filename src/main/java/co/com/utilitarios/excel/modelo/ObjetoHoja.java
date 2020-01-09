package co.com.utilitarios.excel.modelo;

import java.util.List;
import org.apache.poi.ss.usermodel.IndexedColors;

/***
 * @author Paula Moreno
 * Clase para el manejo de hoja de un archivo en Excel
 */
public class ObjetoHoja{

	/***
	 * Nombre de la hoja en el archivo destino
	 */
	private String nombre;
	/**
	 * Titulos en la primera fila
	 */
	private List<String> titulos;
	/**
	 * Colores que utilizaran los titulos
	 */
	private List<IndexedColors> colores;
	/**
	 * Datos que se escribiran en la hoja
	 */
	private List<List<String>> datos;

	public ObjetoHoja(){}
	
	public ObjetoHoja(String nombre, List<String> titulos, List<IndexedColors> colores, List<List<String>> datos) {
		super();
		this.nombre = nombre;
		this.titulos = titulos;
		this.colores = colores;
		this.datos = datos;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<String> getTitulos() {
		return titulos;
	}
	public void setTitulos(List<String> titulos) {
		this.titulos = titulos;
	}
	public List<IndexedColors> getColores() {
		return colores;
	}
	public void setColores(List<IndexedColors> colores) {
		this.colores = colores;
	}
	public List<List<String>> getDatos() {
		return datos;
	}
	public void setDatos(List<List<String>> datos) {
		this.datos = datos;
	}
	

}
