/*
 * @author Andronache Simone
 */
package common;

public class User extends Model {

	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The address. */
	private String address;
	
	/** The postcode. */
	private Postcode postcode;
	
	/**
	 * Instantiates a new user.
	 *
	 * @param username the username
	 * @param password the password
	 * @param address the address
	 * @param postcode the postcode
	 */
	public User(String username, String password, String address, Postcode postcode)
	{
		this.setUsername(username);
		this.setPassword(password);
		this.setAddress(address);
		this.setPostcode(postcode);
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.notifyUpdate("username", this.username, username);
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.notifyUpdate("password", this.password, password);
		this.password = password;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.notifyUpdate("address", this.address, address);
		this.address = address;
	}

	/**
	 * Gets the postcode.
	 *
	 * @return the postcode
	 */
	public Postcode getPostcode() {
		return postcode;
	}

	/**
	 * Sets the postcode.
	 *
	 * @param postcode the new postcode
	 */
	public void setPostcode(Postcode postcode) {
		this.notifyUpdate("postcode", this.postcode, postcode);
		this.postcode = postcode;
	}
	
	@Override
	public String getName() {
		return username;
	}
	
	/**
	 * Gets the object data in a string format.
	 *
	 * @return the string
	 */
	public String toPersistanceString()
	{
		String newString = new String("USER:");
		newString += this.getUsername() + ":";
		newString += this.getPassword() + ":";
		newString += this.getAddress() + ":";
		newString += this.getPostcode().getName().toString();
		return newString;
	}
	
}
