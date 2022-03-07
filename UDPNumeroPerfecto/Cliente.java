package UDPNumeroPerfecto;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
 

public class Cliente {

    public static void main(String[] args) throws Exception {
        
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            	    	
    	DatagramSocket clientSocket = new DatagramSocket();
 
        byte[] enviados = new byte[1024];
        byte[] recibidos = new byte[1024];
 
        InetAddress IPServidor = InetAddress.getLocalHost();
        int puerto = 9886;
        boolean rec=false;
        while(rec==false) {
        System.out.println("Introduce un mensaje: ");
        String cadena = in.readLine();
        enviados = cadena.getBytes();
 
        System.out.println("Enviando " + enviados.length + " bytes al servidor.");
        //envia
        DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
        clientSocket.send(envio);
 
        //recibe
			DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
        System.out.println("Esperando datagrama...");
        clientSocket.receive(recibo);
        String mayuscula = new String(recibo.getData());
 
        InetAddress IPOrigen = recibo.getAddress();
        int puertoOrigen = recibo.getPort();
        System.out.println("\t Procedente de: " + IPOrigen + ": " + puertoOrigen);
        System.out.println("\t Datos: " + mayuscula.trim());
        
        if (cadena.trim().equals("*")) {
            rec=true;
        }
        
        }
        clientSocket.close();
    }
}
 


