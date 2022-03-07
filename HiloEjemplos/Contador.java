package HiloEjemplos;

public class Contador {
	//Se crea un contador para utilizar en los hilos
private int c=0;
Contador(int c){this.c=c;}
public void incremento() {
	c+=1;
}
public void decremento() {
	c-=1;
}
public int getValor() {
	return c;
}
}
