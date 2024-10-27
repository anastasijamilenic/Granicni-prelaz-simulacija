package projekat;


import terminals.CustomsTerminal;
import terminals.PoliceTerminal;
import vehicles.Bus;
import vehicles.Car;
import vehicles.Truck;
import vehicles.Vehicle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Simulation  {
	
	private static final Logger LOGGER = Logger.getLogger(Simulation.class.getName());
    public static FileHandler fileHandler;
    
    static {
        try {
            String path = "logs/Simulation.log"; 

            
            fileHandler = new FileHandler(path);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Vehicle> vozila;
   
    public static boolean isRunning;

   

    public static final PoliceTerminal pt1 = new PoliceTerminal();
    public static final PoliceTerminal pt2 = new PoliceTerminal();
    public static final PoliceTerminal pt3 = new PoliceTerminal(); //za kamione
    public static final CustomsTerminal ct1 = new CustomsTerminal();
    public static final CustomsTerminal ct2 = new CustomsTerminal();

   

    public Simulation() {
       
        this.isRunning = true;

        vozila = Collections.synchronizedList(new ArrayList<Vehicle>());

        for (int i = 0; i < 5; i++) {
            vozila.add(new Bus());
        }

        for (int i = 0; i < 10; i++) {
            vozila.add(new Truck());
        }

        for (int i = 0; i < 35; i++) {
            vozila.add(new Car());
        }

        Collections.shuffle(vozila);
        
        List<Vehicle> copyVehicles = Collections.synchronizedList(new ArrayList<>(vozila));
       
         
         for (Vehicle v : copyVehicles)
         {
             v.start();
             System.out.println("Zapoceto vozilo");
             
             
         }
         
         

         try{
             for(Vehicle v : copyVehicles){
                 v.join();
             }
         }
         catch(InterruptedException e){
        	 Logger.getLogger(Vehicle.class.getName()).log(Level.WARNING,"Problem sa nitima u joinu");
             
         }

         
         
      

         System.out.println("Kraj simulacije!");

     }

     
   
}
