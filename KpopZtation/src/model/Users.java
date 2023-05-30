package model;

public class Users {
	private String UserID, UserName, UserPassword, UserEmail, UserAddress, UserGender, UserRole;

	public Users(String userID, String userName, String userPassword, String userEmail, String userAddress,
			String userGender, String userRole) {
		super();
		UserID = userID;
		UserName = userName;
		UserPassword = userPassword;
		UserEmail = userEmail;
		UserAddress = userAddress;
		UserGender = userGender;
		UserRole = userRole;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserPassword() {
		return UserPassword;
	}

	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}

	public String getUserEmail() {
		return UserEmail;
	}

	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}

	public String getUserAddress() {
		return UserAddress;
	}

	public void setUserAddress(String userAddress) {
		UserAddress = userAddress;
	}

	public String getUserGender() {
		return UserGender;
	}

	public void setUserGender(String userGender) {
		UserGender = userGender;
	}

	public String getUserRole() {
		return UserRole;
	}

	public void setUserRole(String userRole) {
		UserRole = userRole;
	}
	
	
}
