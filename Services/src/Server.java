import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) throws IOException {
        /*receptor del diagrama*/
        byte [] buffer = new byte[512];

        /*asociar el socket al puerto 12345*/
        DatagramSocket datagramSocket = new DatagramSocket(12345);

        /*esperar al diagrama*/
        System.out.println("Esperando al diagrama desde el cliente...");

        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);

        /*recibo datagrama*/
        datagramSocket.receive(datagramPacket);

        /*obtengo numero de bytes*/
        int bytesRec = datagramPacket.getLength();

        /*obtengo String*/
        String paquete = new String( datagramPacket.getData() );

        /*visualizo info*/
        System.out.println("Número de bytes recibidos: " + bytesRec + "\n" +
                            "Contenido del Paquete : " + paquete.trim() + "\n" +
                            "Puerto origen del mensaje: " + datagramPacket.getPort() + "\n" +
                            "IP de origen : " + datagramPacket.getAddress().getHostAddress() + "\n" +
                            "Puerto destino del mensaje:" + datagramSocket.getLocalPort() );

        /*cierro el socket*/
        datagramSocket.close();

    }
}
