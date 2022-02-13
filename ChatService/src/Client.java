import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public Socket socket;
    public DataInputStream bufferIn;
    public DataOutputStream bufferOut;

    public Scanner scanner = new Scanner(System.in);
    public final String TERMINATE = "exit";


    public void startConnection(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            showText("Connected to :" + socket.getInetAddress().getHostName());
        } catch (Exception e) {
            showText("Exception starting the connection: " + e.getMessage());
            System.exit(0);
        }
    }

    public static void showText(String s) {
        System.out.println(s);
    }

    public void openBuffers() {
        try {
            bufferIn = new DataInputStream(socket.getInputStream());
            bufferOut = new DataOutputStream(socket.getOutputStream());
            bufferOut.flush();

        } catch (IOException e) {
            showText("Error opening buffers");
        }
    }

    public void sendText(String s) {
        try {
            bufferOut.writeUTF(s);
            bufferOut.flush();
        } catch (IOException e) {
            showText("IOException sending");
        }
    }

    public void closeConnection() {
        try {
            bufferIn.close();
            bufferOut.close();
            socket.close();
            showText("Connection terminated");
        } catch (IOException e) {
            showText("IOException closing connection()");
        }
    }

    public void executeConnection(String ip, int puerto) {
        Thread hilo = new Thread(() -> {
            try {
                startConnection(ip, puerto);
                openBuffers();
                getData();
            } finally {
                closeConnection();
            }
        });
        hilo.start();
    }

    public void getData() {
        String st = "";
        try {
            do {
                st = (String) bufferIn.readUTF();
                showText("\n[Servidor] => " + st);
                System.out.print("\n[Usted] => ");
            } while (!st.equals(TERMINATE));
        } catch (IOException e) {}
    }

    public void writeData() {
        String entrada = "";
        while (true) {
            System.out.print("[Usted] => ");
            entrada = scanner.nextLine();
            if(entrada.length() > 0)
                sendText(entrada);
        }
    }

    public static void main(String[] argumentos) {
        Client cliente = new Client();
        Scanner escaner = new Scanner(System.in);
        showText("Ingresa la IP: [localhost por defecto] ");
        String ip = escaner.nextLine();
        if (ip.length() <= 0) ip = "localhost";

        showText("Puerto: [5050 por defecto] ");
        String puerto = escaner.nextLine();
        if (puerto.length() <= 0) puerto = "5050";
        cliente.executeConnection(ip, Integer.parseInt(puerto));
        cliente.writeData();
    }
}
