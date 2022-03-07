package GranjaEspacial;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GranjaEspacial {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		int numeroPuerto = 1236;
		ServerSocket servidor = new ServerSocket(numeroPuerto);
		System.out.println("Esperando al cliente...");
		Socket envGranja = servidor.accept();

		// flujo out
		ObjectOutputStream outObjeto = new ObjectOutputStream(envGranja.getOutputStream());
		
		Caballo caballo = new Caballo();
		Gato gato = new Gato();
		Jirafa jirafa = new Jirafa();
		Perro perro = new Perro();
		
		System.out.println("Enviando: " + caballo +"*"+gato+"*"+jirafa+"*"+perro);	
		//Guardamos los animales en un arrayList para enviarlos al cliente
		ArrayList<Animal> listaAnimales = new ArrayList<>();
		
		listaAnimales.add(caballo);
		listaAnimales.add(gato);
		listaAnimales.add(jirafa);
		listaAnimales.add(perro);
		
		outObjeto.writeObject(listaAnimales);
		
		//Recibimos un arrayList de animales del cliente 
		ObjectInputStream inObjeto = new ObjectInputStream(envGranja.getInputStream());
		
		@SuppressWarnings("unchecked")
		ArrayList<Animal> listaAnimalesRecibo = (ArrayList<Animal>) inObjeto.readObject();
		
		for (Animal ani : listaAnimalesRecibo) {
			System.out.println("Recibo: "+ani);
		}
		
		outObjeto.close();
		inObjeto.close();
		envGranja.close();
		servidor.close();
	}

}