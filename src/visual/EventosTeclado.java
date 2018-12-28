package visual;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

public class EventosTeclado implements KeyListener{

	@Override
	public void keyPressed(KeyEvent teclado) {
		JOptionPane.showMessageDialog(null, "Tecla presionada");
	}

	@Override
	public void keyReleased(KeyEvent teclado) {
		
	}

	@Override
	public void keyTyped(KeyEvent teclado) {
		
	}

}
