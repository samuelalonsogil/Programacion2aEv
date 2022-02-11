import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PalindromoServer {


    public static String aMayusculas(String llega) {
        System.out.println("Recibiendo: " + llega);
        return llega.toUpperCase();
    }

    public static String esPalinidromo(String llega) {
        String invertida = new StringBuilder(llega).reverse().toString();
        if (invertida.equals(llega)) return llega + "es palindromo";
        else return llega + "no es palindromo";
    }

    //servicio
    public static void main(String[] args) throws IOException {

        int numeroPuerto = 6000;// Puerto
        ServerSocket servidor = new ServerSocket(numeroPuerto);
        String cad = "";
        System.out.println("Bienvenido al servidor ECO");
        System.out.println("Esperando conexion...");

        Socket clienteConectado = servidor.accept();
        System.out.println("Cliente conectado...");

        // CREO FLUJO DE SALIDA AL CLIENTE
        PrintWriter fsalida = new PrintWriter(clienteConectado.getOutputStream(), true);

        //CREO FLUJO DE ENTRADA DEL CLIENTE
        BufferedReader fentrada = new BufferedReader(new InputStreamReader(clienteConectado.getInputStream()));

        /*MÉTODO DEL SERVIDIOR*/
        while ((cad = fentrada.readLine()) != null) {
            // recibo cad del cliente  y uso el metodo y envio cadena al cliente
            fsalida.println(esPalinidromo(cad));

            if (cad.equals("*"))
                break;
        }
        //Fin del servidor
                // CERRAR STREAMS Y SOCKETS
                System.out.println("Cerrando conexión...");
        fentrada.close();
        fsalida.close();
        clienteConectado.close();

        servidor.close();
    }
}

