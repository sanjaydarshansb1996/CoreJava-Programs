
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;

public class Example {
	
	static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	static final String DB_URL="jdbc:mysql//localhost/mysqlrelearn";

	
	//Database credentials
	static final String USER="root";
	static final String PASS="";
	
	public static void main(String[] args) {
		Connection conn=null;
		Statement stmt=null;
		
		try {
			//step 2:Register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//step 3:open a connection
			System.out.println("Connection to database");
			conn=DriverManager.getConnection(DB_URL,"root","");
			//step4:execute a query
			System.out.println("creating statement");
			stmt=conn.createStatement();
			String sql;
			
			sql="SELECT emp_id,emp_name FROM sanjay_employee";
			ResultSet rs=stmt.executeQuery(sql);
			
			//step5:extract data from result set
			while(rs.next()) {
				int id=rs.getInt("emp_id");
				String empname=rs.getString("emp_name");
				//display values
				System.out.print("employee id"+id);
				System.out.println("employee name"+empname);

				
			}
			//step6:clean up environment
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se) {
			//handle errors 
			se.printStackTrace();
		}catch(Exception e) {
			//handle errors for Class.forName
			e.printStackTrace();
			
		}finally {
			//finally block used to close resources
			try {
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2) {
				//nothing we can do
			}
			try {
				if(conn!=null)
					conn.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	System.out.println("goodbye!!");	
	}

}
