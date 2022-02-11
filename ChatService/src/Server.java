import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

    public static void main(String[] args) throws IOException {

        /*FLUJO PARA ENTRADA ESTANDAR*/
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in) );

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

            enviados = cadena.getBytes();

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
