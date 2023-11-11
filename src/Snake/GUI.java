package Snake;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

public class GUI implements ActionListener {
	
	int weidthScreen;
	int heigthScreen;
	
	JFrame window;
	
	JPanel bottomPanel;
	
	JTextArea textPanel;
	
	JScrollPane panel;
	
	JMenuBar menuBar;

	JMenu menuFile, menuEdit, menuFormat;
	
	JMenuItem fnew, fopen, fsave, fsaveAs, fexit; 
	JMenuItem fcut, fcopy, fpaste, fdel, fundo, fredo;
	
	JMenu menuFont, menuFontSize;
	
	JMenuItem fwrap, fFontArial, fFontCMSC, fFontTNR, FFontCalibri,
	fFontSize8, fFontSize12, fFontSize16, fFontSize20, fFontSize24, fFontSize28,
	fFontSize32, fFontSize36, fFontSize40, fFontSize44, fFontSize48, fFontSize52;
	
	JMenu menuHelp;
	
	JMenuItem itemHelp;
	
	JSeparator separator, separator2;
	
	KeyHandler kh = new KeyHandler(this);
	FunctionFile funcFile = new FunctionFile(this);
	FunctionFormat funcForm = new FunctionFormat(this);
	
	UndoManager undoManager = new UndoManager();
	
	FunctionUndoRedo funcUndoRedo = new FunctionUndoRedo(undoManager);
	
	FunctionHelp help = new FunctionHelp(this);
			
	GUI(int WEIDTH_SCREEN, int HEIGTH_SCREEN){
		this.weidthScreen = WEIDTH_SCREEN;
		this.heigthScreen = HEIGTH_SCREEN;
	}
	
	public void startProgram() {
		createWindow();
		createTextField();
		createMenuBar();
		createFileMenu();
		createEditMenu();
		createFormatMenu();
		createHelpMenu();
		
		funcForm.currentFont = "Arial";
		funcForm.createFont(16, textPanel);
		
		window.setVisible(true);
	}
	
