package TCP2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Ejemplo2Cliente {

	public static void main(String[] args) throws IOException {

		String Host = "localhost";
		int Puerto = 6000;// puerto remoto

		Socket Cliente = new Socket(Host, Puerto);

		// CREO FLUJO DE SALIDA AL SERVIDOR
		PrintWriter fsalida = new PrintWriter(Cliente.getOutputStream(), true);

		// CREO FLUJO DE ENTRADA AL SERVIDOR
		BufferedReader fentrada = new BufferedReader(new InputStreamReader(Cliente.getInputStream()));

		// FLUJO PARA ENTRADA ESTANDAR(equivale a un scanner)
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String cadena, eco = "";
		System.out.println("PULSA * PARA TERMINAR EL CLIENTE");
		System.out.print("Introduce cadena: ");
		cadena = in.readLine(); // lectura por teclado

		while (cadena != null) {
			fsalida.println(cadena); // envio cadena al servidor
			eco = fentrada.readLine(); // recibo cadena del servidor
			System.out.println(" =>ECO: " + eco);

			System.out.print("Introduce cadena: ");
			cadena = in.readLine(); // lectura por teclado
			
			// incluir * en la condicion logica ????
			if (cadena.equals("*"))
				break;
		}
		
		fsalida.close();
		fentrada.close();
		System.out.println("Fin del envio... ");
		in.close();
		Cliente.close();
	}
}