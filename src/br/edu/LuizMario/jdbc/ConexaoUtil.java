package br.edu.LuizMario.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConexaoUtil {
	
	private static ResourceBundle config;
	
	private static ConexaoUtil conexaoUtil;
	
	public ConexaoUtil() {
		config = ResourceBundle.getBundle("config");	
	}
	
	public static ConexaoUtil getInstance(){
		if (conexaoUtil == null)
			{conexaoUtil = new ConexaoUtil();}
	return conexaoUtil;}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(config.getString("br.edu.luizmario.bd.driver"));
		return DriverManager.getConnection(config.getString("br.edu.luizmario.bd.conexao"), config.getString("br.edu.luizmario.bd.usuario"), config.getString("br.edu.luizmario.bd.senha"));
	}	
		
}
