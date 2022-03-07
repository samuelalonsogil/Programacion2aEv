package TCP2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejemplo2Servidor {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		int numeroPuerto = 6000;// Puerto
		ServerSocket servidor = new ServerSocket(numeroPuerto);
		
		String cad="";
		System.out.println("SERVIDOR de ECO");
		System.out.println("Esperando conexion...");
		
		Socket clienteConectado = servidor.accept();
		System.out.println("Cliente conectado...");
		
		// CREO FLUJO DE SALIDA AL CLIENTE
		PrintWriter fsalida = new PrintWriter
		(clienteConectado.getOutputStream(),true);
		
		// CREO FLUJO DE ENTRADA DEL CLIENTE
		BufferedReader fentrada = new BufferedReader
		(new InputStreamReader(clienteConectado.getInputStream()));
		
		//FUNCIONAMIENTO DEL SERVIDOR COMO ECO EN MAYUSCULAS
		while ((cad=fentrada.readLine())!= null) {
			
			//envio cadena al cli ent e
			fsalida.println(servidorECO(cad)); 
			
			//incluir * en la condicion logica ????
			if(cad.equals("*")) 
				break;
		}
			
		// CERRAR STREAMS Y SOCKETS
		System.out.println("Cerrando conexion...");
		fentrada.close();
		fsalida.close();
		clienteConectado.close();
		servidor.close();
		}//main
	
	private static String servidorECO(String cadena) {
		System.out.println("Recibiendo: " + cadena);
		return cadena.toUpperCase(); 
		}
}
