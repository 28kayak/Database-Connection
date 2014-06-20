package db;
/*
-- Below is the stored procedure to create in MySQL
DELIMITER $$

DROP PROCEDURE IF EXISTS `test`.`getEmpData` $$
CREATE PROCEDURE `test`.`getEmpData` ()
BEGIN
  SELECT * FROM customer;
END $$

DELIMITER ;

-- Now the Java Part:
*/

public class ListEmp
{
    public static void main(String[] args)
	{
        String url = "jdbc:mysql://localhost:3306/test";

        listEmp (url, "root", null);
    }

    public static void listEmp (String url, String username, String password)
    {
    	Database db = new Database();
        try {
            // System.out.println("DB Name: "+db.getMetaData().getDatabaseProductName());	
            //System.out.println("Driver: "+db.getMetaData().getDriverName());

            System.out.println ("Employee Table\n");

             
            if (!db.call("getEmpData")) // No ResultSet returned...
                return;

            System.out.println("\n=======================\n");

            do {
                System.out.print(db.getInt("customer_number") + "\t");
                System.out.print(db.getString("LNAME") + "\t");
                System.out.print(db.getString("FNAME") + "\t\n");
            } while(db.fetch());
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
        	db.close();
        }
    }
}



