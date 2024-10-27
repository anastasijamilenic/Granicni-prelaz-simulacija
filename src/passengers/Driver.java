package passengers;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Driver extends Passenger {
	
	private static final Logger LOGGER = Logger.getLogger(Driver.class.getName());
    public static FileHandler fileHandler;
    
    static {
        try {
            String path = "logs/Driver.log"; 

            
            fileHandler = new FileHandler(path);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public Driver() {
		super(0);
	}
}
