import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PalindromoClient {
    public static void main(String[] args) throws IOException {
        String Host = "10.101.19.124";
        int Puerto = 6000;// puerto remoto

        Socket Cliente = new Socket(Host, Puerto);

        // CREO FLUJO DE SALIDA AL SERVIDOR
        PrintWriter fsalida = new PrintWriter(Cliente.getOutputStream(), true);

        // CREO FLUJO DE ENTRADA AL SERVIDOR
        BufferedReader fentrada = new BufferedReader(new InputStreamReader(Cliente.getInputStream()));

        // FLUJO PARA ENTRADA ESTANDAR
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String cadena, recibida = "";

        System.out.print("Introduce cadena: ");
        cadena = in.readLine();//lectura por teclado

        while (cadena != null && !cadena.equals("*")) {
            fsalida.println(cadena);
            //envio cadena al servidor
            recibida = fentrada.readLine();
            //recibo cadena del servidor
            System.out.println(" =>ECO upperCase: " + recibida);

            System.out.print("Introduce cadena: ");
            cadena = in.readLine();//lectura por teclado 1
        }

        fsalida.close();
        fentrada.close();

        System.out.println("Fin del envio... ");

        in.close();
        Cliente.close();
    }
}
