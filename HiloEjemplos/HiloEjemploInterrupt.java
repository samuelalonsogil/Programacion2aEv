package HiloEjemplos;

public class HiloEjemploInterrupt extends Thread{
	
	public void run() {
		//El metodo run se ejecutara mientras el hilo no sea interrumpido, cuando lo haga enseñara un mensaje por la excepcion interruptedException y parará
		try {
			while(!isInterrupted()) {
				System.out.println("En el hilo");
			Thread.sleep(10);
			}
			
		}catch(InterruptedException e){
System.out.println("Ha ocurrido un error en el hilo");	
}System.out.println("Fin del hilo");
	}
	public void interrumpir() {
		interrupt();
	}
public static void main(String[] args) {
	HiloEjemploInterrupt h= new HiloEjemploInterrupt();
	h.start();
	for(int i=0; i<10000000;i++);
	h.interrumpir();
}
}
