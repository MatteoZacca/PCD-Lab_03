package pcd.lab03.ex01_synchwithsem;

import java.util.concurrent.Semaphore;

/**
 * Unsynchronized version
 * 
 * @TODO make it sync 
 * @author aricci
 *
 */
public class TestPingPong {
	public static void main(String[] args) {
		// Creiamo due semafori: uno per il thread Pinger (inizia con permesso 1) e uno
		// per il thread Ponger (inizia con 0 permessi)
		Semaphore pingerSemaphore = new Semaphore(1); // Pinger parte per primo
		Semaphore pongerSemaphore = new Semaphore(0); // Ponger deve aspettare

		// Passiamo i semafori ai rispettivi thread
		new Pinger("Pinger", pingerSemaphore, pongerSemaphore).start();
		new Ponger("Ponger", pingerSemaphore, pongerSemaphore).start();
	}

}
