package terminals;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import vehicles.Vehicle;

public class Terminal {
	
	
	private static final Logger LOGGER = Logger.getLogger(Terminal.class.getName());
    public static FileHandler fileHandler;
    
    static {
        try {
            String path = "logs/Terminal.log"; 

            
            fileHandler = new FileHandler(path);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public boolean isFree;
	//protected TerminalType type;
	protected Vehicle vehicle;
	public boolean isWorking;
	
	public Terminal() {
		
		this.isWorking = true;
		this.isFree = true;
		
	}
	

	
	public void setFree(boolean value) {
		this.isFree = value;
	}
	
	public  synchronized boolean isFree() {
		return this.isFree;
	}
	
	public  synchronized boolean isWorking() {
		return this.isWorking;
	}

	public Vehicle getVehicle()
	{
		return this.vehicle;
	}
	
	

}
