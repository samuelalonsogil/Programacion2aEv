package Multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClienteMC1 {

public static void main(String[] args) throws Exception {
		
		MulticastSocket ms = new MulticastSocket(12345);
		InetAddress grupo = InetAddress.getByName("225.0.0.1");
		ms.joinGroup(grupo);

		//llegada de bytes 
		
		String msg="" ;

		while (!msg.trim().equals("*")) {
			byte[] buf = new byte[1024];
			DatagramPacket paquete = new DatagramPacket(buf, buf.length);
			ms.receive(paquete);
			msg = new String(paquete.getData());
			System.out.println("Recibo. " + msg.trim());
			
		} 
		
		ms.leaveGroup(grupo);
		ms.close();
		System.out.println("Socket Cerrado...");

	}
	
}