package Products;

public class CustomerVO {

	private String customerId;
	private String password;
	private String customerName;
	private String contact;
	
	
	public CustomerVO() {}

	
	public CustomerVO(String customerId, String password, String custormerName, String contact) {
		super();
		this.customerId = customerId;
		this.password = password;
		this.customerName = custormerName;
		this.contact = contact;
	}

	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	
	@Override
	public String toString() {
		return "CustomerVO [customerId=" + customerId + ", password=" + password + ", customerName=" + customerName
				+ ", contact=" + contact + "]";
	}
	
	
	
	
	
	
	
}//end CustomerVO
