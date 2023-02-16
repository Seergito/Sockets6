package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Socket socketcliente= null;
		ServerSocket socketservidor=null;
		String ip="localhost";
		int puerto=5500;
		
		
		
		try {
			
			Scanner entrada=new Scanner(System.in);
			socketcliente=new Socket(ip,puerto);
			System.out.println("Usuario conectado al servidor de puntuaciones...");
			DataInputStream recibir= new DataInputStream(socketcliente.getInputStream());
			DataOutputStream enviar= new DataOutputStream(socketcliente.getOutputStream());
			System.out.println("Introduce una puntución y tu nombre completo o PUNTUACIONES ");
			String control=entrada.nextLine();
			enviar.writeUTF(control);
			System.out.println("Servidor: "+recibir.readUTF());
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
