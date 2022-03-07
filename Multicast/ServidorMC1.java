package Multicast;


import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ServidorMC1 {
	
		public static void main(String[] args) throws Exception {
			Scanner esc=new Scanner(System.in);
			MulticastSocket ms;
			ms = new MulticastSocket();
			int Puerto = 12345;
			String fec="";
			InetAddress grupo = InetAddress.getByName("225.0.0.1");
			
			while(!fec.trim().equals("*")) {
				
				System.out.println("Frase para el altavoz: ");
				LocalDate fecha= LocalDate.now();
				 fec= fecha.toString();
		
				DatagramPacket paquete = new DatagramPacket(fec.getBytes(), fec.length(), grupo, Puerto);
		
				ms.send(paquete);
				TimeUnit.SECONDS.sleep(10);

			}
			//FIN CONTINUO
			
			//cierra socket
			ms.close();
		}
		

}