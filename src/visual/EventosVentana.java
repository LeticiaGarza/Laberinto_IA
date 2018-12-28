package visual;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EventosVentana extends WindowAdapter{
	@Override
	public void windowClosing(WindowEvent ventana) {
		switch(ventana.getWindow().getName()){
			case Ventana.TITULO_VENTANA_INFO_TERRENOS:
				Ventana.ventanaInfoTerreno = false;
				break;			
		}
	}
	
	@Override
	public void windowActivated(WindowEvent ventana) {
		
	}
}
