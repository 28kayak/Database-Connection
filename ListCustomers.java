package db;

public class ListCustomers 
{
    public static void main(String[] args) 
	{
        String url = "jdbc:mysql://localhost:3306/test";  // The database URL.

        listCustomers (url);
    }

    public static void listCustomers(String url)
    {
    	Database db = new Database();
    	try {

            System.out.println ("Customer Table\n");

            String query = "SELECT user_id, last_name, first_name FROM users254";

            //ResultSet rst = db.getResultSet(query);  // Create & Populate a ResultSet object.
            if (!db.query(query))
            	return;
            
            System.out.println();

             do {  // Loop through the ResultSet row by row.
                System.out.print(db.getInt(1) + "\t");
                System.out.print(db.getString(2) + "\t");
                System.out.println(db.getString(3) + "\t");
            }while(db.fetch());
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
        	db.close();
        }
    }
}
// End of File



