package UDPEncripta;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServerEncripta {


	public static void main(String args[]) throws Exception {

		// Le decimos al programa en que puerto debe escuchar y creamos arrays para enviar y recibir datos
		DatagramSocket serverSocket = new DatagramSocket(9886);
		byte[] recibidos;
		byte[] enviados = new byte[1024];
		String cadena;

		//El servidor funcionará de forma infinita hasta que reciba un asterisco, recibiendo datos, encriptandolos y mandandolos al cliente
		while (true) {
			System.out.println("Esperando datagrama...");
			
			recibidos = new byte[1024];
			DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
			serverSocket.receive(paqRecibido);
			cadena = new String(paqRecibido.getData());
			
						
			InetAddress IPOrigen = paqRecibido.getAddress();
			int puerto = paqRecibido.getPort();
			System.out.println("\tOrigen: " + IPOrigen + ":" + puerto);
			System.out.println("\tMensaje recibido: " + cadena.trim());
		
			String resultado = Encriptado(cadena);
			enviados = resultado.getBytes();
			
			DatagramPacket paqEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
			serverSocket.send(paqEnviado);
			
			if (cadena.trim().equals("*"))
				break;
		}

		serverSocket.close();
		System.out.println("Socket cerrado...");

	}
private static String Encriptado(String mensajeRecibido) {
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