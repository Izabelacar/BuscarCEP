package cep;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {
	public static  Connection conector() {
		
	Connection conexao = null;
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/dados";
	String user = "root";
	String password = "bela";
	
	try {
		Class.forName(driver);
		conexao = (Connection) DriverManager.getConnection(url,user, password);
		return conexao;
	}  catch (Exception e) {
		System.out.println(e);
		return null;
	}
	
	
		
	}

}
