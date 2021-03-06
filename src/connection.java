import java.sql.*;
import javax.swing.*;

public class connection {
	
	
	
	private static Connection conn=null;
	public static Connection  cnx() {
		
		try {
			  Class.forName("org.sqlite.JDBC");
			 conn = DriverManager.getConnection("jdbc:sqlite:my_database.sqlite");
					return conn;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		
			return null;
		}
	}
	public static Connection getConn() {
		return conn;
	}
	}


