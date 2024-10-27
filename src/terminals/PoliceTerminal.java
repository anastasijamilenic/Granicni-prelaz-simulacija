package terminals;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import gui.IncidentsGui;

import java.io.*;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import passengers.Passenger;
import projekat.Simulation;
import vehicles.Vehicle;

public class PoliceTerminal extends Terminal {

	private static final Logger LOGGER = Logger.getLogger(PoliceTerminal.class.getName());
    public static FileHandler fileHandler;
    
    static {
        try {
            String path = "logs/PoliceTerminal.log"; 

            
            fileHandler = new FileHandler(path);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private ArrayList<Passenger> punishedPassengers = new ArrayList<Passenger>();
	public static String fileName="reports/policeTerminal.bin";
	
	

   

	
	
	public PoliceTerminal() {
		
		super();
	}

	
	 
	 
	protected boolean controlPassenger(Passenger passenger) {
		 try {
	            
	            Thread.sleep(this.vehicle.getProcessingTime());
	        }
		 
		 catch (InterruptedException e) {
	            
			 Logger.getLogger(Vehicle.class.getName()).log(Level.WARNING,"PoliceTerminal: Problem kod kontrole putnika");
	        }
		return passenger.getIdentificationDocument().isCorrect();
				
				
	}
	
	public void setVehicle(Vehicle v) {
		this.vehicle = v;
	}
	
	public void addPunishedPassenger(Passenger passenger) {
        punishedPassengers.add(passenger);
    }
	
	public void serializePunishedPassengers(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(punishedPassengers);
            System.out.println("Punished people list serialized successfully.");
        } catch (IOException e) {
        	LOGGER.log(Level.WARNING,"PoliceTerminal: Problem sa serijalizacijom kaznjenih putnika");
        }
    }
	
    public  void processVehicle(Vehicle vehicle) {
		
		this.isFree = false;
		
		this.vehicle = vehicle;
		ArrayList<Passenger> passengers = this.vehicle.getPassengers();
		
	
		if(controlPassenger(this.vehicle.getPassengers().get(0)))
		{
			
			
			this.vehicle.setPassedPoliceControl(true);
			 
			
	     
			for(int i=1; i< passengers.size(); i++) {
				Passenger pass = passengers.get(i);
				if(!controlPassenger(pass)) {
	        		this.vehicle.removePassenger(pass);
	        		addPunishedPassenger(pass);
	        		serializePunishedPassengers(fileName);
	        		System.out.println("Putnik izbacen, ID " + pass.getIdentificationDocument());
	        		IncidentsGui.writeToPassengersProblems("Putnik: " + pass.toString()+ " ID: " + pass.getIdentificationDocument().getID() + " izbacen zbog dokumenata na policijskom terminalu iz vozila: " + this.vehicle.toString());
			
		}
				
	       
	      
		}
			
			System.out.println("Vozilo proslo obradu " + this.vehicle.getClass().getName() + " " + this.toString());
			
			
	
			}
		
		
		else
		{
		
			System.out.println("Vozac neispravan");
			IncidentsGui.writeToKickedOut("Vozilo: " + this.vehicle.toString() + " izbaceno sa policijskog terminala zbog problema sa vozacem");
			
			this.vehicle.setPassedPoliceControl(false);
			
			addPunishedPassenger(this.vehicle.getPassengers().get(0));
			serializePunishedPassengers(fileName);
			this.vehicle.removePassenger(this.vehicle.getPassengers().get(0));
			
			System.out.println("Vozac izbacen, a broj putnika: " + this.vehicle.getPassengers().size());
			
			
			while(passengers.size() > 0)
			
			{
				Passenger pass = passengers.get(0);
				this.vehicle.removePassenger(pass);
			
			
	        		
	        		System.out.println("Putnik izbacen zbog vozaca, ID " + pass.getIdentificationDocument());
			
			}
			
			
			
			this.isFree = true;
			this.setVehicle(null);
	
	}
		
		
	
	
		}
    
    public Vehicle getVehicle() {
    	return this.vehicle;
    }
    
    @Override
    public String toString() {
    	if(this == Simulation.pt1)
    	{
    		return "pt1";
    		
    	}
    	
    	else if(this == Simulation.pt2) {
    		return "pt2";
    	}
    	
    	else if(this == Simulation.pt3) {
    		return "pt3";
    	}
    	
    	else
    		return "null";
    }
    	  }
    
