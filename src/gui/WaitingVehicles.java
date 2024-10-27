package gui;

import javax.swing.*;

import projekat.Simulation;
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

public class WaitingVehicles extends JFrame implements Runnable, ActionListener{

	
	private static final Logger LOGGER = Logger.getLogger(WaitingVehicles.class.getName());
    public static FileHandler fileHandler;
    
    static {
        try {
            String path = "logs/WaitingVehicles.log"; 

            
            fileHandler = new FileHandler(path);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    
    private ArrayList<JButton> listOfVehicles =new ArrayList<JButton>();
	
	public WaitingVehicles() {
		
		this.setVisible(true);
		this.setSize(550, 450);
		this.setTitle("Vozila koja cekaju u redu");
		this.setLayout(new GridLayout(5,9));
		
		
		for(int i=0; i < 45; i++) {
			
			JButton vehicle = new JButton();
			listOfVehicles.add(vehicle);
			vehicle.addActionListener(this);
			this.add(vehicle);
			
			
		}
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 0; i < listOfVehicles.size(); i++)
		{
		if(e.getSource() == listOfVehicles.get(i))
		{
			int ind = i+5;
			if(Simulation.vozila.get(ind)!= null) {
				JScrollPane js = new JScrollPane(new JTextArea(Simulation.vozila.get(ind).toString()));
				js.setPreferredSize(new Dimension(500, 200));
				JOptionPane.showMessageDialog(null, js, "Podaci", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		}
		
	}
	
	
		
	public void updateListOfVehicles()
    {
      
		
		for (int i = 0; i < listOfVehicles.size(); i++) {
        
            int index = i + 5;
           
            if (index < Simulation.vozila.size()) {
                
                listOfVehicles.get(i).setText(Gui.getText(Simulation.vozila.get(index)));
                listOfVehicles.get(i).setBackground(Gui.setColor(Simulation.vozila.get(index)));
            } else {
                
                listOfVehicles.get(i).setText(" ");
                listOfVehicles.get(i).setBackground(null);
            }
		}
    }

	@Override
	public void run() {
		
		
		  try
		    {
		        Thread.sleep(100);
		       
		    }catch (InterruptedException e)
		    {
		    	Logger.getLogger(Vehicle.class.getName()).log(Level.WARNING,"Exception kod Waiting Vehicles");
		    }

		  
		  while (true) { 
		        if (Simulation.isRunning) {
		           updateListOfVehicles();
		        }

		       
		        while (!Simulation.isRunning) {
		            try {
		                Thread.sleep(100); 
		            } catch (InterruptedException e) {
		               LOGGER.log(Level.WARNING, "Exception kod zaustavljanja simulacije");
		            }

		           
		            if (Simulation.isRunning) {
		                break; 
		            }
		        }
		    }
		   
		}
	

		
	}
	



