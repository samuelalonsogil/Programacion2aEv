import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String args[]) throws Exception {

        /*FLUJO PARA ENTRADA ESTANDAR*/
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in) );

        /*socket cliente*/
        DatagramSocket clientSocket = new DatagramSocket();

        byte[] enviados;
        byte[] recibidos = new byte[1024];


        /*DATOS DEL SERVIDOR al que envier mensaje*/
        //InetAddress IPServidor = InetAddress.getLocalHost();
        InetAddress IPServidor = InetAddress.getByName("10.101.19.124");

        /*localhost*/
        int puerto = 9876;

        /*INTRODUCIR DATOS POR TECLADO*/
        while(true){
            System.out.print("Introduce mensaje: ");
            String cadena = in.readLine();
            enviados = cadena.getBytes();

            if (cadena.trim().equals("\\stop")) break;

            /*ENVIANDO DATAGRAMA AL SERVIDOR*/
            System.out.println("Enviando " + enviados.length + " bytes al servidor.");

            DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
            clientSocket.send(envio);

            /*RECIBIENDO DATAGRAMA DEL SERVIDOR*/
            DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
            System.out.println("Esperando datagrama .......... ");
            clientSocket.receive(recibo);

            String mayuscula = new String(recibo.getData() );

            /*OBTENIDENDO INFORMACION DEL DATAGRAMA*/
            InetAddress IPOrigen = recibo.getAddress();
            int puertoOrigen = recibo.getPort();
            System.out.println("\tProcedente de: " + IPOrigen + ":" + puertoOrigen);
            System.out.println("\tDatos: " + mayuscula.trim());

            if (cadena.trim().equals("*")) break;
        }

        /*cerrar socket*/
        clientSocket.close();
    }
}
