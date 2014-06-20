package db;

import java.sql.*;

public class AddCustSP {
    public static void main(String[] args) {
        String[]temp = {"root",null,"Lion","Snoop"}; 
    	if (args.length < 4)
        {
            System.out.println("USAGE: java ListCustomers username password last-name first-name");
            args=temp;
        }
        String url = "jdbc:mysql://localhost:3306/test";  

        addCust (url, args[0], args[1], args[2], args[3]);
    }

    public static void addCust (String url,
        String username, String password, String lname, String fname)
    {
    	Database db = new Database();
        try {
        	if (!db.call("add_customer (?,?,?)",0, lname, fname))return;
            // Find out who all the sales people are. line 24
            int  iRetVal = db.getInt(1);
            System.out.println("New Customer # "+iRetVal);
        } catch (SQLException sqle) {
            // This is our little Hack Fix for MySQL 5.0 my_signal Hack!!!  Can't wait til MySQL 5.2 to fix this.
            String msg = sqle.toString();  
            System.err.println ("SQL Error: " + msg);
            
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        } finally {
        	db.close();
        }
    }
}


/*DELIMITER $$
 * 
 * CREATE TABLE customer (customer_number INT UNSIGNED PRIMARY KEY AUTO_INCREMENT, lname VARCHAR(50), fname VARCHAR(50));

DROP PROCEDURE IF EXISTS `test`.`add_customer` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_customer`(
    INOUT p_customer_number  int,
    IN p_lname varchar(50),
    IN p_fname varchar(50))
BEGIN
    IF (p_customer_number = 0 OR p_customer_number IS NULL)
    THEN
        INSERT INTO customer (customer_number, lname, fname)
            VALUES (p_customer_number, p_lname, p_fname);

        SELECT last_insert_id() ;
    ELSE
        UPDATE customer SET
            lname = p_lname,
            fname = p_fname
        WHERE customer_number = p_customer_number;
    END IF;
END $$

DELIMITER ;
*/
