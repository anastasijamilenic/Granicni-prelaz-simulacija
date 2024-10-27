package projekat;
import java.io.File;

import gui.Gui;
public class Main {

	public static void main(String[] args) {
		
		
		
		File file = new File("reports/problemsCustoms.txt");
		
		if(file.exists()) {
			file.delete();
		}
		
        File file2 = new File("reports/policeTerminal.bin");
		
		if(file2.exists()) {
			file2.delete();
		}
		
		String path = "fileWatcher/terminalState.txt";
		Gui gui = new Gui();
		Thread t = new Thread(gui);
		t.start();
		
		Thread fileWatcherThread = new Thread(new FileWatcherService(path));
        fileWatcherThread.start();
		
		Simulation sim = new Simulation();
	}
}
