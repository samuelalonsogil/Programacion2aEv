package TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
 
public class ServerS1 {
    public static void main(String[] args) {
 
        try {
            int Puerto = 6000;// Puerto
            ServerSocket Servidor = new ServerSocket(Puerto);
            System.out.println("Escuchando en " + Servidor.getLocalPort());
            Socket clientel = Servidor.accept();// esperando a un cliente
            // realizar acciones con clientel
            Socket cliente2 = Servidor.accept();// esperando a otro cliente
            // realizar acciones con cliente2
           Servidor.close(); // cierro socket servidor
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}