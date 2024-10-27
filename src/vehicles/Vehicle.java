package vehicles;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import passengers.Driver;
import passengers.Passenger;
import projekat.Simulation;
import terminals.CustomsTerminal;
import terminals.PoliceTerminal;
import terminals.Terminal;

public abstract class Vehicle extends Thread {
	
	
	private static final Logger LOGGER = Logger.getLogger(Vehicle.class.getName());
    public static FileHandler fileHandler;
    
    static {
        try {
            String path = "logs/Vehicle.log"; 

            
            fileHandler = new FileHandler(path);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	protected ArrayList<Passenger> passengers;
	protected int numOfPassengers;
	public Driver driver;
    private boolean passedPoliceControl;
    private boolean passedCustomsControl;
    private String ID;
	
	 public abstract int generateNumOfPassengers();
	 
	 public Vehicle( ) {
		 
		 this.driver = new Driver();
		 this.passengers = new ArrayList<>();
		 this.passedPoliceControl = false;
		 this.passedCustomsControl = false;
		 this.ID = UUID.randomUUID().toString();
	 }

	 public abstract long getProcessingTime();
	 
	 public synchronized boolean isFirst() {
		 
		 if (Simulation.vozila.get(0) == this) {
			 return true;
		 }
		 
		 else
			 return false;
	 }
	 
	
	 @Override
	    public void run() {
		 
		 while (!isFirst()) {
		        try {
		            
		            sleep(100);
		        } catch (InterruptedException e) {
		            LOGGER.log(Level.WARNING, "Exception, vozilo: " + this.toString());
		        }
		    }

		   
		    PoliceTerminal policeTerminal = findPoliceTerminal();
		    if (policeTerminal != null) {
		       
		    	deleteVehicle();
		    	
		        policeTerminal.processVehicle(this);

		        
		        if (this.passedPoliceControl) {
		        	
		        	CustomsTerminal ct = findCustomsTerminal();
		        	
		        	policeTerminal.setVehicle(null);
		            ((Terminal) policeTerminal).setFree(true);
		            ct.processVehicle(this);
		        	
		            
		        }

		       
		    }
		 
		 
	 }
		 
	 public Driver getDriver() {
		 return this.driver;
	 }
	 public ArrayList<Passenger> getPassengers() {
		 return this.passengers;
	 }
	 
	
	 
	 public int getNumOfPassengers() {
		 return this.numOfPassengers;
	 }
	 
	
	 public synchronized void removePassenger(Passenger p)
	    {
	        Iterator<Passenger> iterator = passengers.iterator();
	        while (iterator.hasNext())
	        {
	            Passenger passenger = iterator.next();
	            if (passenger.equals(p)) {
	                iterator.remove();
	                
	            }
	        }

	    }

	 public synchronized void deleteVehicle() {
		 Simulation.vozila.remove(this);
	 }
	 public boolean hasPassedPoliceControl() {
	        return this.passedPoliceControl;
	    }

	 public void setPassedPoliceControl(boolean passedPoliceControl) {
	        this.passedPoliceControl = passedPoliceControl;
	    }
	 
	 
	 public boolean hasPassedCustomsControl() {
	        return this.passedCustomsControl;
	    }

	 
	 public String getID() {
		 return this.ID;
	 }
	 public void setPassedCustomsControl(boolean passedCustomsControl) {
	        this.passedCustomsControl = passedCustomsControl;
	    }
	 
	 
	 @Override
	 public abstract String toString();
	 public abstract PoliceTerminal findPoliceTerminal();
	 
	 public abstract CustomsTerminal findCustomsTerminal();
}
