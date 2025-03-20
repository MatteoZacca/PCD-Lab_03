package pcd.lab03.ex01_synchwithsem;

import java.util.concurrent.Semaphore;

public class Pinger extends ActiveComponent {

	private Semaphore pingerSemaphore;
	private Semaphore pongerSemaphore;

	public Pinger(final String name, Semaphore pingerSemaphore, Semaphore pongerSemaphore) {
		super(name);
		this.pingerSemaphore = pingerSemaphore;
		this.pongerSemaphore = pongerSemaphore;
	}	
	
	public void run() {
		while (true) {
			try {
				pingerSemaphore.acquire(); // Pinger aspetta il permesso
				println("[ " + this.getName() + " ] " + "ha ottenuto un permesso!");
				println("ping");
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				currentThread().interrupt();
			} finally {
				pongerSemaphore.release();
			}
		}
	}
}