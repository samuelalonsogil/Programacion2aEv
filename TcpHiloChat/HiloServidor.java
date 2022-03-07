package TcpHiloChat;


import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class HiloServidor extends Thread {

	DataInputStream fentrada;
	Socket socket = null;

	public HiloServidor(Socket s) {
		socket = s;
		try {
			// CREO FLUJO DE ENTRADA
			fentrada = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("ERROR DE E/S");
			e.printStackTrace();
		} //

	}// constr?

	public void run() {
		//Cuando un cliente se conecta decimos el numero de conexiones y le mandamos todos los mensajes
		ServidorChat.mensaje.setText("NUMERO DE CONEXIONES ACTUALES: " + ServidorChat.ACTUALES);
		String texto = ServidorChat.textarea.getText();
		EnviarMensajes(texto);
//Lee lo que manda el cliente, y cuando manda un * lo sacamos del array y se desconecta
		while (true) {
			String cadena = "";
			try {
				cadena = fentrada.readUTF();

				if (cadena.trim().equals("*")) {
					ServidorChat.ACTUALES--;
					ServidorChat.mensaje.setText("NUMERO DE CONEXIONES ACTUALES: " + ServidorChat.ACTUALES);
					ServidorChat.tabula.remove(this);
					break;
				}

				ServidorChat.textarea.append(cadena + "\n");
				texto = ServidorChat.textarea.getText();
				EnviarMensajes(texto);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		} 
	}

	//Metodo para enviar los mensajes a los clientes que se conecten
	private void EnviarMensajes(String texto) {
		int i;
		for (i = 0; i < ServidorChat.CONEXIONES; i++) {
			//ojo el socket esta en un array dianmico
			Socket si = ServidorChat.tabula.get(i);
			try {
				DataOutputStream fsalida = new DataOutputStream(si.getOutputStream());
				fsalida.writeUTF(texto);
			} catch (SocketException se) {
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // for
	}// EnviarMensajes
}// .. Fin HiloServidor