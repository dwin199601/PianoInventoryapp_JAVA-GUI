package UsedPianoApp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PianoDBCreating {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final String DB_URL = "jdbc:derby:PianoDB;create=true";

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
		dropTables(conn);
		 buildTable(conn);
		}
		
		catch (Exception ex) {
	        System.out.println("ERROR: " + ex.getMessage());
	    }
	}
	
	public static void dropTables(Connection conn) {
		
		System.out.println("Checking for existing tables.");
		 try {
			  Statement stmt = conn.createStatement();
			  
			  try {
				  stmt.execute("DROP TABLE PIANO");
				  System.out.println("PIANO table dropped");
			  }
			  catch(SQLException ex) {
				  
			  }
		 }
		 
		 catch(SQLException ex) {
			 System.out.println("ERROR: " + ex.getMessage());
	            ex.printStackTrace();
		 }
		
	}
	
	
	public static void buildTable(Connection conn) {
		
		try {
			
			Statement stmt = conn.createStatement();   
		// Create the table.
		stmt.execute("CREATE  TABLE PIANO(\r\n" + 
			"ProdID	Int	NOT NULL,\r\n" + 
			"Brand	Char(25)	NOT NULL,\r\n" + 
			"Model	Char(25),  \r\n" +
	         "Price	Numeric(15,2)     	NOT NULL,\r\n" + 
			"ManufacturedYear	Char(25)	\r\n" + 
			"    )");
		
		Path fileRead = Paths.get("C:\\MY DOC\\java 2175\\FINAL_EXAM\\insert.txt");
		String inputString = " ";
		InputStream input= null;
		try {
			input = Files.newInputStream(fileRead);
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			inputString = reader.readLine();
			while(inputString!=null) {
				stmt.execute(inputString);
				inputString = reader.readLine();
			}
			System.out.println("The table PIANO was created");
			reader.close();
		}
		catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
	}
		catch(Exception e) {
			System.out.println("ERROR: " + e);
		}

}
}
