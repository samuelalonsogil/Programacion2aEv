package HiloEjemplos;

public class HiloA extends Thread{
	private Contador contador;
	public HiloA(String g,Contador c) {
		setName(g);

		contador=c;
	}
	//Se crea un hilo que sume el contador hasta 300
	public void run() {
		for (int i=0;i<300;i++) {
			contador.incremento();
			System.out.println("Contador vale "+contador.getValor());

			try {
			sleep(1000);
			}catch(InterruptedException e) {
				
			}
		}
	}

}
