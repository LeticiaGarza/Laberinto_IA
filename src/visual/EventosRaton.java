package visual;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sistema.Cuadricula;
import sistema.Terreno;

public class EventosRaton extends MouseAdapter {

    public static Terreno estadoFinal;
    public static Terreno estadoInicial;
    JPanel cuadricula;

    public EventosRaton() {
        estadoInicial = null;
        estadoFinal = null;
    }

    @Override
    public void mouseClicked(MouseEvent raton) {
        if (raton.getClickCount() == 2) {
            if (estadoInicial == null) {
                estadoInicial = Cuadricula.dameTerreno(raton.getX(), raton.getY());
                estadoInicial.fijaImagenTerreno(estadoInicial.dameImagenAux());
                Ventana.estadoInicioFin.setText("Inicio " + estadoInicial.damePosicion());
            } else if (estadoFinal == null) {
                estadoFinal = Cuadricula.dameTerreno(raton.getX(), raton.getY());
                estadoFinal.fijaImagenTerreno(estadoInicial.dameImagenAux());
                Ventana.estadoInicioFin.setText(Ventana.estadoInicioFin.getText()
                        + " Fin " + estadoFinal.damePosicion());
            } else if (Ventana.listaTipoTerrenos != null) {
                Panel.mostrarDatosCordenada(Cuadricula.dameTerreno(raton.getX(), raton.getY()));
            } else {
                JOptionPane.showMessageDialog(null, "No ingresaste los datos de los terrenos");
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent raton) {

    }
}
