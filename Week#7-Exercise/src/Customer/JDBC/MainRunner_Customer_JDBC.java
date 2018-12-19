package Customer.JDBC;

import java.io.IOException;
import java.util.Scanner;

public class MainRunner_Customer_JDBC {
	public static String NEW_LINE="\n";
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		
		StringBuilder stringBuilder=new StringBuilder();
		String[] menu= {"Add Customer","Update Customer","Delete Customer",
				"Show Customer","Show All","Load Data","Export Data"};
		
		for(int i=0;i<menu.length;++i) {
			int j=i+1;
			if(i==menu.length-1)
				stringBuilder.append(j + " - " + menu[i]);
			else
				stringBuilder.append(j + " - " + menu[i]+ NEW_LINE);
		}
	
		CustomerDAO_JDBC customerDAO=new CustomerDAO_JDBC();
		
		while(true){
			
			System.out.println("Pick an option:");
			System.out.println(stringBuilder);
			int pick=scanner.nextInt();
			
			getMenu(pick, scanner, customerDAO);
			if(pick==9) {
				break;
			}
		}

		scanner.close();
		
	}
	
	static void getMenu(int pick, Scanner scanner,CustomerDAO_JDBC customerDAO) throws IOException, ClassNotFoundException {

		int account_id=0;
		String fname="";
		String lname="";
		
		switch (pick) {
		case 1:
			System.out.println("Enter a customer id");
			account_id=scanner.nextInt();
			System.out.println("Enter a customer first name");
			fname=scanner.next();
			System.out.println("Enter a customer last name");
			lname=scanner.next();
			customerDAO.addCustomer(account_id, fname,lname);
			break;
		case 2:
			System.out.println("Enter a customer id");
			account_id=scanner.nextInt();
			System.out.println("Enter a customer first name");
			fname=scanner.next();
			System.out.println("Enter a customer last name");
			lname=scanner.next();
			customerDAO.updateCustomer(account_id, fname,lname);
			break;
		case 3:			
			System.out.println("Enter a customer id");
			account_id=scanner.nextInt();
			customerDAO.deleteCustomer(account_id);
			break;
		case 4:
			System.out.println("Enter a customer id");
			account_id=scanner.nextInt();
			customerDAO.showCustomer(account_id);
			break;
		case 5:
			customerDAO.showallCustomer();
			break;
		case 6:
			customerDAO.UploadData();;
			break;
		case 7:
			customerDAO.DownloadData();
			break;	
		default:
			break;
		}
		
		scanner.nextLine();

	}
}
