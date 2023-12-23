package Notepad;
import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class FunctionFile {
	GUI gui;
	private String fileName, fileDirection, nameSaveAsFile;
	private boolean checkIfChosedCancel;
	
	FunctionFile(GUI gui){
		this.gui = gui;
	}
	
	private void clearForNewFile() {
		gui.textPanel.setText("");
		gui.window.setTitle("Unnamed");
		fileName = null;
		fileDirection = null;
	}
	
	private void checkEndsWithTXT() {
		if(fileName.endsWith(".txt")) {nameSaveAsFile = fileDirection + fileName;}
		else nameSaveAsFile = fileDirection + fileName + ".txt";
	}
	
	public void newFile() {
		int input = JOptionPane.showConfirmDialog(null, "Do you want to save this file?", "Create new?" ,JOptionPane.YES_NO_CANCEL_OPTION);
		if(input == 0) {
			if(fileName != null) {
				saveFile();
				clearForNewFile();
			}
			else {
				saveAsFile();
				if(checkIfChosedCancel) {
					clearForNewFile();
				}
			}
		}
		else if(input == 1) {
			clearForNewFile();
		}
		
	}
	
	public void openFile() {
		FileDialog dialog = new FileDialog(gui.window, "Open", FileDialog.LOAD);
		dialog.setVisible(true);
		
		if(dialog.getFile() != null ) {
				if(dialog.getFile().endsWith(".txt")) {
					
				fileName = dialog.getFile();
				fileDirection = dialog.getDirectory();
				
				gui.window.setTitle(fileName);
				
				try {
					BufferedReader bf = new BufferedReader(new FileReader(fileDirection + fileName));
					
					gui.textPanel.setText("");
					
					String line = null;
					
					while((line = bf.readLine()) != null) {
						gui.textPanel.append(line + "\n");
					}
					bf.close();
				}
				catch(Exception e){}
			}
			else {
				JOptionPane.showMessageDialog(gui.window, "File type must be a txt", "Error", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		
		
		
	}
	
	public void saveFile() {
		if(fileName == null && fileDirection == null) {
			saveAsFile();
		}
		else {
			try {
				checkEndsWithTXT();
				FileWriter fw = new FileWriter(nameSaveAsFile);
				
				fw.write(gui.textPanel.getText());
				
				gui.window.setTitle(fileName);
				
				fw.close();
			}
			catch(Exception e){}
		}
	}
	
	public void saveAsFile() {
		
		FileDialog dialog = new FileDialog(gui.window, "Save", FileDialog.SAVE);
		dialog.setVisible(true);
		
		if(dialog.getFile() != null) {
			
			fileName = dialog.getFile();
			fileDirection = dialog.getDirectory();
				
			gui.window.setTitle(fileName);
				
			try {
				checkEndsWithTXT();
				FileWriter fw = new FileWriter(nameSaveAsFile);
					
				fw.write(gui.textPanel.getText());
					
				gui.window.setTitle(fileName);
					
				fw.close();
			}
			catch(Exception e){}
				
			checkIfChosedCancel = true;
		}
		else checkIfChosedCancel = false;	
	}
	
	public int exit() {
		int input = JOptionPane.showConfirmDialog(null, "Do you want to save this file before exit?", "Exit" ,JOptionPane.YES_NO_CANCEL_OPTION);
		
		if(input == 0) {
			if(fileName != null) {
				saveFile();
				System.exit(0);
			}
			else {
				saveAsFile();
				if(checkIfChosedCancel) {
					System.exit(0);
				}
			}
		}
		else if(input == 1) {System.exit(0);}
		return 0;
	}
}
