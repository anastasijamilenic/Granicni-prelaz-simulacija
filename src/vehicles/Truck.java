package vehicles;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import passengers.Passenger;
import projekat.Simulation;
import terminals.CustomsTerminal;
import terminals.PoliceTerminal;

public class Truck extends Vehicle {
	
	
	private static final Logger LOGGER = Logger.getLogger(Truck.class.getName());
    public static FileHandler fileHandler;
    
    static {
        try {
            String path = "logs/Truck.log"; 

            
            fileHandler = new FileHandler(path);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	private Cargo cargo;
	
	
	public Truck() {
		super();
		this.numOfPassengers = generateNumOfPassengers();
		this.passengers.add(this.driver);
		this.cargo = new Cargo();
		
		for(int i = 1; i < numOfPassengers; i++) {
			
			Passenger pass = new Passenger(i);
			this.passengers.add(pass);
			
		}
		
		
	}
	
   public synchronized PoliceTerminal findPoliceTerminal() {
	   
	   while(true) {
		   
		   if(Simulation.pt3.isFree && Simulation.pt3.isWorking) {
			   return Simulation.pt3;
		   }
		   
		   
		   try {
	           sleep(200);
	       } catch (InterruptedException e) {
	    	   LOGGER.log(Level.WARNING, "Exception, vozilo: " + this.toString());
		   }
		   }
		
	        
	
    
}
   
public synchronized CustomsTerminal findCustomsTerminal() {
	   
	   while(true) {
		   
		   if(Simulation.ct2.isFree && Simulation.ct2.isWorking && Simulation.isRunning) {
			   return Simulation.ct2;
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

	
        
    public Cargo getCargo() {
    	return this.cargo;
    }


	public int generateNumOfPassengers(){
        return new Random().nextInt(3) + 1;
    }
	 @Override
	 public String toString() {
		 String s= "";
		 for(Passenger pass: passengers) {
			 
			 s+=pass.toString();
			 s+=" ";
		 }
		 return "Truck, ID: " + this.getID() + " Putnici: " + s;
	 }
	
	@Override
    public long getProcessingTime() {
        
        return 500;
    }
}
