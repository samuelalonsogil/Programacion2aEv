import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client01 {
    public String host = "localhost";
    public int port = 6000;
    public Socket socketClient;
    public ObjectInputStream objectInputStream;
    public Persona personaDato;
    public ObjectOutputStream objectOutputStream;

    public  void initVariables() throws IOException, ClassNotFoundException {
        System.out.println("client iniciado");

        socketClient = new Socket(host,port);
        objectInputStream = new ObjectInputStream(socketClient.getInputStream());
        personaDato = (Persona) objectInputStream.readObject();

        System.out.println("Recibo: " + personaDato.getNombre() + personaDato.getEdad());

        personaDato.setNombre("Samu");
        personaDato.setEdad(22);

        objectOutputStream = new ObjectOutputStream(socketClient.getOutputStream());
        objectOutputStream.writeObject(personaDato);
        System.out.println("Envio: " + personaDato.getNombre() + " " + personaDato.getEdad());

        objectOutputStream.close();
        objectInputStream.close();
        socketClient.close();

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client01 client01 = new Client01();
        client01.initVariables();


    }
}
