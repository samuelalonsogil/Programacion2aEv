package udp;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
 
public class clienteudp2{
 
	//instancia del array de recepcion
	
	//corte con booleano de la comunicacion
	
    public static void main(String[] args) throws Exception {
        
    	//NECESITO UN BUFFERREADER PARA METER STRINGS en los Packets
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            	    	
    	DatagramSocket clientSocket = new DatagramSocket();
 
    	//para recibir y enviar datos 
        byte[] enviados = new byte[1024];
        byte[] recibidos = new byte[1024]; //no deberia instanciarse en el bucle??
 
        InetAddress IPServidor = InetAddress.getLocalHost();
      //  InetAddress IPServidor = InetAddress.getByName("192.168.1.50");
        int puerto = 9886;
 
        while(true) {
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
            break;//sale con un break, deberia ser un booleano
        //donde esta el true del while
        }
        
        }
        clientSocket.close();
    }
 
}