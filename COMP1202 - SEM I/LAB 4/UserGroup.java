import java.util.*;

public class UserGroup
{
    private ArrayList<User> users;

	/**
		Public constructor of the UserGroup class.
		Creates an empty array of type user.
	*/
    public UserGroup()
    {
      users = new ArrayList<User>();
    }

	/**
		Helper method to add multiple users for testing purposes.
	*/
    public void addSampleData()
    {
      for(int i=0;i<10;i++) //add 10 users
      {
        User user = new User("user" + Integer.toString(i),((i%2==0)?"admin":"user"),"Simone"); //if i is even the user will be an admin if not it's a regular user
        users.add(user);
      }
    }
	
	/**
		Getter method for the user at the specified index in the array
		@param index the location of the user
		@return User it return the User at the index position
	*/
    public User getUser(int index)
    {
      return users.get(index);
    }
	
	/**
		Removes the first user in the list.
	*/
	public void removeFirstUser()
	{
		users.remove(0);
	}
	
	/**
		Removes the last user in the list.
	*/
	public void removeLastUser()
	{
		users.remove(users.size()-1);
	}
	
	/**
		Getter method for the users list iterator.
		@return Iterator<User> an iterator of type User
	*/
	public Iterator<User> getUserIterator()
	{
		return users.iterator();
	}
	
	/**
		Removes all users with the given username.
		@param username the username you want to remove
	*/
	public void removeUser(String username)
	{
		Iterator<User> current = users.iterator();
		while(current.hasNext())
		{
			User currentUser = current.next();
			if(currentUser.getUsername() == username)
			{
				current.remove();
			}
		}
	}
	
	/**
		Adds a new user to the list.
		@param newUser a user to add
	*/
	public void addUser(User newUser)
	{
		users.add(newUser);
	}
	
	/**
		Helper method to print the usernames and types of the current users in the list.
	*/
    public void printUsernames()
    {
      for(User current : users)
      {
        System.out.println(current.getUsername() + " " + current.getUserType());
      }
    }
}
