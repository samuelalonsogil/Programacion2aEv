import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServidoObjeto01 {

    public final int NUM_PUERTO=6000;
    public ServerSocket serverSocket;
    public Socket sockerClient;
    public ObjectOutputStream objectOutputStream;
    public Persona persona;
    public Persona personaDato;
    public ObjectInputStream objectInputStream;
    public ArrayList<Persona> personas;


    public void cargarArray(){
        personas = new ArrayList<Persona>();
        personas.add(new Persona("Samuel", 22));
        personas.add(new Persona("Balseiro", 21));
        personas.add(new Persona("Oitaben", 23));
        personas.add(new Persona("Martin", 21));
    }

    public void initVariables() throws IOException, ClassNotFoundException {
        System.out.println("esperando al client...");
        serverSocket = new ServerSocket(NUM_PUERTO);
        sockerClient = serverSocket.accept();
        objectOutputStream = new ObjectOutputStream(sockerClient.getOutputStream());

        cargarArray();
        for (Persona persona:personas){
            objectOutputStream.writeObject(persona);
            System.out.println(persona.getNombre() + " " + persona.getEdad());
        }

        objectInputStream = new ObjectInputStream( sockerClient.getInputStream() );
        personaDato = (Persona) objectInputStream.readObject();

        System.out.println("Recibo: " + personaDato.getNombre() + " " + personaDato.getEdad() );

        objectOutputStream.close();
        objectInputStream.close();
        sockerClient.close();

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServidoObjeto01 server01 = new ServidoObjeto01();
        server01.initVariables();



    }
}
