import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.ResultSetMetaData;

/**
 * This class demonstrates how to connect to MySQL and run some basic commands.
 * 
 * In order to use this, you have to download the Connector/J driver and add
 * its .jar file to your build path.  You can find it here:
 * 
 * http://dev.mysql.com/downloads/connector/j/
 * 
 * You will see the following exception if it's not in your class path:
 * 
 * java.sql.SQLException: No suitable driver found for jdbc:mysql://localhost:3306/
 * 
 * To add it to your class path:
 * 1. Right click on your project
 * 2. Go to Build Path -> Add External Archives...
 * 3. Select the file mysql-connector-java-5.1.24-bin.jar
 *    NOTE: If you have a different version of the .jar file, the name may be
 *    a little different.
 *    
 * The user name and password are both "root", which should be correct if you followed
 * the advice in the MySQL tutorial. If you want to use different credentials, you can
 * change them below. 
 * 
 * You will get the following exception if the credentials are wrong:
 * 
 * java.sql.SQLException: Access denied for user 'userName'@'localhost' (using password: YES)
 * 
 * You will instead get the following exception if MySQL isn't installed, isn't
 * running, or if your serverName or portNumber are wrong:
 * 
 * java.net.ConnectException: Connection refused
 */
public class DBDemo {

	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "pratikbsp";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "2RILUUST";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "test";
	
	/** The name of the table we are testing with */
	private final String tableName = "JDBC_TEST";
	
	Connection conn;
	
	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		
		//i haven't used class.forname here. It is used to dyanmically load the jdbc driver
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);

		return conn;
	}

	/**
	 * Run a SQL command which does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException If something goes wrong
	 */
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	/**
	 * Connect to MySQL and do some stuff.
	 * @throws Exception 
	 */
	public void run() throws Exception {

		// Connect to MySQL
		conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		//this.createTable();
		
		//this.insert("Jul", 3500);
		this.returnRows();
		// Create a table
		
		/**
		// Drop the table
		try {
		    String dropString = "DROP TABLE " + this.tableName;
			this.executeUpdate(conn, dropString);
			System.out.println("Dropped the table");
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not drop the table");
			e.printStackTrace();
			return;
		}**/
	}
	
	public int noOfRow(){
		try {
			conn = null;
			conn = this.getConnection();
			System.out.println("Connected to database");
			Object[][] data ;
			Statement stmt = conn.createStatement();
		    
		    ResultSet rs1 =stmt.executeQuery("SELECT COUNT(*) AS NumberOfOrders FROM jdbc_test");
		 //   ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
		    rs1.next();
		    int noOfRow = rs1.getInt(1);
		    return noOfRow;
		}
		catch (SQLException e) {
			System.out.println("ERROR");
			e.printStackTrace();
			return 0;
		}
	}
	
	public void insert(String month,int total) throws Exception{
		String command ="INSERT INTO "+ this.tableName+ "(Month,Total) VALUES("+"'"+month+"'"+","+total+")" ;
		 this.executeUpdate(conn,command);
	}
	
	public Object[][] returnRows() throws Exception{
		
		try {
			conn = null;
			conn = this.getConnection();
			System.out.println("Connected to database");
			Object[][] data ;
			Statement stmt = conn.createStatement();
		    
		    ResultSet rs1 =stmt.executeQuery("SELECT COUNT(*) AS NumberOfOrders FROM jdbc_test");
		 //   ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
		    rs1.next();
		    int noOfRow = rs1.getInt(1);
		    System.out.println(noOfRow);
            data = new Object[noOfRow][3];
            
            ResultSet rs = stmt.executeQuery("SELECT ID, Month, Total FROM "+ this.tableName);
            int count=0;
		    while (rs.next()) {
		       data[count][0]= rs.getInt(1);
		       data[count][1]= rs.getString(2);
		       data[count][2]= rs.getInt(3);
		       //System.out.println(data[count][0]+" "+data[count][1]+" "+data[count][2]);
		       count++;
		    }
			
			
			return data;

		} catch (SQLException e) {
			System.out.println("ERROR");
			e.printStackTrace();
			return null;
		}
		//this.run();
	}

	
	public void createTable(){
		try {
		    String createString =
			        "CREATE TABLE " + this.tableName + " ( " +
			        "ID INTEGER NOT NULL AUTO_INCREMENT, " +
			        "Month varchar(40) NOT NULL, " +
			        "Total varchar(40) NOT NULL, "  +
			        "PRIMARY KEY (ID))";
			this.executeUpdate(conn, createString);
			System.out.println("Created a table");
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}
	}
	
	
	/**
	 * Connect to the DB and do some stuff
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		DBDemo app = new DBDemo();
		//app.run("July",2);
		//app.returnRows();
		app.run();
		app.insert("July", 100);
		System.out.print(app.noOfRow());
	}
	
}
