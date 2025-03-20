package pcd.lab03.cswithlocks;

import java.util.concurrent.locks.Lock;

public class MyWorkerB extends Worker {
	
	private Lock lock;
	
	public MyWorkerB(String name, Lock lock){
		super(name);
		this.lock = lock;
	}

	public void run(){
		while (true){
		  try {
			  /* Quando un thread chiama 'lock.lockInterruptibly()' il thread rimane in
			  attesa finchè non riesce ad acquisire il lock, se questo è già detenuto da
			  un altro thread. Durante l'attesa il thread può essere interrotto (ad
			  esempio con 'Thread.interrupt()'. Se il thread viene interrotto mentre è in
			  attesa, viene generata un'eccezione 'InterruptedException', e il thread non
			  acquisisce il lock. */
			  lock.lockInterruptibly();
			  b1();	
			  b2();
		  } catch (InterruptedException ex) {
		  } finally {
			  lock.unlock();
		  }
		  b3();
		}
	}
	
	protected void b1(){
		println("b1");
		wasteRandomTime(0,1000);	
	}
	
	protected void b2(){
		println("b2");
		wasteRandomTime(100,200);	
	}
	protected void b3(){
		println("b3");
		wasteRandomTime(1000,2000);	
	}
}
