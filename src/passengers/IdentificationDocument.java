package passengers;

import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class IdentificationDocument implements Serializable {
	
	private static final Logger LOGGER = Logger.getLogger(IdentificationDocument.class.getName());
    public static FileHandler fileHandler;
    
    static {
        try {
            String path = "logs/IdentificationDocument.log"; 

            
            fileHandler = new FileHandler(path);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private String ID;
	
	public IdentificationDocument() {
		
		this.ID = UUID.randomUUID().toString();
		
	}
	
	public String getID( ) {
		return this.ID;
	}

	public boolean isCorrect( ) {
		return Math.random() < 0.97;
	}
	

	
}