	private void createWindow(){
		
		window = new JFrame("Unnamed");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	        
	    int x = (screenSize.width - weidthScreen) / 2;
	    int y = (screenSize.height - heigthScreen) / 2;
		
	    // use WindowAdapter to call exit method when close the frame
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				funcFile.exit();
			}});		
			
		window.setIconImage(new ImageIcon("Icon.png").getImage());
		window.setSize(weidthScreen, heigthScreen);
		window.setLocation(x, y);
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
	}
	
	private void createTextField() {
		textPanel = new JTextArea();
		
		textPanel.setMargin(new Insets(5, 5, 5, 5));
		textPanel.addKeyListener(kh);
		
		// add the undoManager to the Text panel
		textPanel.getDocument().addUndoableEditListener(
				new UndoableEditListener() {
					@Override
					public void undoableEditHappened(UndoableEditEvent e) {
						undoManager.addEdit(e.getEdit());
					}
				} );
		
		panel = new JScrollPane(textPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		window.add(panel);
	}
	
	
	
	private void createMenuBar() {
		menuBar = new JMenuBar();
		window.setJMenuBar(menuBar);
		
		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		menuEdit = new JMenu("Edit");
		menuBar.add(menuEdit);
		
		menuFormat = new JMenu("Format");
		menuBar.add(menuFormat);
		
	}
	
	private void createFileMenu() {
		fnew = new JMenuItem("New");
		menuFile.add(fnew);
		fnew.addActionListener(this);
		fnew.setActionCommand("New");
		
		fopen = new JMenuItem("Open");
		menuFile.add(fopen);
		fopen.addActionListener(this);
		fopen.setActionCommand("Open");
		
		fsave = new JMenuItem("Save");
		menuFile.add(fsave);
		fsave.addActionListener(this);
		fsave.setActionCommand("Save");
		
		fsaveAs = new JMenuItem("Save as");
		menuFile.add(fsaveAs);
		fsaveAs.addActionListener(this);
		fsaveAs.setActionCommand("Save as");
		
		separator = new JSeparator();
		menuFile.add(separator);
		
		fexit = new JMenuItem("Exit");
		menuFile.add(fexit);
		fexit.addActionListener(this);
		fexit.setActionCommand("Exit");
	}
	
	private void createEditMenu() {
		fcut = new JMenuItem("Cut");
		menuEdit.add(fcut);
		fcut.addActionListener(this);
		fcut.setActionCommand("Cut");
		
		fcopy = new JMenuItem("Copy");
		menuEdit.add(fcopy);
		fcopy.addActionListener(this);
		fcopy.setActionCommand("Copy");
		
		fpaste = new JMenuItem("Paste");
		menuEdit.add(fpaste);
		fpaste.addActionListener(this);
		fpaste.setActionCommand("Paste");
		
		fdel = new JMenuItem("Delete");
		menuEdit.add(fdel);
		fdel.addActionListener(this);
		fdel.setActionCommand("Delete");
		
		separator = new JSeparator();
		menuEdit.add(separator);
		
		fundo = new JMenuItem("Undo");
		menuEdit.add(fundo);
		fundo.addActionListener(this);
		fundo.setActionCommand("Undo");
		
		fredo = new JMenuItem("Redo");
		menuEdit.add(fredo);
		fredo.addActionListener(this);
		fredo.setActionCommand("Redo");
	}
	
	private void createFormatMenu() {
		fwrap = new JMenuItem("Word wrap: off");
		menuFormat.add(fwrap);
		fwrap.addActionListener(this);
		fwrap.setActionCommand("Wrap");
		
		menuFont = new JMenu("Font");
		menuFormat.add(menuFont);
		
		menuFontSize = new JMenu("Font size");
		menuFormat.add(menuFontSize);
		
		fFontArial = new JMenuItem("Arial");
		menuFont.add(fFontArial);
		fFontArial.addActionListener(this);
		fFontArial.setActionCommand("Arial");
		
		fFontCMSC = new JMenuItem("Comic Sans MS");
		menuFont.add(fFontCMSC);
		fFontCMSC.addActionListener(this);
		fFontCMSC.setActionCommand("Comic");
		
		fFontTNR = new JMenuItem("Times New Roman");
		menuFont.add(fFontTNR);
		fFontTNR.addActionListener(this);
		fFontTNR.setActionCommand("Times");
		
		FFontCalibri = new JMenuItem("Calibri");
		menuFont.add(FFontCalibri);
		FFontCalibri.addActionListener(this);
		FFontCalibri.setActionCommand("Calibri");
		
		fFontSize8 = new JMenuItem("8");
		menuFontSize.add(fFontSize8);
		fFontSize8.addActionListener(this);
		fFontSize8.setActionCommand("8");
		
		fFontSize12 = new JMenuItem("12");
		menuFontSize.add(fFontSize12);
		fFontSize12.addActionListener(this);
		fFontSize12.setActionCommand("12");
		
		fFontSize16 = new JMenuItem("16");
		menuFontSize.add(fFontSize16);
		fFontSize16.addActionListener(this);
		fFontSize16.setActionCommand("16");
		
		fFontSize20 = new JMenuItem("20");
		menuFontSize.add(fFontSize20);
		fFontSize20.addActionListener(this);
		fFontSize20.setActionCommand("20");
		
		fFontSize24 = new JMenuItem("24");
		menuFontSize.add(fFontSize24);
		fFontSize24.addActionListener(this);
		fFontSize24.setActionCommand("24");
		
		fFontSize28 = new JMenuItem("28");
		menuFontSize.add(fFontSize28);
		fFontSize28.addActionListener(this);
		fFontSize28.setActionCommand("28");
		
		fFontSize32 = new JMenuItem("32");
		menuFontSize.add(fFontSize32);
		fFontSize32.addActionListener(this);
		fFontSize32.setActionCommand("32");
		
		fFontSize36 = new JMenuItem("36");
		menuFontSize.add(fFontSize36);
		fFontSize36.addActionListener(this);
		fFontSize36.setActionCommand("36");
		
		fFontSize40 = new JMenuItem("40");
		menuFontSize.add(fFontSize40);
		fFontSize40.addActionListener(this);
		fFontSize40.setActionCommand("40");
		
		fFontSize44 = new JMenuItem("44");
		menuFontSize.add(fFontSize44);
		fFontSize44.addActionListener(this);
		fFontSize44.setActionCommand("44");
		
		fFontSize48 = new JMenuItem("48");
		menuFontSize.add(fFontSize48);
		fFontSize48.addActionListener(this);
		fFontSize48.setActionCommand("48");
		
		fFontSize52 = new JMenuItem("52");
		menuFontSize.add(fFontSize52);
		fFontSize52.addActionListener(this);
		fFontSize52.setActionCommand("52");
	}
	
	private void createHelpMenu() {
		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		
		itemHelp = new JMenuItem("About the program");
		menuHelp.add(itemHelp);
		itemHelp.addActionListener(this);
		itemHelp.setActionCommand("Help");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command =  e.getActionCommand();
		
		switch(command) {
		
			case "New": funcFile.newFile(); break;
			case "Open": funcFile.openFile(); break;
			case "Save": funcFile.saveFile(); break;
			case "Save as":funcFile.saveAsFile(); break;
			case "Exit":funcFile.exit(); break;
		
			case "Cut": textPanel.cut(); break;
			case "Copy": textPanel.copy(); break;
			case "Paste": textPanel.paste(); break;
			case "Delete": textPanel.replaceSelection(""); break;
			case "Undo": funcUndoRedo.undo(); break;
			case "Redo": funcUndoRedo.redo(); break;
		
			case "Wrap": funcForm.wordWrap(); break;
			case "Arial": funcForm.setFontSize(command, textPanel); break;
			case "Comic": funcForm.setFontSize(command, textPanel); break;
			case "Times": funcForm.setFontSize(command, textPanel); break;
			case "Calibri": funcForm.setFontSize(command, textPanel); break;
			case "8": funcForm.createFont(8, textPanel); break;
			case "12": funcForm.createFont(12, textPanel); break;
			case "16": funcForm.createFont(16, textPanel); break;
			case "20": funcForm.createFont(20, textPanel); break;
			case "24": funcForm.createFont(24, textPanel); break;
			case "28": funcForm.createFont(28, textPanel); break;
			case "32": funcForm.createFont(32, textPanel); break;
			case "36": funcForm.createFont(36, textPanel); break;
			case "40": funcForm.createFont(40, textPanel); break;
			case "44": funcForm.createFont(44, textPanel); break;
			case "48": funcForm.createFont(48, textPanel); break;
			case "52": funcForm.createFont(52, textPanel); break;
			
			case "Help": help.createHelpWindow(); break;
		}
	}
}
