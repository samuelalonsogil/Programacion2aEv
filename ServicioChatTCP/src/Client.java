import java.io.IOException;
import java.net.Socket;

public class Client {
    public final String HOST = "localhost";
    public final int PORT = 6000;
    public Socket client;

    public void initVariables() throws IOException {
        client = new Socket(HOST, PORT);
    }

    public static void main(String[] args) {

    }
}
