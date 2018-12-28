package util;

import java.awt.Image;	
import java.io.File;
import java.io.FileInputStream;
import javax.swing.ImageIcon;

public class Imagen {
	public static ImageIcon redimencionar(String rutaImagen,int ancho,int alto, byte[] pixImg){
		ImageIcon imagen;				
		if(rutaImagen!=null){
		 imagen = new ImageIcon(Imagen.class.getResource(rutaImagen));
		}
		else{
			imagen = new ImageIcon(pixImg);
		}
		Image fondoImg = imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
		ImageIcon imgRedimensionada = new ImageIcon(fondoImg);
		return imgRedimensionada;
	}
	
	public static byte[] dameImagenEnBytes(File archivoImagen){
		byte[] arregloBytesImagen = new byte[1024*1024];	
		try{
			FileInputStream archivoCadenaImagen = new FileInputStream(archivoImagen);
		    archivoCadenaImagen.read(arregloBytesImagen);
		    archivoCadenaImagen.close();
		}catch(Exception excepcion){}
		return arregloBytesImagen;
	}
}
