package vehicles;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import passengers.Passenger;
import projekat.Simulation;
import terminals.CustomsTerminal;
import terminals.PoliceTerminal;

import java.io.IOException;
import java.util.ArrayList;

public class Car extends Vehicle {
	
	private static final Logger LOGGER = Logger.getLogger(Car.class.getName());
    public static FileHandler fileHandler;
    
    static {
        try {
            String path = "logs/Car.log"; 

            
            fileHandler = new FileHandler(path);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public Car() {
		super();
		this.numOfPassengers = generateNumOfPassengers();
		this.passengers.add(this.driver);
		
		for(int i = 1; i < numOfPassengers; i++) {
			
			Passenger pass = new Passenger(i);
			this.passengers.add(pass);
			
		}
		
	}
	
	
	public int generateNumOfPassengers(){
        return new Random().nextInt(5) + 1;
    }
	
 
	public  PoliceTerminal findPoliceTerminal() {
	   
		 while(true) {
			   
			   if(Simulation.pt1.isFree() && Simulation.pt1.isWorking) {
				   return Simulation.pt1;
			   }
			   
			   else if(Simulation.pt2.isFree && Simulation.pt2.isWorking) {
				   return Simulation.pt2;
			   }
			   else {
			   try {
		           Thread.sleep(200);
		       } catch (InterruptedException e) {
		    	   LOGGER.log(Level.WARNING, "Exception, vozilo: " + this.toString());
			   }
			   }
			   }
	}

   
 public synchronized CustomsTerminal findCustomsTerminal() {
	   
	   while(true) {
	   
	   if(Simulation.ct1.isFree && Simulation.ct1.isWorking && Simulation.isRunning) {
		   return Simulation.ct1;
	   }
	   
	   else {
	   try {
           sleep(200);
       } catch (InterruptedException e) {
    	   LOGGER.log(Level.WARNING, "Exception, vozilo: " + this.toString());
	   }
	   }
	   }
	
        
    } 
 
 @Override
 public String toString() {
	 
	 String s= "";
	 for(Passenger pass: passengers) {
		 
		 s+=pass.toString();
		 s+=" ";
	 }
	 return "Car, ID: " + this.getID() + " Putnici: " + s;
 }
	@Override
    public long getProcessingTime() {
       
        return 500;
    }
}
	
	

