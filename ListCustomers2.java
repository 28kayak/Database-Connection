package db;

import java.sql.*;

public class ListCustomers2 {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/test";  
        String driver="com.mysql.jdbc.Driver";

        listCustomers (driver, url, null, null);
    }

    public static void listCustomers(String driver, String url,
        String username, String password)
    {
    	Database db = new Database();
    	try {
            System.out.println ("Customer Table\n");

            String query = "SELECT * FROM users254";

            if(!db.query(query)) return;

            int columnCount = db.getColumnCount();

            for (int i = 1; i < columnCount+1; i++) {
                System.out.print(db.getColumnName(i) + " ");
            }

            System.out.println();

           do {
	            for (int i = 1; i < columnCount+1; i++) 
                	System.out.print(db.getString(i) + "\t");
	            System.out.println();	            
            } while(db.fetch());
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
        	db.close();
        }
    }
}
// End of File

