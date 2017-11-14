public class User
{
    private String username;
    private String userType;
    private String name;

	/**
		Public constructor of the User class.
		@param username the username of the User
		@param userType one of the following type : user, editor, admin
		@param name the name of the user
	*/
    public User(String username, String userType, String name)
    {
      this.username = username;
      this.userType = userType;
      this.name = name;
    }
	
	/**
		Getter for the username member
		@return String returns the username of the user
	*/
    public String getUsername()
    {
      return username;
    }
	
	/**
		Getter for the userType member
		@return String returns the type of the user
	*/
    public String getUserType()
    {
      return userType;
    }
	
	/**
		Getter for the name member
		@return String returns the name of the user
	*/
    public String getName()
    {
      return name;
    }
	
	/**
		Setter for the userType member
		@param type the type of the user (user, editor, admin)
	*/
    public void setUserType(String type)
    {
      userType = type;
    }

}
