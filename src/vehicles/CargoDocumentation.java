package vehicles;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class CargoDocumentation {
	
	
	private static final Logger LOGGER = Logger.getLogger(CargoDocumentation.class.getName());
    public static FileHandler fileHandler;
    
    static {
        try {
            String path = "logs/CargoDocumentation.log"; 

            
            fileHandler = new FileHandler(path);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	private String ID;
	
	public CargoDocumentation() {
		
		this.ID = UUID.randomUUID().toString();
	}
	
	public String getID() {
		
		return this.ID;
	}

}
