import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
	
	  //PART 1
	  System.out.println("Part 1:\n");
      Scanner scanner = new Scanner(System.in);
      System.out.println("Choose a number: ");
      int number = scanner.nextInt(); //reading a number from the console
      scanner.close();
	  
      String lineToPrint="Times table:\n"; //printing the table for that number up to 20
      for(int i=1;i<=20;i++)
      {
        lineToPrint += i*number;
        if(i<20)
        {
          lineToPrint += ", ";
        }
      }
      System.out.println(lineToPrint);

      int sum=0;
      int current=1;
      while(sum<500) //check if the sum is less than 500
      {
          sum += current; //add the current number
          current += 1; //add one to the current number
      }
      System.out.println("Sum to 500 needed "+ Integer.toString(current-1) + " iterations."); //Integer.toString() converts an integer to a String
	  System.out.println("");
	  //PART 2
	  System.out.println("Part 2:\n");
	  UserGroup group = new UserGroup();
	  group.addSampleData();
	  group.printUsernames();
	  System.out.println("");
	  //PART 3
	  System.out.println("Part 3:\n");
	  UserGroup administrators = new UserGroup();
	  Iterator<User> iterator = group.getUserIterator();
	  int size = 0;
	  while(iterator.hasNext())
	  {
		  User currentUser = iterator.next();
		  if(currentUser.getUserType() == "admin")
		  {
			  administrators.addUser(currentUser);
			  size++;
		  }
	  }
	  administrators.printUsernames();
	  administrators.getUser(size-1).setUserType("user");

	  group.printUsernames();
	  administrators.printUsernames();
	  //Now the administrators group will contain an user which is not an admin. One fix would be to iterate again through the administrators list and remove any non-admin user.
    }
}
