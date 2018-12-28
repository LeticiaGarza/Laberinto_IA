package util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Constante {
	public static final String ARRIBA = "arriba";
	public static final String ABAJO = "abajo";
	public static final String IZQUIERDA = "izquierda";
	public static final String DERECHA = "derecha";
	public static final String DEFAULT = "default";
	
	static int filas;
	static int columnas;	
	static int altoVentana;
	static int anchoVentana;	
	static int anchoCuadricula;
	static int altoCuadricula;
	static int margenError;
	static int margenAltoCuadricula;
	static int margenAnchoCuadricula;
	static int ladoImagen;
	static boolean esRedimensionable;	
	static JFrame principal;
	
	public static void inicialiar(){
		Dimension ventana = new Dimension();
		ventana = Toolkit.getDefaultToolkit().getScreenSize();		  
		  anchoVentana = (int)ventana.getWidth();
		  altoVentana = (int)ventana.getHeight();
		  margenError = 50;
		  margenAltoCuadricula = 150;
		  margenAnchoCuadricula = 375;		  
		  altoCuadricula = altoVentana-margenAltoCuadricula;	
		  anchoCuadricula = anchoVentana-margenAnchoCuadricula;
		  esRedimensionable = false;
		  ladoImagen = 128;
	}

	public static int dameAltoVentana() { return altoVentana; }
	public static int dameAnchoVentana() { return anchoVentana; }
	public static int dameAnchoCuadricula() { return anchoCuadricula; }
	public static int dameAltoCuadricula() { return altoCuadricula; }
	public static int dameMargenError() { return margenError; }
	public static int dameMargenAltoCuadricula() { return margenAltoCuadricula; }
	public static int dameMargenAnchoCuadricula() { return margenAnchoCuadricula; }
	public static int dameLadoImagen() { return ladoImagen; }
	public static boolean esRedimensionable() { return esRedimensionable; }
	
	public static int dameFilas() { return filas; }
	public static void fijaFilas(int filas) { Constante.filas = filas; }
	public static int dameColumnas() { return columnas; }
	public static void fijaColumnas(int columnas) { Constante.columnas = columnas;}	
}
