import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server02 {
    public static void main(String[] args) throws IOException {
        /*Puerto por el que escucha el servidor: 9876*/
        DatagramSocket serverSocket = new DatagramSocket(9876);

        byte[] recibidos;
        byte[] enviados;
        String cadena;

        while (true) {
            /*RECIBO DATAGRAMA*/
            System.out.println("Esperando datagrama ");
            recibidos = new byte[1024];

            DatagramPacket pagRecibido = new DatagramPacket(recibidos, recibidos.length);
            serverSocket.receive(pagRecibido);
            cadena = new String(pagRecibido.getData());

            //DIRECCION ORIGEN
            InetAddress IPOrigen = pagRecibido.getAddress();
            int puerto = pagRecibido.getPort();

            System.out.println("\tOrigen: " + IPOrigen + ":" + puerto);
            System.out.println("\tMensaje recibido: " + cadena.trim());

            //CONVERTIR CADENA A MAYUSCULA
            String mayuscula = cadena.trim().toUpperCase();
            enviados = mayuscula.getBytes();
            //ENVIO DATAGRAMA AL CLIENTE
            DatagramPacket pagEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
            serverSocket.send(pagEnviado);

            //Para terminar
            if (cadena.trim().equals("*")) break;
        }
        serverSocket.close();
        System.out.println("Socket cerrado...");
    }


}



