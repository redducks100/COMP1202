public class Calculator {
        Double x;
        /*
        * Chops up input on ' ' then decides whether to add or multiply.
        * If the string does not contain a valid format returns null.
        */
        public Double x(String x){
        		String[] parsedString = x.split(" ");
        		
        		if(parsedString.length != 3)
        		{
        			return null;
        		}
        		
        		try {
        			this.x = Double.parseDouble(parsedString[0]);
        		}
        		catch(Exception e)
        		{
        			return null;
        		}
        		
        		switch(parsedString[1])
        		{
        			case "+":
        				try {
        					Double secondDouble = Double.parseDouble(parsedString[2]);
        					return x(secondDouble);
        				}
        				catch(Exception e)
        				{
        					return null;
        				}
        			case "x":
        				try {
        					Double secondDouble = Double.parseDouble(parsedString[2]);
        					return x(secondDouble.doubleValue());
        				}
        				catch(Exception e)
        				{
        					return null;
        				}
        			default:
        				return null;
        		}
        }

        /*
        * Adds the parameter x to the instance variable x and returns the answer as a Double.
        */
        public Double x(Double x){
                System.out.println("== Adding ==");
                return new Double(this.x + x);
        }

        /*
        * Multiplies the parameter x by instance variable x and return the value as a Double.
        */
        public Double x(double x){
                System.out.println("== Multiplying ==");
                return new Double(this.x * x);
        }
}