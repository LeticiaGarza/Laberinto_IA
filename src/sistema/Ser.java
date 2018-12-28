package sistema;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Ser {

    String nombre;
    ImageIcon imagen;
    ArrayList<Float> costos;
    ArrayList<String> terrenosVal;
    ArrayList<String> terrenosInval;

    public Ser(String nombre, ImageIcon imagen, ArrayList<String> terrenosVal,
            ArrayList<String> terrenosInval, ArrayList<Float> costos) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.costos = costos;
        this.terrenosVal = terrenosVal;
        this.terrenosInval = terrenosInval;
    }

    public String dameNombre() {
        return nombre;
    }

    public ImageIcon dameImagen() {
        return imagen;
    }

    public ArrayList<Float> dameCostos() {
        return costos;
    }

    public ArrayList<String> dameTerrenosVal() {
        return terrenosVal;
    }

    public ArrayList<String> dameTerrenosInval() {
        return terrenosInval;
    }
}
