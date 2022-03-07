package EjEncriptar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPEncriptarServidor {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		int numeroPuerto = 6000;
		//Creamos el socket del servidor en el mismo puerto que el cliente
		ServerSocket servidor = new ServerSocket(numeroPuerto);
		
		String cad="";
		System.out.println("SERVIDOR de Encriptado");
		System.out.println("Esperando conexion...");
		//Acepta al cliente en el momento que lo recibe
		Socket clienteConectado = servidor.accept();
		System.out.println("Cliente conectado...");
		
		//Creamos el writer y el reader
		PrintWriter fsalida = new PrintWriter
		(clienteConectado.getOutputStream(),true);
		
		
		BufferedReader fentrada = new BufferedReader
		(new InputStreamReader(clienteConectado.getInputStream()));
		
		//Guarda la informacion que llega del cliente, la encripta y la manda de vuelta
		while ((cad=fentrada.readLine())!= null) {
			
			fsalida.println(servidorEncriptar(cad)); 
			
			if(cad.equals("*")) 
				break;
		}
			
		System.out.println("Cerrando conexion...");
		fentrada.close();
		fsalida.close();
		clienteConectado.close();
		servidor.close();
		}
	
	private static String servidorEncriptar(String mensajeRecibido) {
		System.out.println(mensajeRecibido);
		mensajeRecibido = mensajeRecibido.toLowerCase();
		String cadenaAEnviar = "";

		for (int i = 0; i < mensajeRecibido.length(); i++) {
			char c = mensajeRecibido.charAt(i);
			char s = c;

			if (c >= '0' && c <= '8') s = (char) (c + 1);
			if (c == '9') s = '0';
			if (c >= 'a' && c <= 'y') s = (char) (c + 1);
			if (c == 'z') s = 'a';
			cadenaAEnviar += s;
		}
		System.out.println(cadenaAEnviar);
		return cadenaAEnviar.toUpperCase();
		}
}
		