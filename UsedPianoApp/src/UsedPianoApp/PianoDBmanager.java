package UsedPianoApp;

import static java.nio.file.StandardOpenOption.*;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PianoDBmanager {
	
    public final static String DB_URL = "jdbc:derby:PianoDB";
    
    public static ArrayList<String> getProd() throws SQLException {
		
		Connection conn = DriverManager.getConnection(DB_URL);
		conn = DriverManager.getConnection(DB_URL);
		
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); 
	
		String quary = "SELECT ProdID, Brand, Model, Price, "
				+ "ManufacturedYear FROM PIANO";
		ResultSet resultSet = stmt.executeQuery(quary);
		ArrayList<String> Prod = new ArrayList<>();
		
		
		resultSet.last();
		int numRows = resultSet.getRow(); 
		resultSet.first();
	
		Prod.add("ID  " +"Brand" + "   Model" + "   Price$" + "  Year");
		
		 for (int index = 0; index < numRows; index++) {
	           
			
			 Prod.add(resultSet.getString(1).trim() + " " + resultSet.getString(2).trim() + " " + resultSet.getString(3).trim() + " " + resultSet.getString(4).trim() +
					 " " + resultSet.getString(5).trim());
			
	            // Go to the next row in the result set.
	            resultSet.next();
	        }
		System.out.println(Prod);
		conn.close();
		stmt.close();
		
		
		return Prod;
	}

   




}
