package Notepad;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JTextArea;

public class FunctionHelp {
	GUI gui;
	
	FunctionHelp(GUI gui) {
		this.gui = gui;
	}
	
	public void createHelpWindow() {
		 JDialog dialog = new JDialog(gui.window, "About", true);
	     
		 JTextArea text = new JTextArea();
	     
		 Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
		 
	     dialog.addWindowListener(new WindowAdapter() {
	    	 public void windowClosing(WindowEvent e) {
					dialog.dispose();
			
	     }});
	     
	     
	     text.setText("It's simple clone of notepad created by HheZi.\nLink to the github: https://github.com/HheZi");
	     gui.funcForm.createFont(18, text);
	     text.setEditable(false);
	     
	     dialog.add(text);
	     
	     dialog.setIconImage(new ImageIcon("Icon.png").getImage());
	     dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	     dialog.setSize(400, 300);
	     dialog.setLocation((sizeScreen.width - gui.weidthScreen)/2, (sizeScreen.height - gui.heigthScreen)/2);
	     dialog.setLocationRelativeTo(null);
	     dialog.setVisible(true);
	}
}
