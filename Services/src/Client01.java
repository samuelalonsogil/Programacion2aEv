import java.io.IOException;
import java.net.*;

public class Client01 {
    public static void main(String[] args) throws IOException {
                                                    /*para hacerlo en remoto*/
        //InetAddress destino = InetAddress.getByName("10.101.19.124");

                                        /*para hacerlo en localhost*/
        InetAddress destino = InetAddress.getLocalHost();

        /*puerto al que envio el datagrama*/
        int port = 12345;

        byte[] mensaje;
        String saludo="soy samuel";

        /*codifico String a bytes*/
        mensaje = saludo.getBytes();

        /*CONSTRUYO EL DATAGRAMA A ENVIAR*/
        DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, port);

        /*Puerto local*/
        DatagramSocket socket = new DatagramSocket(34567);

        System.out.println(
                "Enviando Datagrama de longitud: "+ mensaje.length + "\n" +
                "Host destino : "+ destino.getHostName() + "\n" +
                "IP Destino : " + destino.getHostAddress() + "\n" +
                "Puerto local del socket: " + socket.getLocalPort() + "\n" +
                "Puerto al que envio: " + envio.getPort() );

        /*ENVIO DATAGRAMA*/
        socket.send(envio);

        /*cierro el socket*/
        socket.close();
    }
}
