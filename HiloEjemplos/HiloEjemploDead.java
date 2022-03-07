package HiloEjemplos;

public class HiloEjemploDead extends Thread{
	
	private boolean stopHilo=false;
	//Cambia el valor de stopHilo para  salir del bucle infinito
	public void pararHilo() {
		stopHilo=true;
	}//Se crea un bucle infinito a no ser que se llame al metodo pararHilo para comprobar el funcionamiento del estado de los hilos
	public void run() {
		while(!stopHilo) {
			System.out.println("En el hilo");
		}
	}
	
	public static void main(String[] args) {
		HiloEjemploDead h= new HiloEjemploDead();
		h.start();
		//Ejecutamos el hilo, y le damos algo de tiempo para que enseñe algo por pantalla con el bucle for, al ejecutar el metodo pararHilo el hilo muere
		for(int i=0;i<100000000;i++);
			h.pararHilo();
		
	}

}
