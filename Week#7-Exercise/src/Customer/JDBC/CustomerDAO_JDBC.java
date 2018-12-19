package Customer.JDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import CustomerHashMap.Customer;
import CustomerHashMap.FileReaderCode;


public class CustomerDAO_JDBC {
	List<Customer> customers=new LinkedList<Customer>();
	String url = "jdbc:Oracle:thin:@localhost:1521:orcl"; 
	String user = "system"; 
	String pass = "password"; 
	String sql="";
	
	String filename="src/customers.txt";
	String CD=",";
	String NEW_LINE="\n";
	
	//JDBC Driver to connect to Database to run SQL Statements.	
	int addCustomer(int id, String fname, String lname) throws ClassNotFoundException {

        //Creating the connection 
		Connection con=null; 
      
		Class.forName("oracle.jdbc.driver.OracleDriver");
      
		try
		{ 
          //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

          //Reference to connection interface 
          con = DriverManager.getConnection(url,user,pass); 

          
          String sql="Insert into customer(Account, fname, lname) values(?,?,?)";
          
          Statement st = con.createStatement();  
          PreparedStatement ps=con.prepareStatement(sql);
          ps.setInt(1, id);
          ps.setString(2, fname);
          ps.setString(3, lname);

          int result= ps.executeUpdate();
          
          //step5 close the connection object  

          con.close();
          return result;

      } 
      catch(Exception ex) 
      { 
          System.err.println(ex); 
      } 
		
		return -1;
	}
	
	int updateCustomer(int id, String fname, String lname) throws ClassNotFoundException {

        //Creating the connection 
		Connection con=null; 
      
		Class.forName("oracle.jdbc.driver.OracleDriver");
      
		try
		{ 
          //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

          //Reference to connection interface 
          con = DriverManager.getConnection(url,user,pass); 

          
          String sql="update customer set fname=?, lname=? where account=?";
          
          Statement st = con.createStatement();  
          PreparedStatement ps=con.prepareStatement(sql);
          ps.setString(1, fname);
          ps.setString(2, lname);
          ps.setInt(3, id);

          int result= ps.executeUpdate();
          
          //step5 close the connection object  

          con.close();
          return result;

      } 
      catch(Exception ex) 
      { 
          System.err.println(ex); 
      } 
		
		return -1;
	}
	
	int deleteCustomer(int id) throws ClassNotFoundException {

        //Creating the connection 
		Connection con=null; 
      
		Class.forName("oracle.jdbc.driver.OracleDriver");
      
		try
		{ 
          //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

          //Reference to connection interface 
          con = DriverManager.getConnection(url,user,pass); 

          
          String sql="delete customer where account=?";
          
          Statement st = con.createStatement();  
          PreparedStatement ps=con.prepareStatement(sql);
          ps.setInt(1, id);

          int result= ps.executeUpdate();
          
          if(result>0)
        	  System.out.println("DELETION WORKED");
          else 
        	  System.out.println("DELETION NOT WORKED");
          //step5 close the connection object  

          con.close();
          return result;

      } 
      catch(Exception ex) 
      { 
          System.err.println(ex); 
      } 
		
		return -1;
	}
	
	
	int deleteAllCustomer() throws ClassNotFoundException {

        //Creating the connection 
		Connection con=null; 
      
		Class.forName("oracle.jdbc.driver.OracleDriver");
      
		try
		{ 
          //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

          //Reference to connection interface 
          con = DriverManager.getConnection(url,user,pass); 

          
          String sql="delete customer";
          
          Statement st = con.createStatement();  
          PreparedStatement ps=con.prepareStatement(sql);

          int result= ps.executeUpdate();
          
          if(result>0)
        	  System.out.println("DELETION ALL CUSTOMER");
          else 
        	  System.out.println("DELETION NOT WORKED");
          //step5 close the connection object  

          con.close();
          return result;

      } 
      catch(Exception ex) 
      { 
          System.err.println(ex); 
      } 
		
		return -1;
	}
	
	
	Customer showCustomer(int id) throws ClassNotFoundException {

        //Creating the connection 
		Connection con=null; 
      
		Class.forName("oracle.jdbc.driver.OracleDriver");
      
		try
		{ 
          //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

          //Reference to connection interface 
          con = DriverManager.getConnection(url,user,pass); 
          sql="select * from customer where account=" + id;
          Statement st = con.createStatement();  
          ResultSet rs=st.executeQuery(sql);  
          
          if(rs!=null) {          
	          while(rs.next()) {
	        		int account_id=rs.getInt(1);
	        		String fname=rs.getString(2);
	        		String lname=rs.getString(3);
					
	        		System.out.format("ID= %d, First=%s, Last=%s %n", account_id,fname,lname);
					return new Customer(account_id, fname, lname);
	          }
          }else {
        	  System.out.println("RS is NULL!!!!");
        	  return null;
          }

      } 
      catch(Exception ex) 
      { 
          System.err.println(ex); 
      } 
		
		return null;
	}
	
	List<Customer> showallCustomer() throws ClassNotFoundException {

        //Creating the connection 
		Connection con=null; 
      
		Class.forName("oracle.jdbc.driver.OracleDriver");
      
		try
		{ 
          //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

          //Reference to connection interface 
          con = DriverManager.getConnection(url,user,pass); 

          sql="select * from customer";
          Statement st = con.createStatement();  
          ResultSet rs=st.executeQuery(sql);  
                 
          //System.out.println("sTART");
          
          while(rs.next()) {
        		int account_id=rs.getInt(1);
        		String fname=rs.getString(2);
        		String lname=rs.getString(3);
        		
        		System.out.format("ID= %d, First=%s, Last=%s %n", account_id,fname,lname);
        		
				customers.add(new Customer(account_id, fname, lname));
				//System.out.println(id+"||"+name+"||"+quantity_in_stock+"||"+price);  
          }
          
          System.out.println();
          //System.out.println("sTAEND");

          return customers;

      } 
      catch(Exception ex) 
      { 
          System.err.println(ex); 
      } 
		
		return null;
	}
	
	//Load data into Table
	void UploadData() throws IOException, ClassNotFoundException {
		System.out.println("Loading Data...");
		String file_data=FileReaderCode.readCsvFile(filename);
		//System.out.println(file_data);
		
		String[] lines=file_data.split(NEW_LINE);
		for (String line : lines) {
			String[] customer_data=line.split(CD);
			int account_id=Integer.parseInt(customer_data[0]);
			String fname=customer_data[1];
			String lname=customer_data[2];
			
			customers.add(new Customer(account_id, fname, lname));
		}
		
		deleteAllCustomer();
		for (Customer customer : customers) {
			addCustomer(customer.getAccount(), customer.getFirstName(), customer.getLastName());
		}
		
		
		System.out.println("Data imported successfully Into table!!!\n");
		showallCustomer();
	}
	
	
	//Write data into file
	void DownloadData() throws IOException, ClassNotFoundException {
		String output="";
		int i=1;
		
		customers.clear();
		customers=showallCustomer();
		
		for (Customer customer : customers) {
			
			if(i==customers.size())
				output=output.concat(customer.getAccount()+CD+customer.getFirstName()+CD+customer.getLastName());
			else
				output=output.concat(customer.getAccount()+CD+customer.getFirstName()+CD+customer.getLastName()+NEW_LINE);			
			i++;
		}
		
		FileReaderCode.WriteFileCSV(filename, output);
		
		System.out.println("File Download Completed!!!!");
	}
}
