package UDPLoteria;



import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {

	public static int loteriaRES[]= {25,43,21,4,6,7};
	public static int loteria[]= new int[6];
	public static int aux=0;
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
			cadena = new String(paqRecibido.getData());
			
						
			InetAddress IPOrigen = paqRecibido.getAddress();
			int puerto = paqRecibido.getPort();
			System.out.println("\tOrigen: " + IPOrigen + ":" + puerto);
			System.out.println("\tMensaje recibido: " + cadena.trim());
			
			
			
			for(int i=loteria.length-1;i>=0;i--) {
				if(loteria[i]==0) {
					aux=i;
					System.out.println(loteria[i]);
				}
			}
			int valor=Integer.parseInt(cadena.trim());
			loteria[aux]=valor;
			if(aux==5) {
			String resultado = calculoLoteria(loteria);
			for(int i=0;i<loteria.length;i++) {
				loteria[i]=0;
			}
			enviados = resultado.getBytes();
			aux=0;
			DatagramPacket paqEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
			serverSocket.send(paqEnviado);
			
			}
			
			if (cadena.trim().equals("*"))
				break;
		}

		serverSocket.close();
		System.out.println("Socket cerrado...");

	}
private static String calculoLoteria(int[] loteriaRecibida) {
int contador=0;
for(int i=0; i<loteriaRecibida.length;i++) {
	if (loteriaRecibida[i]==loteriaRES[i]) {
		contador++;
	}
}
	String resultado="";
	if(contador==6) {
		resultado="HAS GANADO LA LOTERIA BIEN HECHO";
	} else if(contador==5) {
		resultado="Casi ganas la loteria chaval";
	} else if(contador==4) {
		resultado="Acertaste 4 numeros, te quedas cerca";
	}else if(contador==3) {
		resultado="3 aciertos, menos da una piedra";
	}else {
		resultado="Lo importante es intentarlo";
	}
	
	return resultado;
	}
}
