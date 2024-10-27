package terminals;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import gui.IncidentsGui;
import passengers.Passenger;
import projekat.Simulation;
import vehicles.Bus;
import vehicles.Car;
import vehicles.CargoDocumentation;
import vehicles.Truck;
import vehicles.Vehicle;

public class CustomsTerminal extends Terminal {
	
	
	private static final Logger LOGGER = Logger.getLogger(CustomsTerminal.class.getName());
    public static FileHandler fileHandler;
    
    static {
        try {
            String path = "logs/CustomsTerminal.log"; 

            
            fileHandler = new FileHandler(path);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public static String filePath="reports/problemsCustoms.txt";


public CustomsTerminal() {
		
	super();
	
	}

public void processVehicle(Vehicle vehicle) {
	
	this.isFree = false;
	this.vehicle = vehicle;
	ArrayList<Passenger> passengers = this.vehicle.getPassengers();
	
	if(vehicle instanceof Car) {
		
	
		
		 try
	        {
	            Thread.sleep(2000);
	        } catch (InterruptedException e)
	        {
	        	Logger.getLogger(Car.class.getName()).log(Level.WARNING,"Problem kod obrade automobila na carini");

	        }
		 vehicle.setPassedCustomsControl(true);
		 System.out.println("Automobil prosao carinsku obradu" + this.vehicle.getClass().getName() + this.toString());
		 
		 this.isFree = true;
		 this.vehicle = null;
	}
	
	else if(vehicle instanceof Bus) {
		
		for(int i=1; i< passengers.size(); i++ ) {
			Passenger pass = passengers.get(i);
			if(pass.hasSuitcase()) {
				if(pass.getSuitcase().hasIllegalThings()) {
					this.vehicle.removePassenger(pass);
					
					System.out.println("Putnik izbacen na carini zbog kofera, ID " + pass.getIdentificationDocument().getID());
					 writeToFile(vehicle, "Putnik izbacen iz autobusa zbog nedozvoljenih stvari u koferu, ID putnika:" + pass.getIdentificationDocument().getID()+ this.toString());
					 IncidentsGui.writeToPassengersProblems("Putnik: " + pass.toString()+ " ID: " + pass.getIdentificationDocument().getID() + " izbacen na carini zbog kofera iz vozila: " + this.vehicle.toString());
						
				}
			}
			
			
			
			 try
	            {
	                Thread.sleep(100);
	            }catch (InterruptedException e)
	            {
	            	Logger.getLogger(Bus.class.getName()).log(Level.WARNING,"Problem kod obrade putnika na carini");
	            }
			 
			 
		}
		
		
	
		System.out.println("Vozilo proslo carinsku obradu" + this.vehicle.getClass().getName() + this.toString());
		this.isFree = true;
		vehicle.setPassedCustomsControl(true);
		this.vehicle = null;
	
	}
	
	else if(vehicle instanceof Truck) {
		
		
		Truck truck = (Truck) vehicle;
		
		
		
		try
        {
            Thread.sleep(500);
        }catch (InterruptedException e)
        {
        	Logger.getLogger(Truck.class.getName()).log(Level.WARNING,"Problem kod carinske obrade kamiona");
            
        }
		
		if(truck.getCargo().hasDocumentation) {
			
			truck.getCargo().setCargoDocumentation(new CargoDocumentation());
			
			
		}
		
		if(truck.getCargo().customsControl()) {
			
			vehicle.setPassedCustomsControl(true);
			System.out.println("Vozilo proslo carinsku obradu " + this.vehicle.getClass().getName() + " " + this.toString());
		
			
		}
		
		else {
			
			vehicle.setPassedCustomsControl(false);
			System.out.println("Kamion nije prosao carinsku obradu zbog preopterecnosti" + this.vehicle.getClass().getName() + this.toString());
		    writeToFile(vehicle, "Kamion nije prosao carinsku obradu zbog preopterecenosti" + this.vehicle.getClass().getName() + this.toString());
		    IncidentsGui.writeToKickedOut("Vozilo: " + this.vehicle.toString()+ " izbaceno na carini zbog nedozvoljene tezine");
		}
	
		this.isFree = true;
		this.vehicle = null;
		
	}
	
	
}

public void writeToFile(Vehicle v, String message) {
	 try (PrintWriter out = new PrintWriter(new FileWriter(filePath, true))) {
         out.println("Vozilo: " + v + " - " + message);
         
     } catch (IOException e) {
    	 LOGGER.log(Level.WARNING,"Problem kod upisa putnika u datoteku");
     }
}
@Override
public String toString() {
	if(this == Simulation.ct1)
	{
		return "ct1";
		
	}
	
	else if(this == Simulation.ct2) {
		return "ct2";
	}
	
	
	else
		return "null";
}

public Vehicle getVehicle() {
	return this.vehicle;
}


}
