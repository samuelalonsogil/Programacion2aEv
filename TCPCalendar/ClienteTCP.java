package TCPCalendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClienteTCP {

	

		public static void main(String[] args) throws IOException {
			//Creamos la conexion del cliente en el mismo puerto que usará el servidor
			String Host = "localhost";
			int Puerto = 6000;
			Socket Cliente = new Socket(Host, Puerto);

			//Creamos el writer para poder pasar informacion al servidor
			PrintWriter fsalida = new PrintWriter(Cliente.getOutputStream(), true);

			BufferedReader fentrada = new BufferedReader(new InputStreamReader(Cliente.getInputStream()));

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String cadena, respuesta = "";
			System.out.println("PULSA * PARA TERMINAR EL CLIENTE");
			System.out.print("Introduce fecha en formato DIA/MES/AÑO: ");
			cadena = in.readLine(); 
				//Mientras no se pase null o * se pasaran fechas al servidor y este nos dirá que dia de semana fue
			while (cadena != null) {
				fsalida.println(cadena); 
				respuesta = fentrada.readLine(); 
				System.out.println(" Resultado: " + respuesta);

				System.out.print("Introduce fecha en formato DIA/MES/AÑO ");
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