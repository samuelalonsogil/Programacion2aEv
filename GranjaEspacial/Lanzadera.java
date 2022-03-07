package GranjaEspacial;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public final class Lanzadera {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		// puerto remoto
		String Host = "localhost";
		int Puerto = 1236;

		System.out.println("PROGRAMA CLIENTE INICIADO...");

		Socket recGranja = new Socket(Host, Puerto);

		//Creamos un flujo de entrada para recibir los objetos del servidor
		ObjectInputStream aniEnt = new ObjectInputStream(recGranja.getInputStream());
		
		@SuppressWarnings("unchecked")
		ArrayList<Animal> listaAnimales = (ArrayList<Animal>) aniEnt.readObject();
		//Recibimos los animales y comprobamos que llegaron bien
		for (Animal anim : listaAnimales) {
			System.out.println("Recibo: "+anim.toString());
		}
		
		//Creamos dos objetos, los guardamos en un arrayList y los enviamos a la granja 
		Caballo caballo2 = new Caballo();
		Gato gato2 = new Gato();
		
		listaAnimales.add(caballo2);
		listaAnimales.add(gato2);
		
		ObjectOutputStream aniSal = new ObjectOutputStream(recGranja.getOutputStream());
		
		aniSal.writeObject(listaAnimales);
		
		System.out.println("Envio: " + caballo2 + "*" + gato2);
		
		aniEnt.close();
		aniSal.close();
		recGranja.close();
	}

}