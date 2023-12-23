package Notepad;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GUI gui;
	
	KeyHandler(GUI gui) {
		this.gui = gui;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S){
			gui.funcFile.saveFile();
		}
		else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_N) {
			gui.funcFile.newFile();
		}
		else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_O) {
			gui.funcFile.openFile();
		}
		else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
			gui.funcUndoRedo.undo();
		}
		else if(e.isAltDown() && e.getKeyCode() == KeyEvent.VK_F4) {
			gui.funcFile.exit();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

}
