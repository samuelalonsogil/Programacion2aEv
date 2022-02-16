import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

    public BufferedReader bufferedReader;
    public DatagramSocket datagramSocket;

    public byte[] enviados;
    public byte[] recibidos;

    public InetAddress ipServer;

    public int port = 9876;

    public void initVaribles() throws SocketException, UnknownHostException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        datagramSocket = new DatagramSocket();
        enviados = new byte[1024];
        ipServer = InetAddress.getLocalHost();
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.initVaribles();

        while (true){
            System.out.println("Introduce mensaje: ");
            String string = client.bufferedReader.readLine();
        }
    }
}
