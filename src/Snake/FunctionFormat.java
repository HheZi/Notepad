package Snake;
import java.awt.Font;

import javax.swing.JTextArea;

public class FunctionFormat {
	GUI gui;
	
	boolean wordWrapOnOff;
	
	Font arial, CMSC,TNR, Calibri, Palatino;
	
	String currentFont;
	
	int fontSize = 0;
	
	FunctionFormat(GUI gui) {
		this.gui = gui;
	}
	
	public void wordWrap() {
		if(!wordWrapOnOff) {
			wordWrapOnOff = true;
			gui.textPanel.setLineWrap(true);
			gui.textPanel.setWrapStyleWord(true);
			gui.fwrap.setText("Word wrap: true");
		}
		else {
			wordWrapOnOff = false;
			gui.textPanel.setLineWrap(false);
			gui.textPanel.setWrapStyleWord(false);
			gui.fwrap.setText("Word wrap: off");
		}
	}
	
	
	public void createFont(int size, JTextArea textPanel) {
		fontSize = size;
		
		arial = new Font("Arial", Font.PLAIN, fontSize);
		CMSC = new Font("Comic Sans MS", Font.PLAIN, fontSize);
		TNR = new Font("Times New Roman", Font.PLAIN, fontSize);
		Calibri = new Font("Calibri", Font.PLAIN, fontSize);
		
		setFontSize(currentFont, textPanel);
	}
	
	public void setFontSize(String font, JTextArea textPanel) {
		currentFont = font;
		
		switch(currentFont) {
			case "Arial":
				textPanel.setFont(arial);
				break;
			case "Comic":
				textPanel.setFont(CMSC);
				break;
			case "Times":
				textPanel.setFont(TNR);
				break;
			case "Calibri": 
				textPanel.setFont(Calibri);
		}
	}
	
}
