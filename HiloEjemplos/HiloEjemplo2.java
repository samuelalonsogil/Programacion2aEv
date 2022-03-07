package HiloEjemplos;

public class HiloEjemplo2 extends Thread {
//Durante la ejecucion del hilo ejecutaremos los metodos que devuelven su nombre, propiedad e identificador
	public void run() {
		System.out.println("Dentro del hilo "+this.getName() +
				" Prioridad "+this.getPriority()
				+" Id "+this.getId()
				);
		
	}//Fin del run
	public static void main(String[] args) {
		HiloEjemplo2 h= null;
		//Dentro del for le damos un nombre y prioridad a cada hilo, ademas de hacer el toString que devuelve todos los datos del hilo
		for(int i=0;i<3;i++){
			h= new HiloEjemplo2();
			h.setName("Hilo"+i);
			h.setPriority(i+1);
			h.start();
			System.out.println("Informacion del hilo "+h.getName()+ " : "+h.toString());
		}
	}
}
