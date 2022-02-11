import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {




    public static void main(String[] args) throws IOException {
        String host = "10.101.19.124";
        int port = 6000;
        Socket client = new Socket(host, port);

        // CREO FLUJO DE SALIDA AL SERVIDOR
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);

        // CREO FLUJO DE ENTRADA AL SERVIDOR
        BufferedReader inServer = new BufferedReader(new InputStreamReader(client.getInputStream()));

        // FLUJO PARA ENTRADA ESTANDAR
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String introducedString, receivedString = "";

        System.out.print("Introduce the data ( * to stop ) : " );
        /*lectura por teclado*/
        introducedString = in.readLine();

        while (introducedString != null && !introducedString.equals("*")) {
            out.println(introducedString);

            /*envio data al servidor*/
            receivedString = inServer.readLine();

            /*recibo data del servidor*/
            System.out.println(" =>ECO upperCase: " + receivedString);

            System.out.print("Introduce data: ");
            /*lectura por teclado 1*/
            introducedString = in.readLine();
        }

        out.close();
        in.close();

        System.out.println(" Process finished ");

        in.close();
        client.close();
    }
}
