package Servidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CREARBBDD {

	public static void main(String[] args) {
		Connection conexion=null;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","root");
			String sql_drop_db="DROP DATABASE `puntos`";
			Statement sentencia_drop=conexion.createStatement();
			sentencia_drop.execute(sql_drop_db);
			String sql_create_db="CREATE DATABASE puntos";
			Statement sentencia_create=conexion.createStatement();
			sentencia_create.execute(sql_create_db);
			conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/puntos", "root", "root");
			String sql_table="CREATE TABLE `puntos`.`puntos` ( `puntos` DECIMAL(10.2) NOT NULL , `nombre` VARCHAR(10) NOT NULL ) ENGINE = InnoDB;\r\n"
					+ "";
			Statement sentencia_tabla=conexion.createStatement();
			sentencia_tabla.execute(sql_table);
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
}
