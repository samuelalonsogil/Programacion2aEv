package HiloEjemplos;
//Se extiende la clase a Thread para poder ejecutar los hilos con el metodo start();
public class HiloEjemplo1 extends Thread{

	private int c;
	private int hilo;
	//Constructor de hilos
	public HiloEjemplo1(int hilo){
		this.hilo=hilo;
		}
	//Metodo que ejecutara el hilo con la llamada del start, en este caso solo recorre el bucle for enseñando datos por pantalla
	public void run() {
		c=0;
		while(c<=5) {
			System.out.println("Hilo "+hilo+" C "+c);
			c++;
		}
	}
	public static void main(String[] args) {
		
		HiloEjemplo1 h= null;
		//Creacion de varios hilos y su ejecucion
		for(int i=0; i<3;i++) {
			h= new HiloEjemplo1(i);
			h.start();
		}
		System.out.println("3 hilos creados");
	}
}
