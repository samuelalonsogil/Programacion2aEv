import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {

    public String getDayWeek(String inputDate) throws ParseException {

        SimpleDateFormat format01 = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format01.parse(inputDate);
        DateFormat format02 = new SimpleDateFormat("EEEE");
        return  format02.format(date);

    }



    /*service*/
    public static void main(String[] args) throws IOException, ParseException {

        int port = 6000;
        ServerSocket serverSocket = new ServerSocket(port);
        String string = "";
        Server server = new Server();



        System.out.println("Welcome to ECO server");
        System.out.println("Waiting for connection...");

        Socket clientConnected = serverSocket.accept();
        System.out.println("Client connected...");

        /*CREO FLUJO DE SALIDA AL CLIENTE*/
        PrintWriter out = new PrintWriter( clientConnected.getOutputStream(), true );

        /*CREO FLUJO DE ENTRADA DEL CLIENTE*/
        BufferedReader in = new BufferedReader(new InputStreamReader(clientConnected.getInputStream()));

        /*MÉTODO DEL SERVIDIOR*/
        while ((string = in.readLine()) != null) {
            /*recibo string del cliente, uso el metodo y envio cadena al cliente*/

            out.println(server.getDayWeek(string));

            if (string.equals("*"))
                break;
        }
        //Fin del servidor
        // CERRAR STREAMS Y SOCKETS
        System.out.println("Cerrando conexión...");
        in.close();
        out.close();
        clientConnected.close();

        serverSocket.close();

}
}
