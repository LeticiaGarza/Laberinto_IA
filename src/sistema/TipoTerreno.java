package sistema;

import javax.swing.ImageIcon;

public class TipoTerreno {

    String id;
    String tipo;
    ImageIcon imagen;

    public ImageIcon dameImagen() {
        return imagen;
    }

    public String dameId() {
        return id;
    }

    public String dameTipo() {
        return tipo;
    }

    public TipoTerreno(ImageIcon imagen, String id, String tipo) {
        this.imagen = imagen;
        this.id = id;
        this.tipo = tipo;
    }
}
