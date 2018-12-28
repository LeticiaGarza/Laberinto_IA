package util;
import java.io.BufferedReader;	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.regex.*;

public class Archivo {
	static File archivo;	
	static String DELIMITADOR = ",";
	private static String TIPO_IMAGEN = "Imagen";	
	
	static int filas;
	static int columnas;		
	static String expresionRegular;
	
	public static ArrayList<String[]> listaTerrenos;
	public static ArrayList<String> listaTipoTerrenos;
	public static ArrayList<ImageIcon> listaColores; 

	public static boolean elegirArchivo(String tipArchivo){
		filas = 0;
		columnas = 0;
		int resultado = 0;
		boolean archivoElegido = false;		
		try{
			if(tipArchivo.equals(TIPO_IMAGEN)){
				expresionRegular = ".+[.jpg | .png | .jpeg | .gif]$";
			}
			else expresionRegular = ".+[.txt]$";										
			
			JFileChooser selecArchivo = new JFileChooser();
			FileNameExtensionFilter filtro; 
			filtro = new FileNameExtensionFilter("*.Archivos","png","jpg","jpeg","gif","txt");
			selecArchivo.setCurrentDirectory(new File(System.getProperty("user.home")));
			selecArchivo.setFileFilter(filtro);	
			
			resultado = selecArchivo.showSaveDialog(null);
			if(resultado==JFileChooser.APPROVE_OPTION){
				archivoElegido = true;
				archivo = selecArchivo.getSelectedFile();
			}
			else {
				archivoElegido = false;
			}
		}catch(Exception excepcion){
		}
		return archivoElegido;		
	}
	
	public static boolean esArchivoValido(){	
		boolean esArchivoValido = false;	
	 	Pattern expresion = Pattern.compile(expresionRegular);
	     Matcher validarNomArchivo = expresion.matcher(archivo.getName());
	     if (validarNomArchivo.matches()) {
			esArchivoValido = true;
		}
		return esArchivoValido;
	}	
	
	public static boolean validarDatosArchivo(){
		boolean datosValidos = true;
		listaTerrenos = new ArrayList<String[]>();
		expresionRegular = "^\\d+(,\\d+)*";
		try {
			int colAux;					
			BufferedReader datosArchivo = new BufferedReader(new FileReader(archivo));
			String datosLeidos = datosArchivo.readLine();			
			Pattern expresion = Pattern.compile(expresionRegular);
		    Matcher validarDatosLeidos = expresion.matcher(datosLeidos);		    
		    colAux = datosLeidos.split(DELIMITADOR).length;
		    String[] idlistaTerrenos = new String[colAux];
		    idlistaTerrenos = datosLeidos.split(DELIMITADOR);
		    listaTerrenos.add(idlistaTerrenos);
			while(datosLeidos!=null && validarDatosLeidos.matches() && datosValidos){					
				 datosLeidos = datosArchivo.readLine();
				 if(datosLeidos!=null){
					 columnas = datosLeidos.split(DELIMITADOR).length;
					 idlistaTerrenos = new String[columnas];
					 idlistaTerrenos = datosLeidos.split(DELIMITADOR);
					 listaTerrenos.add(idlistaTerrenos);
					 validarDatosLeidos = expresion.matcher(datosLeidos);	
					 columnas = idlistaTerrenos.length;					 				     				
				 }
				 if(colAux > columnas) datosValidos = false;				 
				 if(datosLeidos!=null) colAux = columnas;
				 filas++;
			}			
			if(filas==1) columnas = colAux;
			if(datosLeidos==null && filas<=15 && columnas<=15){
				datosValidos = true;
				Constante.fijaFilas(filas);
				Constante.fijaColumnas(columnas);
				crearListaTipoTerrenos();
			}else datosValidos = false;						
			datosArchivo.close();
		} catch (FileNotFoundException excepcion) {}
		catch (IOException excepcion) {}		
		return datosValidos;
	}	
	
	public static void crearListaTipoTerrenos(){
		listaTipoTerrenos = new ArrayList<String>();
		String [] auxListaTerrenos;
		for(int i=0; i<filas; i++){
			auxListaTerrenos = listaTerrenos.get(i);
			for(int j=0; j<columnas; j++){
				listaTipoTerrenos.add(auxListaTerrenos[j]);
			}			
		}
		Set<String> purgarListaTipoTerrenos = new HashSet<>();
		purgarListaTipoTerrenos.addAll(listaTipoTerrenos);
		listaTipoTerrenos.clear();
		listaTipoTerrenos.addAll(purgarListaTipoTerrenos);
		crearListaColores();
	}
	
	public static void crearListaColores(){
		listaColores = new ArrayList<ImageIcon>();
		listaColores.add(new ImageIcon(Archivo.class.getResource("/imagenes/amarillo.png")));
		listaColores.add(new ImageIcon(Archivo.class.getResource("/imagenes/azul.png")));
		listaColores.add(new ImageIcon(Archivo.class.getResource("/imagenes/verde.png")));
		listaColores.add(new ImageIcon(Archivo.class.getResource("/imagenes/rosado.png")));
		listaColores.add(new ImageIcon(Archivo.class.getResource("/imagenes/azul_bajito.png")));
		listaColores.add(new ImageIcon(Archivo.class.getResource("/imagenes/morado.png")));
		listaColores.add(new ImageIcon(Archivo.class.getResource("/imagenes/verde_claro.png")));
		listaColores.add(new ImageIcon(Archivo.class.getResource("/imagenes/azul_fuerte.png")));
		listaColores.add(new ImageIcon(Archivo.class.getResource("/imagenes/naranja.png")));
		listaColores.add(new ImageIcon(Archivo.class.getResource("/imagenes/negro.png")));
	}
	
	public static File dameArchivo(){ 
		return archivo; 
	}	
}