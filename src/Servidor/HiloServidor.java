package Servidor;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;




public class HiloServidor extends Thread {

	Socket socketcliente;
	int contador;
	BufferedReader lectura=null;
	PrintWriter escritura=null;
	
	public HiloServidor(int contador,Socket socketcliente) {
		this.contador=contador;
		this.socketcliente=socketcliente;

	}
	
	public void add(String punto) throws IOException{
		
		Connection conexion=null;
		try {
			conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/puntos", "root", "root");
			int posespacio=punto.indexOf(" ");
			double punt=Double.valueOf(punto.substring(0, posespacio));
			String nombre=punto.substring(posespacio+1, punto.length());
			
			String sql_insert="INSERT INTO puntos (puntos,nombre) VALUES (?,?)";
			PreparedStatement sentencia=conexion.prepareStatement(sql_insert);
			sentencia.setDouble(1, punt);
			sentencia.setString(2, nombre);
			sentencia.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public String leer() throws IOException {
		String texto="";
		Connection conexion=null;
		try {
			conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/puntos", "root", "root");
			
			String sql_call="SELECT puntos,nombre from puntos";
			
			CallableStatement call=conexion.prepareCall(sql_call);
			ResultSet resultado=call.executeQuery();
			
			while (resultado.next()) {
				texto=texto+resultado.getDouble(1)+" "+resultado.getString(2)+"\n";;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	
			return texto;
	}
	
	
	public synchronized void run() {
		try {
		DataInputStream recibir= new DataInputStream(socketcliente.getInputStream());
		DataOutputStream enviar = new DataOutputStream(socketcliente.getOutputStream());
		String puntuacion=recibir.readUTF();
				if(puntuacion.equals("PUNTUACIONES")) {
					enviar.writeUTF(leer());
				}else {
					add(puntuacion);
					enviar.writeUTF("OK");
				}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
	}
	

