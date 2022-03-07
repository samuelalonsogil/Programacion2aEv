package UDPEncripta;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
 

public class UDPEncriptaCliente {

    public static void main(String[] args) throws Exception {
        
    	//Creamos el buffered reader
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            	    	
    	DatagramSocket clientSocket = new DatagramSocket();
 
    	//Arrays de bytes para enviar y recibir datos
        byte[] enviados = new byte[1024];
        byte[] recibidos = new byte[1024]; 
 
        InetAddress IPServidor = InetAddress.getLocalHost();
        //Le decimos al inettAddress que se conecte al localhost
        int puerto = 9886;
        boolean rec=false;
        while(rec==false) {
        System.out.println("Introduce un mensaje: ");
        String cadena = in.readLine();
        enviados = cadena.getBytes();
 
        System.out.println("Enviando " + enviados.length + " bytes al servidor.");
        //envia datos
        DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
        clientSocket.send(envio);
 
        //recibe la cadena encriptada
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
 


