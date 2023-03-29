package project.response;

public class PasswordSpecificResponse {
	
	private String password;

	public PasswordSpecificResponse(String password) {
		super();
		this.password = password;
	}

	public PasswordSpecificResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "PasswordSpecificResponse [password=" + password + "]";
	}
	
	

}
