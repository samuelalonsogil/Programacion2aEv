package EjEncriptar;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPEncriptarCliente {

	public static void main(String[] args) throws IOException {

		String Host = "localhost";
		int Puerto = 6000;
			//Se crea un socket de cliente en el mismo puerto y direccion que el servidor
		Socket Cliente = new Socket(Host, Puerto);

		//Creamos el writer para poder pasar informacion al servidor
		PrintWriter fsalida = new PrintWriter(Cliente.getOutputStream(), true);

		//Flujo de entrada para recibir datos del servidor
		BufferedReader fentrada = new BufferedReader(new InputStreamReader(Cliente.getInputStream()));

		// Flujo de entrada estandar, tiene las mismas funcionalidades que un scanner 
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			//Crea la cadena a enviar al servidor leyendola por tecladp
		String cadena, eco = "";
		System.out.println("PULSA * PARA TERMINAR EL CLIENTE");
		System.out.print("Introduce cadena: ");
		cadena = in.readLine(); 
			//Mientras la cadena no sea nula o no se introduzca un * se enviaran datos al servidor y recibira la respuesta encriptada
		while (cadena != null) {
			fsalida.println(cadena); 
			eco = fentrada.readLine(); 
			System.out.println(" =>ECO: " + eco);

			System.out.print("Introduce cadena: ");
			cadena = in.readLine(); 
			
			
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