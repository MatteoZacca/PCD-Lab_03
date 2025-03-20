package pcd.lab03.ex01_synchwithsem;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.concurrent.Semaphore;

public class Ponger extends ActiveComponent {

	private Semaphore pingerSemaphore;
	private Semaphore pongerSemaphore;

	public Ponger(final String name, Semaphore pingerSemaphore, Semaphore pongerSemaphore) {
		super(name);
		this.pingerSemaphore = pingerSemaphore;
		this.pongerSemaphore = pongerSemaphore;
	}
	
	public void run() {
		while (true) {
			try {
				pongerSemaphore.acquire(); // Ponger aspetta che Pinger rilasci il permesso
				println("[ " + this.getName() + " ] " + "ha ottenuto un permesso!");
				println("pong");
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				currentThread().interrupt();
			} finally {
				pingerSemaphore.release();
			}
		}
	}
}