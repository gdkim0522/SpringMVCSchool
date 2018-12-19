package Customer.JDBC;

public class Customer {
	private int account;
	private String firstName;
	private String lastName;
	
	public Customer(int account, String firstName, String lastName) {
		this.account = account;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
