package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {



	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ServerSocket serversocket= null;
		Socket socketcliente=null;
		int puerto=5500;
		int contador=0;
		try {
			serversocket= new ServerSocket(puerto);
			
			System.out.println("Servidor abierto...");
			while(true) {
				socketcliente=serversocket.accept();
				contador++;
			//	System.out.println("Cliente "+contador+" conectado");
				HiloServidor hilo = new HiloServidor(contador,socketcliente);			
					hilo.start();	
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
		
	}

}
