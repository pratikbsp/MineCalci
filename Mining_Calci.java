

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;

public class Mining_Calci {
	static JButton jbutton;
	JPanel bottom;
	JPanel show;
	JTextField t1;
	JTextField t2;
	JTextField t3;
	JTextField t4;
	JTextField t5;
	JTextField t6;
	JTextField t7;
	JLabel text;
	String month;
	int total;
	JPanel newpanel;
	DefaultTableModel model;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	
		
		Mining_Calci m = new Mining_Calci();
		m.go();
	}
	
	public void go() throws Exception{
		JFrame jframe = new JFrame("Mining Calci");
		jframe.setLayout(new BoxLayout(jframe.getContentPane(),BoxLayout.Y_AXIS));
		jbutton = new JButton("Estimate");
		//jbutton.setPreferredSize(new Dimension(100,100));
		JPanel p = new JPanel();
		JPanel top =  new JPanel();
		JButton top1 = new JButton("Input");
		JButton top2 = new JButton("History");
		top.add(top1);
		top.add(top2);
		jframe.add(top);
		bottom = new JPanel();
		bottom.setLayout(new BoxLayout(bottom,BoxLayout.Y_AXIS));
		
		SpringLayout layout = new SpringLayout();
		JPanel center = new JPanel(layout);
		/****************************************************************/
		show = new JPanel();
		text = new JLabel("hello");
		JButton addHistory = new JButton("Add to History");
		
		show.add(text);
		show.add(addHistory);
		jframe.add(show);
		show.setVisible(false);
		
		/****************************************************************/
		JLabel l1 = new JLabel("Month");
		t1 = new JTextField(20);
		center.add(l1);
		center.add(t1);
		layout.putConstraint(SpringLayout.WEST, l1, 10, SpringLayout.WEST,
				center);
		layout.putConstraint(SpringLayout.NORTH, l1, 25, SpringLayout.NORTH,
				center);
		layout.putConstraint(SpringLayout.NORTH, t1, 25, SpringLayout.NORTH,
				center);
		layout.putConstraint(SpringLayout.WEST, t1, 115, SpringLayout.EAST,
				l1);
		/****************************************************************/
		JLabel l2 = new JLabel("Mineral Processing");
		t2 = new JTextField(20);
		center.add(l2);
		center.add(t2);
		layout.putConstraint(SpringLayout.WEST, l2, 10, SpringLayout.WEST,
				center);
		layout.putConstraint(SpringLayout.NORTH, l2, 50, SpringLayout.NORTH,
				center);
		layout.putConstraint(SpringLayout.NORTH, t2, 50, SpringLayout.NORTH,
				center);
		layout.putConstraint(SpringLayout.WEST, t2, 40, SpringLayout.EAST,
				l2);
		/****************************************************************/
		JLabel l3 = new JLabel("Transportation");
		t3 = new JTextField(20);
		center.add(l3);
		center.add(t3);
		layout.putConstraint(SpringLayout.WEST, l3, 10, SpringLayout.WEST,
				center);
		layout.putConstraint(SpringLayout.NORTH, l3, 75, SpringLayout.NORTH,
				center);
		layout.putConstraint(SpringLayout.NORTH, t3, 75, SpringLayout.NORTH,
				center);
		layout.putConstraint(SpringLayout.WEST, t3, 66, SpringLayout.EAST,
				l3);

		/****************************************************************/
		JLabel l4 = new JLabel("Dust Sepration");
		t4 = new JTextField(20);
		center.add(l4);
		center.add(t4);
		layout.putConstraint(SpringLayout.WEST, l4, 10, SpringLayout.WEST,
				center);
		layout.putConstraint(SpringLayout.NORTH, l4, 100, SpringLayout.NORTH,
				center);
		layout.putConstraint(SpringLayout.NORTH, t4, 100, SpringLayout.NORTH,
				center);
		layout.putConstraint(SpringLayout.WEST, t4, 66, SpringLayout.EAST,
				l4);
		/****************************************************************/
		JLabel l5 = new JLabel("Potable Water");
		t5 = new JTextField(20);
		center.add(l5);
		center.add(t5);
		layout.putConstraint(SpringLayout.WEST, l5, 10, SpringLayout.WEST,
				center);
		layout.putConstraint(SpringLayout.NORTH, l5, 125, SpringLayout.NORTH,
				center);
		layout.putConstraint(SpringLayout.NORTH, t5, 125, SpringLayout.NORTH,
				center);
		layout.putConstraint(SpringLayout.WEST, t5, 70, SpringLayout.EAST,
				l5);
		/****************************************************************/
		JLabel l6 = new JLabel("Cooling of Machnes");
		t6 = new JTextField(20);
		center.add(l6);
		center.add(t6);
		layout.putConstraint(SpringLayout.WEST, l6, 10, SpringLayout.WEST,
				center);
		layout.putConstraint(SpringLayout.NORTH, l6, 150, SpringLayout.NORTH,
				center);
		layout.putConstraint(SpringLayout.NORTH, t6, 150, SpringLayout.NORTH,
				center);
		layout.putConstraint(SpringLayout.WEST, t6, 40, SpringLayout.EAST,
				l6);

		/****************************************************************/
		JLabel l7 = new JLabel("Washing");
		t7 = new JTextField(20);
		center.add(l7);
		center.add(t7);
		layout.putConstraint(SpringLayout.WEST, l7, 10, SpringLayout.WEST,
				center);
		layout.putConstraint(SpringLayout.NORTH, l7, 175, SpringLayout.NORTH,
				center);
		layout.putConstraint(SpringLayout.NORTH, t7, 175, SpringLayout.NORTH,
				center);
		layout.putConstraint(SpringLayout.WEST, t7, 101, SpringLayout.EAST,
				l7);

		/****************************************************************/
		bottom.add(center);
		p.add(jbutton);
		bottom.add(p);
		jframe.add(bottom);
		/***************************************************************/
			//getting data from database to be populated
		
		
		/***************************************************************/
		SpringLayout newLayout = new SpringLayout();
		newpanel = new JPanel(newLayout);
		DBDemo db = new DBDemo();
		Object rowData[][] = db.returnRows();
		Object columnNames[] = { "ID", "Month","Total"};
		model = new DefaultTableModel(
		          new String[] { "ID", "Month","Total" }, 0);
		JTable table = new JTable(model);
		this.AddRow();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane newScrollPane = new JScrollPane(table); 
		newpanel.add(newScrollPane);
		jframe.add(newpanel);
		newpanel.setVisible(false);
		/***************************************************************/
		//jframe.add(p);
		//jframe.getContentPane().add(BorderLayout.SOUTH,p);
		jframe.setSize(450, 350);
		//jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		jbutton.addActionListener(new jbutton_listener());
		top1.addActionListener(new top1_listener());
		top2.addActionListener(new top2_listener());
		addHistory.addActionListener(new addHistory_listener());
	}
	//adding rows dynamically to model
	public void AddRow(){
		DBDemo newDemo = new DBDemo();
		try{
			int no = newDemo.noOfRow();
			Object rows[][]= newDemo.returnRows();
			for(int i=0;i<no;i++){
				model.addRow(rows[i]);
			}
		}
		catch(Exception e1){
			System.out.println("Error in Connecting database");
		}
	}
	
	
	class jbutton_listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generate`d method stub
			month = t1.getText();
			String mp_amount =  t2.getText();
			String transportation = t3.getText();
			String dust_amount = t4.getText();
			String potable_water = t5.getText();
			String cooling_water = t6.getText();
			String washing = t7.getText();
			t1.setText(null);
			t2.setText(null);
			t3.setText(null);
			t4.setText(null);
			t5.setText(null);
			t6.setText(null);
			t7.setText(null);
			total = Integer.parseInt(mp_amount)+Integer.parseInt(transportation)+Integer.parseInt(dust_amount)+Integer.parseInt(potable_water)+Integer.parseInt(cooling_water)+Integer.parseInt(washing);
			text.setText("In "+month+" total amount of water used is "+total);
			newpanel.setVisible(false);
			bottom.setVisible(false);
			show.setVisible(true);
		}
		
	}
	
	class top1_listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			newpanel.setVisible(false);
			bottom.setVisible(true);
			show.setVisible(false);
		}
		
	}
	class top2_listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			bottom.setVisible(false);
			//show.setVisible(true);
			newpanel.setVisible(true);
		}
		
	}
	class addHistory_listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			DBDemo db = new DBDemo();
			try{
				db.run();
				db.insert(month, total);
				int no = db.noOfRow();
				model.addRow(new Object[]{no,month,total});
				
			}
			catch(Exception e1){
				System.out.println("Error in Connecting database");
			}
			
			
			newpanel.setVisible(true);
			bottom.setVisible(false);
			show.setVisible(false);
		}
		
	}
	
	
	
	
	
	
	
/***************************************************************************************/
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
			    //System.out.println(noOfRow);
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
		
		public void insert(String month,int total) throws Exception{
			run();
			String command ="INSERT INTO "+ this.tableName+ "(Month,Total) VALUES("+"'"+month+"'"+","+total+")" ;
			 this.executeUpdate(conn,command);
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
		
	}

}
