package udp;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//corte del bucle con booleano
//logica del servicio en metodo aparte

public class servidorUDP2 {

	public static void main(String args[]) throws Exception {

		// Puerto por el que escucha el servidor: 9886
		// arrays de bytes para recibir y enviar
		DatagramSocket serverSocket = new DatagramSocket(9886);
		byte[] recibidos;
		byte[] enviados = new byte[1024];
		String cadena;

		// EL SERVIDOR FUNCIONA DE FORMA INFINITA HASTA ASTERISCO
		// DEVOLVIENDO EN MAYUSCULAS LA FRASE RECIBIDA
		while (true) {
			System.out.println("Esperando datagrama...");
			// RECIBO DATAGRAMA
			//crea un nuevo array para recepcion en cada pasada del bucle
			recibidos = new byte[1024];
			DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
			serverSocket.receive(paqRecibido);
			//convierte el contenido en una cadena
			cadena = new String(paqRecibido.getData());
			
						
			// DIRECCION ORIGEN
			InetAddress IPOrigen = paqRecibido.getAddress();
			int puerto = paqRecibido.getPort();
			System.out.println("\tOrigen: " + IPOrigen + ":" + puerto);
			System.out.println("\tMensaje recibido: " + cadena.trim());
			
			// CONVERTIR CADENA A MAYÚSCULA
			String mayuscula = cadena.trim().toUpperCase();
			enviados = mayuscula.getBytes();
			
			// ENVIO DATAGRAMA AL CLIENTE
			DatagramPacket paqEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
			serverSocket.send(paqEnviado);
			
			// Para terminar
			if (cadena.trim().equals("*"))
				break;// sale con un break, deberia ser un booleano
			// donde esta el true del while
		}

		serverSocket.close();
		System.out.println("Socket cerrado...");

	}

}
