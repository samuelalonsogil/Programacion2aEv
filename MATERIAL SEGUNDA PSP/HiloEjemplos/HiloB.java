package HiloEjemplos;
public class HiloB extends Thread{
	private Contador contador;
	public HiloB(String g,Contador c) {
		setName(g);

		contador=c;
	}
	//Se crea un segundo hilo que decremente el contador
	public void run() {
		for (int i=0;i<300;i++) {
			contador.decremento();
			System.out.println("Contador vale "+contador.getValor());

			try {
			sleep(1000);
			}catch(InterruptedException e) {
				
			}
		}
	}

}
