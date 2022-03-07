package TCPCalendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;

public class ServidorTCP {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//Creamos la conexion del server en el mismo puerto que el cliente
		int numeroPuerto = 6000;
		ServerSocket servidor = new ServerSocket(numeroPuerto);
		
		String cad="";
		System.out.println("SERVIDOR CALENDAR");
		System.out.println("Esperando conexion...");
		Socket clienteConectado = servidor.accept();
		System.out.println("Cliente conectado...");
		//Creamos el writer y el reader
		PrintWriter fsalida = new PrintWriter
		(clienteConectado.getOutputStream(),true);
		
		BufferedReader fentrada = new BufferedReader
		(new InputStreamReader(clienteConectado.getInputStream()));
		//Mientras la cadena no sea nula o * se recibiran fechas y mandará respuestas al cliente
		while ((cad=fentrada.readLine())!= null) {
			
			fsalida.println(obterDiaSemana(cad)); 
			
			if(cad.equals("*")) 
				break;
		}
			
		System.out.println("Cerrando conexion...");
		fentrada.close();
		fsalida.close();
		clienteConectado.close();
		servidor.close();
		}
	
	private static String obterDiaSemana(String cadena) {
		cadena = cadena.trim();
		String[] datos = cadena.split("/");

		System.out.println(cadena);

		for (int i = 0; i < datos.length; i++) {
			System.out.println(datos[i]);
		}

		int[] datosNumericos = new int[3];

		for (int i = 0; i < datos.length; i++) {
			datosNumericos[i] = Integer.parseInt(datos[i]);
			System.out.println(datosNumericos[i]);
		}

		//caramba no va bien				yy		MM				DD
		LocalDate dia = LocalDate.of(datosNumericos[2], datosNumericos[1], datosNumericos[0]);
		return dia.getDayOfWeek().toString();
		
	}
	
}