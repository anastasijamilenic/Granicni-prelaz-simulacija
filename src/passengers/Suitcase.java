package passengers;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
public class Suitcase implements Serializable{
	
	private static final Logger LOGGER = Logger.getLogger(Suitcase.class.getName());
    public static FileHandler fileHandler;
    
    static {
        try {
            String path = "logs/Suitcase.log"; 

            
            fileHandler = new FileHandler(path);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	private boolean illegalThings;

    public Suitcase() {
        this.illegalThings = generateIllegalThings();
    }

    private boolean generateIllegalThings() {
        
        return new Random().nextDouble() < 0.1; 
    }


    public boolean hasIllegalThings() {
        return illegalThings;
    }
}


