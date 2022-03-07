import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Server {

    public BufferedReader bufferedReader;
    public MulticastSocket multicastSocket;
    public final int PORT = 12345;
    public InetAddress inetAddress;
    public DatagramPacket datagramPacket;
    public String string;

    public void initVariables() throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        multicastSocket = new MulticastSocket();
        inetAddress = InetAddress.getByName("225.0.0.1");
        string = "";
    }


    public String getText() throws IOException {
        System.out.print("Introduce the data you want to send (* to stop): ");
        return string = bufferedReader.readLine();
    }

    public void sendData() throws IOException {
        while(!string.trim().equals("*")) {
            if (string.equals("*") ) break;
            getText();
            /*send to group*/
            datagramPacket = new DatagramPacket(string.getBytes(), string.length(), inetAddress, PORT);
            multicastSocket.send (datagramPacket);
        }
        multicastSocket.close();
        System.out.println ("socket close...");
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.initVariables();
        server.sendData();
    }
}
