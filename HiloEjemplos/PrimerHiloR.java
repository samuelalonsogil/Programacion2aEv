package HiloEjemplos;

public class PrimerHiloR implements  Runnable {
	
//La interfaz Runnable se implementa para que se pueda utilizar la clase como un hilo
private int x;
PrimerHiloR(int x){
	this.x=x;
	
}
//El run lo unico que hace es imprimir por pantalla el valor de x en un bucle for
@Override
public void run() {
for(int i=0; i<x;i++) {
	System.out.println("En el hilo... "+i);
}
}
public static void main(String[] args) {
	//Creamos aqui el hilo a y lo ejecutamos con start
 PrimerHiloR r= new PrimerHiloR(10);
 new Thread(r).start();
 
}
}
