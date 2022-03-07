package UDPNumeroPerfecto;



import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {


	public static void main(String args[]) throws Exception {

	
		DatagramSocket serverSocket = new DatagramSocket(9886);
		byte[] recibidos;
		byte[] enviados = new byte[1024];
		String cadena;

	
		while (true) {
			System.out.println("Esperando datagrama...");
			
			recibidos = new byte[1024];
			DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
			serverSocket.receive(paqRecibido);
			//convierte el contenido en una cadena
			cadena = new String(paqRecibido.getData());
			
						
			InetAddress IPOrigen = paqRecibido.getAddress();
			int puerto = paqRecibido.getPort();
			System.out.println("\tOrigen: " + IPOrigen + ":" + puerto);
			System.out.println("\tMensaje recibido: " + cadena.trim());
			
			String resultado = Perfecto(cadena);
			enviados = resultado.getBytes();
			
			DatagramPacket paqEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
			serverSocket.send(paqEnviado);
			
			if (cadena.trim().equals("*"))
				break;
		}

		serverSocket.close();
		System.out.println("Socket cerrado...");

	}
private static String Perfecto(String mensajeRecibido) {

	
	int num= Integer.parseInt(mensajeRecibido.trim());
	String perfecto;

	int aux=0;
	for(int i=1;i<num;i++) {
		if(num%i==0) {
			aux+=i;
		}
	}
	String resultado;
	if(aux==num) {
		resultado="El numero es perfecto";
	}else {
		resultado="El numero no es perfecto";
	}
	System.out.println(resultado);
	return resultado;
	}
}
