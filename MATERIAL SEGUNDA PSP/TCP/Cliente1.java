package TCP;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
 
public class Cliente1 {
 
    public static void main(String[] args) {
 
        try {
            String Host = "192.168.1.190";
            int Puerto = 6000;// puerto remoto
            // ABRIR SOCKET
            Socket Cliente = new Socket(Host, Puerto);// conecta
            InetAddress i = Cliente.getInetAddress();
            System.out.println("Puerto local: " + Cliente.getLocalPort());
            System.out.println("Puerto Remoto: " + Cliente.getPort());
            System.out.println("Host Remoto: " + i.getHostName().toString());
            System.out.println("IP Host Remoto: " + i.getHostAddress().toString());
           Cliente.close();// Cierra el socket
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
}