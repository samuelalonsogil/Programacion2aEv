package UDPLoteria;


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
        int contador=0;
        while(rec==false) {
        	System.out.println("Numero de loteria "+(contador+1));
        System.out.println("Introduce un numero entre el 1 y el 46: ");
        String cadena = in.readLine();
        int c= Integer.parseInt(cadena);
        if((c<1)||(c>46)) {
        	 System.out.println("Vuelve a introducir un numero");
        }else {
        	enviados = cadena.getBytes();
       	 
            System.out.println("Enviando " + enviados.length + " bytes al servidor.");
            //envia
            DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
            clientSocket.send(envio);
            contador++;
            System.out.println(contador);
        }
       
        if(contador==6) {
			DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
        System.out.println("Esperando datagrama...");
        clientSocket.receive(recibo);
        String mayuscula = new String(recibo.getData());
 
        InetAddress IPOrigen = recibo.getAddress();
        int puertoOrigen = recibo.getPort();
        System.out.println("\t Procedente de: " + IPOrigen + ": " + puertoOrigen);
        System.out.println("\t Datos: " + mayuscula.trim());
        contador=0;
        }
        if (cadena.trim().equals("*")) {
            rec=true;
        }
        
        }
        clientSocket.close();
    }
}
 


