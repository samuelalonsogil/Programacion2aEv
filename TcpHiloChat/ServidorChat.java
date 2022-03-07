package TcpHiloChat;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServidorChat extends JFrame implements ActionListener {

	private static final long serialVersionUlD = 1L;

	static ServerSocket servidor;
	static final int PUERTO = 44444;
	// Indicamos que puerto escuchará el servidor
	static int CONEXIONES = 0;
	//yebta el numero actual de conexiones activas
	static int ACTUALES = 0; 
	static JTextField mensaje = new JTextField("");
	static JTextField mensaje2 = new JTextField("");
	private JScrollPane scrollpanel;
	static JTextArea textarea;
	JButton salir = new JButton("Salir");

	static ArrayList<Socket> tabula = new ArrayList<>();
	

	// Constructor

	public ServidorChat() {
		super(" VENTANA DEL SERVIDOR DE CHAT ");
		setLayout(null);
		mensaje.setBounds(10, 10, 400, 30);
		add(mensaje);
		mensaje.setEditable(false);

		mensaje2.setBounds(10, 348, 400, 30);
		add(mensaje2);
		mensaje2.setEditable(false);
		textarea = new JTextArea();
		scrollpanel = new JScrollPane(textarea);
		scrollpanel.setBounds(10, 50, 400, 300);
		add(scrollpanel);
		salir.setBounds(420, 10, 100, 30);
		add(salir);
		textarea.setEditable(false);
		salir.addActionListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	// Cuando pulsamos salir cierra 
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == salir) {
			
			try {
				servidor.close(); 

			} catch (IOException el) {
				el.printStackTrace();
			}
			System.exit(0);
		}
	} 

	public static void main(String args[]) throws IOException {

		servidor = new ServerSocket(PUERTO);
		System.out.println("Servidor iniciado...");

		ServidorChat pantalla = new ServidorChat();
		pantalla.setBounds(0, 0, 540, 400);
		pantalla.setVisible(true);
		mensaje.setText("NUMERO DE CONEXIONES ACTUALES: " + 0);

		
		while(true) {
			Socket s = new Socket();
			try {
				s = servidor.accept();
				// Espera al cliente para aceptarlo
			} catch (SocketException ns) {
				//Si pulsamos salir cierra sin pasar por todo el bucle
				break;
			}
			
			tabula.add(s);
			CONEXIONES++;
			ACTUALES++;
			HiloServidor hilo = new HiloServidor(s);
			hilo.start();
		}

		if (!servidor.isClosed()) {
			try {
				mensaje2.setForeground(Color.red);
				mensaje2.setText("MAXIMO N° DE CONEXIONES ESTABLECIDAS: " + CONEXIONES);
				servidor.close();
			} catch (IOException el) {
				el.printStackTrace();
				System.out.println("Servidor finalizado.");
			}
		} // if

	}
}