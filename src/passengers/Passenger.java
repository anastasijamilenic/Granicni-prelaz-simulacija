package passengers;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Passenger implements Serializable {
	private static final Logger LOGGER = Logger.getLogger(Passenger.class.getName());
    public static FileHandler fileHandler;
    
    static {
        try {
            String path = "logs/Passenger.log"; 

            
            fileHandler = new FileHandler(path);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	private IdentificationDocument ID;
	private Suitcase suitcase = null;
	private boolean hasSuitcase = false;

	private String name;
	
	public Passenger(int i) {
		this.ID = new IdentificationDocument();
		this.name = "Putnik" + i;
		
	
	}
	
	public IdentificationDocument getIdentificationDocument( ) {
		
		return this.ID;
	}
	
	public void setHasSuitcase(boolean value) {
		this.hasSuitcase = value;
		if(value) {
			this.suitcase = new Suitcase();
		}
	}
	
	@Override
	public String toString() {
		return this.name + this.getIdentificationDocument().getID();
	}
	public Suitcase getSuitcase() {
		return this.suitcase;
	}
	
	public void setSuitcase(Suitcase suitcase) {
		this.suitcase = suitcase;
	}
	
	public boolean hasSuitcase() {
		return this.hasSuitcase;
	}
	
	
	
}
