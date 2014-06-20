package db;


/*
DELIMITER $$

DROP PROCEDURE IF EXISTS `test`.`world_record_count` $$
CREATE PROCEDURE `world_record_count`()
BEGIN
	SELECT * FROM Customer;
	SELECT * FROM Users254;
	SELECT * FROM Authors;
END $$

DELIMITER ;

*/



import java.sql.*;

public class ManyRS {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test";

        listManyResultSets (url, "root", null);
    }

    public static void listManyResultSets (String url, String username, String password)
    {
    	Database db = new Database();
        try {
            boolean moreResultSets = db.call("world_record_count"); 

            while (moreResultSets)
            {
                System.out.println("\n=======================\n");

      	      	int columnCount = db.getColumnCount();

                for (int i = 1; i < columnCount+1; i++) {
                    System.out.print(db.getColumnName(i) + " ");
                }

                System.out.println();

      	      	
               do {
                    for (int i = 1; i < columnCount+1; i++) 
                        System.out.print(db.getString(i) + "\t"); 

                    System.out.print("\n");
                }while(db.fetch()) ;
                moreResultSets = db.getMoreResults();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
        	db.close();
        }
    }
}

