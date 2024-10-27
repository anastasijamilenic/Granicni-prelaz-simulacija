
package projekat;  

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileWatcherService implements Runnable {

	
	private static final Logger LOGGER = Logger.getLogger(FileWatcherService.class.getName());
    public static FileHandler fileHandler;
    
    static {
        try {
            String path = "logs/FileWatcherService.log"; 

            
            fileHandler = new FileHandler(path);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public Path filePath;

    public FileWatcherService(String filePath) {
        this.filePath = Paths.get(filePath);
    }
    
    public static void watchTerminalStatus(Path filePath) throws IOException, InterruptedException {
       
        WatchService watchService = FileSystems.getDefault().newWatchService();

        
        filePath.getParent().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

       
        while (true) {
            WatchKey key = watchService.take();
            handleStatusChange(key, filePath);
            key.reset();
        }
    }

    private static void handleStatusChange(WatchKey key, Path filePath) throws IOException {
        for (WatchEvent<?> event : key.pollEvents()) {
            if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                Path fileName = (Path) event.context();
                if (fileName.toString().equals(filePath.getFileName().toString())) {
                    // Detektovana izmjena statusa terminala
                    System.out.println("Promjene u stanju terminala:");
                    updateTerminalStatus(filePath);
                }
            }
        }
    }

    private static void updateTerminalStatus(Path filePath) throws IOException {
        
        List<String> lines = Files.readAllLines(filePath);

       
        for (String line : lines) {
            String[] parts = line.split(":");
            String terminalName = parts[0];
            boolean isWorking = parts[1].trim().equals("working");


            
            

            System.out.println(terminalName + ": " + (isWorking ? "working" : "blocked"));
            
            
              if(terminalName.equals("pt1"))
            {
            	
            	Simulation.pt1.isWorking = isWorking;
            }
              

              else  if(terminalName.equals("pt2"))
            {
            	
            	Simulation.pt2.isWorking = isWorking;
            }
              
              else  if(terminalName.equals("pt3"))
              {
              	
              	Simulation.pt3.isWorking = isWorking;
              }
              
              else  if(terminalName.equals("ct1"))
              {
              	
              	Simulation.ct1.isWorking = isWorking;
              }
              
              else  if(terminalName.equals("ct2"))
              {
              	
              	Simulation.ct2.isWorking = isWorking;
              }
              
              
        }
    }
    
    @Override
    public void run() {
        try {
            watchTerminalStatus(filePath);
        } catch (IOException | InterruptedException e) {
        	LOGGER.log(Level.WARNING,"Problem u run metodi");
        }
    }
}
