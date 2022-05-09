package model;
import java.sql.*;

public class User {
	
	// Create a method to contain the code to connect to the MySQL DB 
	public Connection connect() 
	{ 
		 Connection con = null; 
		 
		 try 
		 { 
			 Class.forName("com.mysql.jdbc.Driver"); 
			 
			//Provide the correct details: DBName, username and password 
			 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdb", 
			 "root", ""); 
			 
			 //For testing
			 System.out.print("Successfully connected"); 
		 }
		 
		 catch(Exception e) 
		 { 
			 e.printStackTrace(); 
		 } 
		 
		 return con; 
	}
	
	
	//Create method name insertUser to insert new user details
	public String insertUser(String code,  String name, String nic, String phone, String email, String address, String password )
	{ 
		 String output = ""; 
		 
		 try
		 { 
			 //Call the connect method to verify the database connection .
			 Connection con = connect(); 
			 
			 if (con == null) 
			 { 
				 return "Error while connecting to the database"; 
			 } 
			 
			 // create a prepared statement
			 String query = "insert into user (`userID`, `userCode`,`userName`,`nic`,`phone`,`email`,`address`, `password`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, code); 
			 preparedStmt.setString(3, name); 
			 preparedStmt.setString(4, nic); 
			 preparedStmt.setString(5, phone);
			 preparedStmt.setString(6, email);
			 preparedStmt.setString(7, address);
			 preparedStmt.setString(8, password);
			 
	
			//execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 
			
			 
			 String newUsers = readUser(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newUsers + "\"}"; 

			 } 
		 
			catch (Exception e) 
			 { 
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
				  
				 System.err.println(e.getMessage()); 
			 } 
			return output; 
	}


	//Create method name readUser to read all user details
	public String readUser()
	{ 
		 String output = ""; 
			try
			{ 
				//Call the connect method to verify the database connection .
				 Connection con = connect();
				 
				 if (con == null) 
				 { 
					 return "Error while connecting to the database for reading."; 
				 } 
				 
				
				// Prepare the html table to be displayed all user details.
					 output = "<table border='1'><tr>"
						+ "<th>User ID</th>"
						+ "<th>User Code</th>"
						+ "<th>User Name</th>" 
						+"<th>User NIC</th>"  
						+"<th>Phone</th>" 
						+"<th>Email</th>" 
						+"<th>Address</th>" 
						+"<th>Password</th>" 
						+"<th>Update</th><th>Remove</th></tr>"; 
						 
						 
				 String query = "select * from user"; 
				 Statement stmt = con.createStatement(); 
				 ResultSet rs = stmt.executeQuery(query); 
				 
				 
				 // iterate through the rows in the result set
				 while (rs.next()) 
				 { 
					 String userID = Integer.toString(rs.getInt("userID")); 
					 String userCode = rs.getString("userCode"); 
					 String userName = rs.getString("userName"); 
					 String nic = rs.getString("nic"); 
					 String phone = rs.getString("phone"); 
					 String email = rs.getString("email"); 
					 String address = rs.getString("address"); 
					 String password = rs.getString("password"); 
				
				 
					// Add a row into the html table
					 output += "<tr><td>" + userID + "</td>";
					 output	+= "<td>" +userCode + "</td>";
					 output += "<td>" + userName + "</td>"; 
					 output += "<td>" + nic + "</td>"; 
					 output += "<td>" + phone + "</td>"; 
					 output += "<td>" + email + "</td>";
					 output += "<td>" + address + "</td>";
					 output += "<td>" + password + "</td>";
				 
				
					 // buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update'"
					 		+ "class='btnUpdate btn btn-secondary' data-userid='" + userID+ "'></td>"
						
						 + "<td><input name='btnRemove' type='button' value='Remove'"
						 + "class='btnRemove btn btn-danger' data-userid='" + userID + "'></td></tr>";
				
				 } 
				 
				 con.close(); 
				 
				 // Complete the html table
				 output += "</table>"; 
			} 
			
			catch (Exception e) 
			{ 
				 output = "Error while reading the items."; 
				 System.err.println(e.getMessage()); 
			} 
				return output; 
	}




	//Create method name updateUser to update user details
	public String updateUser(String ID, String code, String name, String nic, String phone, String email, String address, String password ) 
	{ 
		String output = ""; 
			try
			{     
				//Call the connect method to verify the database connection .
				 Connection con = connect(); 
				 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for updating."; 
				 } 
				 
				 // create a prepared statement
				 	String query = "UPDATE user SET userCode=?,userName=?,nic=?,phone=? , email=?, address=?, password=?  WHERE userID=?";
				 	
				 	PreparedStatement preparedStmt = con.prepareStatement(query); 
				 	
				 	
				 // binding values
				 preparedStmt.setString(1, code); 
				 preparedStmt.setString(2, name); 
				 preparedStmt.setString(3, nic); 
				 preparedStmt.setString(4, phone);
				 preparedStmt.setString(5, email);
				 preparedStmt.setString(6, address);
				 preparedStmt.setString(7, password);
				 preparedStmt.setInt(8, Integer.parseInt(ID)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 
				 con.close(); 
				 
				 String newUser = readUser(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
				 newUser + "\"}"; 
			} 
			
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
				 System.err.println(e.getMessage()); 
			 } 
			
			 return output; 
	} 

	//Create method name deleteUser to delete a user
	public String deleteUser(String userID) 
	{ 
		String output = ""; 
			try
			{ 
				//Call the connect method to verify the database connection .
				Connection con = connect(); 
				
				if (con == null) 
				{ 
					return "Error while connecting to the database for deleting."; 
				} 
				
				// create a prepared statement
				String query = "delete from user where userID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(userID)); 
 
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				
				String newUser = readUser(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
				 newUser + "\"}"; 
			}
			
			catch (Exception e) 
			{ 
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
				
				System.err.println(e.getMessage()); 
			} 
			return output; 
	}

}
