package HiloEjemplos;

public class HiloJoin extends Thread{
private int n;
public HiloJoin(String nom, int n) {
	super(nom);
	this.n=n;
}

public void run() {
	for(int i=0;i<=n;i++) {
		System.out.println(getName()+" : "+i);
		try {
			sleep(1000);
		}catch(InterruptedException ignore) {
			
		}
		System.out.println("Fin del bucle "+getName());
	}
	
}
public static void main(String[] args) {
	HiloJoin h1= new HiloJoin("Hilo1", 2);
	HiloJoin h2= new HiloJoin("Hilo2", 7);
	HiloJoin h3= new HiloJoin("Hilo3", 5);
	h1.start();
	h2.start();
	h3.start();
	try {
		h1.join();h2.join();h3.join();
	}catch(InterruptedException e) {
		System.out.println("END");
	}

}
}
