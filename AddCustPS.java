package db;

import java.sql.*;

public class AddCustPS {
    public static void main(String[] args) {
        String[]temp = {null,null,"Sheen","Charlie"}; 
    	if (args.length < 4)
        {
            System.out.println("USAGE: java ListCustomers username password last-name first-name");
            args=temp;
        }
        String url = "jdbc:mysql://localhost:3306/test";  
        String driver="com.mysql.jdbc.Driver";

        addCust (driver, url, args[0], args[1], args[2], args[3]);
    }

    public static void addCust (String driver, String url,
        String username, String password, String lname, String fname)
    {
    	String sql = "users254 (last_name, first_name)";
    	Database db  = new Database();
    	try {
            int id = db.insert(sql, lname, fname);
            System.out.printf ("New ID: %d\n", id);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
        	db.close();
        }
    }
}

