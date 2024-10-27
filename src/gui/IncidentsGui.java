package gui;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import javax.swing.*;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class IncidentsGui extends JFrame {
	
	private static final Logger LOGGER = Logger.getLogger(IncidentsGui.class.getName());
    public static FileHandler fileHandler;
    
    static {
        try {
            String path = "logs/IncidentsGui.log"; 

            
            fileHandler = new FileHandler(path);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static JTextArea passengersProblems = new JTextArea();
    public static JTextArea kickedOut = new JTextArea();
    
    public IncidentsGui() {
		
	
	this.setSize(550, 450);
	this.setLayout(null);
	this.setTitle("Incidenti ");
	
	
	passengersProblems.setEditable(false);
	JScrollPane ps = new JScrollPane(passengersProblems);
	JLabel pslabel = new JLabel("Problemi sa putnicima: ");
	pslabel.setBounds(10, 10, 400, 25);
	passengersProblems.setBounds(10, 60, 425, 150);
    ps.setBounds(10, 60, 425, 150);

    
	
	
	kickedOut.setEditable(false);
	JScrollPane kickedOutpane = new JScrollPane(kickedOut);
	JLabel kslabel = new JLabel("Izbacena vozila: ");
	kslabel.setBounds(10, 210, 400, 25);
	kickedOut.setBounds(10, 260, 425, 150);
    kickedOutpane.setBounds(10, 260, 425, 150);

	
	JPanel contentPane = new JPanel();
    contentPane.setBackground(new Color(255, 204, 255));
    contentPane.setLayout(null);
    contentPane.setBounds(0, 0, 550, 450); 
    this.setContentPane(contentPane); 
    
    add(pslabel);
	add(ps);
    
    add(kslabel);
	add(kickedOutpane);
    
	
	this.setVisible(true);
    }
    
    
    public static void writeToPassengersProblems(String text) {
    	passengersProblems.append(text + "\n");
    }
    
    public static void writeToKickedOut(String text) {
    	kickedOut.append(text + "\n");
    }

}
