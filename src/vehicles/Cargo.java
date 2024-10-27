package vehicles;

import java.io.IOException;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Cargo {
	
	private static final Logger LOGGER = Logger.getLogger(Cargo.class.getName());
    public static FileHandler fileHandler;
    
    static {
        try {
            String path = "logs/Cargo.log"; 

            
            fileHandler = new FileHandler(path);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	private double declaredWeight;
    private double realWeight;
    public boolean hasDocumentation;
    private CargoDocumentation documentation = null;

    public Cargo() {
        this.declaredWeight = generatedeclaredWeight();
        this.realWeight = generaterealWeight();
        this.hasDocumentation = new Random().nextDouble() < 0.5; 
    }

    private double generatedeclaredWeight() {
        
        return new Random().nextDouble() * (5000 - 1000) + 1000;
    }

    private double generaterealWeight() {
        Random rand = new Random();
        if (rand.nextDouble() < 0.2) { 
            return declaredWeight * 1.3; 
        } else {
            return declaredWeight; // Inače, stvarna masa je ista kao deklarisana
        }
    }

   
    public double getdeclaredWeight() {
        return declaredWeight;
    }

    
    public boolean hasDocumentation() {
    	return this.hasDocumentation;
    }
    public double getrealWeight() {
        return realWeight;
    }
    
    public CargoDocumentation getDocumentation() {
    	return this.documentation;
    }
    
    public void setCargoDocumentation(CargoDocumentation documentation)
    {
    	this.documentation = documentation;
    }
    public boolean customsControl() {
    	return (this.realWeight <= this.declaredWeight);
    }

}


