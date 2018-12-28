package sistema;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Terreno {

    float costo;
    int posX;
    int posY;
    int fila;
    int columna;
    int numVisita;

    String id;
    String tipo;
    String posicion;
    boolean estaOculto;
    JLabel imagen;
    JLabel mascara;
    ImageIcon imagenAux;
    ImageIcon imagenOculta;

    public JLabel dameImagen() {
        return imagen;
    }

    public void fijaImagen(JLabel imagen) {
        this.imagen = imagen;
    }

    public JLabel dameMascara() {
        return mascara;
    }

    public void fijaMascara(JLabel mascara) {
        this.mascara = mascara;
    }

    public String dameId() {
        return id;
    }

    public void fijaId(String id) {
        this.id = id;
    }

    public String dameTipo() {
        return tipo;
    }

    public void fijaTipo(String tipo) {
        this.tipo = tipo;
    }

    public String damePosicion() {
        return posicion;
    }

    public void fijaPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int damePosX() {
        return posX;
    }

    public void fijaPosX(int posX) {
        this.posX = posX;
    }

    public int damePosY() {
        return posY;
    }

    public void fijaPosY(int posY) {
        this.posY = posY;
    }

    public int dameFila() {
        return fila;
    }

    public void fijaFila(int fila) {
        this.fila = fila;
    }

    public float dameCosto() {
        return costo;
    }

    public void fijaCosto(float costo) {
        this.costo = costo;
    }

    public int dameColumna() {
        return columna;
    }

    public void fijaColumna(int columna) {
        this.columna = columna;
    }

    public int dameNumVisita() {
        return numVisita;
    }

    public void aumentarVisita() {
        numVisita += 1;
    }

    public Terreno(JLabel imagen, JLabel mascara, String id, String tipo,
            int fila, int columna, String posicion, int posX, int posY) {
        this.imagen = imagen;
        this.mascara = mascara;
        this.id = id;
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;
        this.posicion = posicion;
        this.posX = posX;
        this.posY = posY;
        estaOculto = true;
        numVisita = 0;
    }

    public void fijaVisita(int numVisita) {
        this.numVisita = numVisita;
    }

    public void fijaOcultar(boolean estaOculto) {
        this.estaOculto = estaOculto;
    }

    public boolean estaOculto() {
        return estaOculto;
    }

    public void fijaImagenTerreno(ImageIcon img) {
        imagen.setIcon(img);
    }

    public void fijaImagenAux(ImageIcon imagenAux) {
        this.imagenAux = imagenAux;
    }

    public ImageIcon dameImagenAux() {
        return imagenAux;
    }
}
