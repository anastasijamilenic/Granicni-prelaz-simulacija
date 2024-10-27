package gui;


import javax.swing.*;

import projekat.Simulation;
import vehicles.Bus;
import vehicles.Car;
import vehicles.Truck;
import vehicles.Vehicle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gui extends JFrame implements Runnable, ActionListener {
	
	private static final Logger LOGGER = Logger.getLogger(Gui.class.getName());
    public static FileHandler fileHandler;
    
    static {
        try {
            String path = "logs/Gui.log"; 

            
            fileHandler = new FileHandler(path);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
    
    private JButton firstVehicle = new JButton("Prvo vozilo");
    private JButton secondVehicle = new JButton("Drugo vozilo");
    private JButton thirdVehicle = new JButton("Trece vozilo");
    private JButton fourthVehicle = new JButton("Cetvrto vozilo");
    private JButton fifthVehicle = new JButton("Peto vozilo");
    
    private JButton pt1 = new JButton("pt1");
    private JButton pt2 = new JButton("pt2");
    private JButton pt3 = new JButton("pt3");
    private JButton ct1 = new JButton("ct1");
    private JButton ct2 = new JButton("ct2");
   
    
    private JPanel vehiclePanel=new JPanel();
   
    
    private ArrayList<JButton> firstFiveButtons =new ArrayList<JButton>();
    private ArrayList<JButton> policeButtons =new ArrayList<JButton>();
    private ArrayList<JButton> customsButtons =new ArrayList<JButton>();
    
    private JButton otherVehicles = new JButton();
    private JButton vehiclesWithProblems =new JButton();
    
    private JButton startSimulation = new JButton();
    private JButton stopSimulation =new JButton();
 
	@Override
	public void run() {
	
		  try
		    {
		        Thread.sleep(300);
		    }catch (InterruptedException e)
		    {
		        e.printStackTrace();

		    }

		  
		  while (true) { 
		        while (Simulation.isRunning) {
		            updateFirstFive();
		            updatePoliceTerminal();
		            updateCustomsTerminal();
		        }

		        
		        while (!Simulation.isRunning) {
		            try {
		                Thread.sleep(100); 
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }

		            
		            if (Simulation.isRunning) {
		                break; 
		            }
		        }
		    }
		}
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 
		for(int i = 0; i < firstFiveButtons.size(); i++)
		{
		if(e.getSource() == firstFiveButtons.get(i))
		{
			if(Simulation.vozila.get(i)!= null) {
				JScrollPane js = new JScrollPane(new JTextArea(Simulation.vozila.get(i).toString()));
				js.setPreferredSize(new Dimension(500, 200));
				JOptionPane.showMessageDialog(null, js, "Podaci", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		}
		
		
		if(e.getSource() == policeButtons.get(0))
		{
			
			if(Simulation.pt1.getVehicle()!= null) {
				JScrollPane js = new JScrollPane(new JTextArea(Simulation.pt1.getVehicle().toString()));
				js.setPreferredSize(new Dimension(500, 200));
				JOptionPane.showMessageDialog(null, js, "Podaci", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if(e.getSource() == policeButtons.get(1))
		{
			
			if(Simulation.pt2.getVehicle()!= null) {
				JScrollPane js = new JScrollPane(new JTextArea(Simulation.pt2.getVehicle().toString()));
				js.setPreferredSize(new Dimension(500, 200));
				JOptionPane.showMessageDialog(null, js, "Podaci", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if(e.getSource() == policeButtons.get(2))
		{
			
			if(Simulation.pt3.getVehicle()!= null) {
				JScrollPane js = new JScrollPane(new JTextArea(Simulation.pt3.getVehicle().toString()));
				js.setPreferredSize(new Dimension(500, 200));
				JOptionPane.showMessageDialog(null, js, "Podaci", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if(e.getSource() == customsButtons.get(0))
		{
			
			if(Simulation.ct1.getVehicle()!= null) {
				JScrollPane js = new JScrollPane(new JTextArea(Simulation.ct1.getVehicle().toString()));
				js.setPreferredSize(new Dimension(500, 200));
				JOptionPane.showMessageDialog(null, js, "Podaci", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if(e.getSource() == customsButtons.get(1))
		{
			
			if(Simulation.pt3.getVehicle()!= null) {
				JScrollPane js = new JScrollPane(new JTextArea(Simulation.ct2.getVehicle().toString()));
				js.setPreferredSize(new Dimension(500, 200));
				JOptionPane.showMessageDialog(null, js, "Podaci", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if (e.getSource() == startSimulation) {
			Simulation.isRunning = true;
		}
		
		 
		if (e.getSource() == stopSimulation) {
			Simulation.isRunning = false;	
			}
	}
	
	public static Color setColor(Vehicle v) {
		
		if (v instanceof Car) {
			return new Color(255, 102, 102);
		}
		
		else if (v instanceof Bus) {
			return new Color(0, 128, 255);
		}
		
		else if (v instanceof Truck) {
			
			return new Color(153, 0, 76);
		}
		
		else 
		{
		
		return Color.WHITE;
		}

	}
	
	
	
	private void updateFirstFive() {
       
		for (int i = 0; i < firstFiveButtons.size(); i++)
        {
            if (i < (Simulation.vozila.size()-1))
            {
                firstFiveButtons.get(i).setText(getText(Simulation.vozila.get(i)));
                firstFiveButtons.get(i).setBackground(setColor(Simulation.vozila.get(i)));
            } else {
                firstFiveButtons.get(i).setText(" ");
                firstFiveButtons.get(i).setBackground(null);
            }
        }
    }
	
	
	public void updatePoliceTerminal() {
		
		policeButtons.get(0).setText("PT1" +  getText(Simulation.pt1.getVehicle()));
		policeButtons.get(0).setBackground(setColor(Simulation.pt1.getVehicle()));
		
		policeButtons.get(1).setText("PT2" +  getText(Simulation.pt2.getVehicle()));
		policeButtons.get(1).setBackground(setColor(Simulation.pt2.getVehicle()));
		
		policeButtons.get(2).setText("PT3" +  getText(Simulation.pt3.getVehicle()));
		policeButtons.get(2).setBackground(setColor(Simulation.pt3.getVehicle()));
	}
	
	
public void updateCustomsTerminal() {
		
		customsButtons.get(0).setText("CT1" +  getText(Simulation.ct1.getVehicle()));
		customsButtons.get(0).setBackground(setColor(Simulation.ct1.getVehicle()));
		
		customsButtons.get(1).setText("CT2" +  getText(Simulation.ct2.getVehicle()));
		customsButtons.get(1).setBackground(setColor(Simulation.ct2.getVehicle()));
		
		
		
		
	}
	

public static String getText(Vehicle v) {
	if(v instanceof Car)
    {
        return "Car";
    }
    else if(v instanceof Bus)
    {
        return "Bus";
    }
    else if(v instanceof Truck)
    {
        return "Truck";
    }
    return "";
}

	public Gui() {
		setLayout(null);
	
	   
	    
	    vehiclePanel.setLayout(null);
	    vehiclePanel.setBounds(0, 0, 850, 600);
	    add(vehiclePanel);
	
	    
	    int buttonWidth = 100;
	    int buttonHeight = 45;
	    
	    
	   
	    
	    firstVehicle.setBounds(375, 100 + 1 * 40, buttonWidth, buttonHeight);
	    vehiclePanel.add(firstVehicle);
	    firstVehicle.addActionListener(this);
	    firstFiveButtons.add(firstVehicle);
	    
	    
	    
	    secondVehicle.setBounds(375, 100 + 2 * 40, buttonWidth, buttonHeight);
	    vehiclePanel.add(secondVehicle);
	    secondVehicle.addActionListener(this);
	    firstFiveButtons.add(secondVehicle);
	    
	    thirdVehicle.setBounds(375, 100 + 3 * 40, buttonWidth, buttonHeight);
	    vehiclePanel.add(thirdVehicle);
	    thirdVehicle.addActionListener(this);
	    firstFiveButtons.add(thirdVehicle);
	    
	    fourthVehicle.setBounds(375, 100 + 4 * 40, buttonWidth, buttonHeight);
	    vehiclePanel.add(fourthVehicle);
	    fourthVehicle.addActionListener(this);
	    firstFiveButtons.add(fourthVehicle);
	    
	    fifthVehicle.setBounds(375, 100 + 5 * 40, buttonWidth, buttonHeight);
	    vehiclePanel.add(fifthVehicle);
	    fifthVehicle.addActionListener(this);
	    firstFiveButtons.add(fifthVehicle);
	    
	    int terminalButtonWidth = 100;
	    int terminalButtonHeight = 45;
	
	    vehiclePanel.setBackground(new Color(153, 255, 204));
	
	    pt1.setBounds(270, 60, terminalButtonWidth, terminalButtonHeight);
	    pt2.setBounds(375, 60, terminalButtonWidth, buttonHeight);
	    pt3.setBounds(480, 60, terminalButtonWidth, terminalButtonHeight);
	
	    vehiclePanel.add(pt1);
	    vehiclePanel.add(pt2);
	    vehiclePanel.add(pt3);
	    
	    policeButtons.add(pt1);
	    policeButtons.add(pt2);
	    policeButtons.add(pt3);
	    
	    
	    pt1.addActionListener(this);
	    pt2.addActionListener(this);
	    pt3.addActionListener(this);
	    
	    ct1.setBounds(270, 10, terminalButtonWidth, terminalButtonHeight);
	    ct2.setBounds(480, 10, terminalButtonWidth, buttonHeight);
	    
	    vehiclePanel.add(ct1);
	    vehiclePanel.add(ct2);
	    
	    
	    customsButtons.add(ct1);
	    customsButtons.add(ct2);
	    
	    
	    ct1.addActionListener(this);
	    ct2.addActionListener(this);
	    
	    otherVehicles.setBounds(150, 350, 70, 70);
	    otherVehicles.setText("Red");
	    otherVehicles.setBackground(new Color(102, 178, 255));
	    otherVehicles.addActionListener(e->{WaitingVehicles wv=new WaitingVehicles();
	    Thread otherThread=new Thread(wv);
	    otherThread.start();});;
	    vehiclePanel.add(otherVehicles);
	    
	    
	    startSimulation.setBounds(150, 150, 70, 70);
	    startSimulation.setText("Start");
	    startSimulation.setBackground(new Color(255, 255, 153));
	    startSimulation.addActionListener(this);
	    vehiclePanel.add(startSimulation);
	    
	    
	    stopSimulation.setBounds(150, 250, 70, 70);
	    stopSimulation.setText("Stop");
	    stopSimulation.setBackground(new Color(255, 153, 255));
	    stopSimulation.addActionListener(this);
	    vehiclePanel.add(stopSimulation);
	    
	    vehiclesWithProblems.setBounds(150, 450, 70, 70);
	    vehiclesWithProblems.setText("Problemi");
	    vehiclesWithProblems.setBackground(new Color(255, 153,153));
	    vehiclesWithProblems.addActionListener(e->{new IncidentsGui();});
	    vehiclePanel.add(vehiclesWithProblems);
	    
	    
	
	    
	    setTitle("Simulacija");
	    setSize(850, 600);
	    setLocationRelativeTo(null);
	    
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.add(vehiclePanel);
	   
	    setVisible(true);
	    
	   
	    
	
	}
}
