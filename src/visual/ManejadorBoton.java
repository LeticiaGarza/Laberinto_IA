package visual;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sistema.Administradora;

public class ManejadorBoton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent boton) {			
			String btnPresionado = boton.getActionCommand();
			switch(btnPresionado){
			case Botones.ETIQUETA_BTN_CARGAR_MAPA:
			    Administradora.cargarMapa();		
			break;
			
			case Botones.ETIQUETA_BTN_JUGAR:
				Administradora.jugar();
			break;
				
			case Botones.ETIQUETA_BTN_REINICIAR:
				Administradora.reiniciar();
			break;
			
			case Botones.ETIQUETA_BTN_INFO_TERRENO:
				Administradora.infoTerrenos();
			break;
				
			case Botones.ETIQUETA_BTN_MOSTRAR_ARBOL:
				Administradora.mostrarArbol();
			break;
				
			case Botones.ETIQUETA_BTN_CREAR_SER:
				Administradora.crearSer();
				break;				
		}
	}
}
