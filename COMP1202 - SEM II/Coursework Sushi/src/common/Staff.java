/*
 * @author Andronache Simone
 */
package common;

import java.util.HashMap;
import java.util.Random;

public class Staff extends Model{

	/** The dish this staff is working on. */
	private Dish workingOn;
	
	/** The thread. */
	private StaffThread thread;
	
	/** True, if staff is currently working on a dish. */
	private boolean working = true;
	
	/**
	 * Instantiates a new staff.
	 *
	 * @param name the name
	 */
	public Staff(String name)
	{
		this.name = name;
		this.thread = new StaffThread(this);
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus()
	{
		if(this.workingOn == null)
		{
			return "Idle";
		}
		
		return "Working on: " + this.workingOn.getName();
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the working status of the staff.
	 *
	 * @return the working status
	 */
	public boolean getWorkingStatus()
	{
		return this.working;
	}
	
	/**
	 * Stop working.
	 */
	public void stopWorking()
	{
		this.working = false;
		this.thread.interrupt();
	}
	
	/**
	 * Start working.
	 */
	public void startWorking()
	{
		this.working = true;
		this.thread.start();
	}
	
	/**
	 * Get the object data in string format.
	 *
	 * @return the string
	 */
	public String toPersistanceString()
	{
		return "STAFF:" + this.getName();
	}
	
	public class StaffThread extends Thread {
		
		/** The staff this thread belongs to. */
		public Staff staff;
		
		/**
		 * Instantiates a new staff thread.
		 *
		 * @param staff the staff
		 */
		public StaffThread(Staff staff)
		{
			this.staff = staff;
		}
		
		@Override
		public void run() {
			while(staff.getWorkingStatus())
			{
				if(staff.workingOn == null)
				{
					staff.workingOn = StockManagement.instance.popDishQueue();
					StockManagement.instance.addWorkingOnDish(staff.workingOn);
				}
				else
				{
					int workingTime = (int)(Math.random() * 60 + 20); 
					HashMap<Ingredient, Number> recipe = staff.workingOn.getRecipe();
					boolean satisfiable = true;
					
					for(Ingredient ingredient : recipe.keySet())
					{
						int neededAmount = (Integer) recipe.get(ingredient);
						int availableAmount = (Integer) StockManagement.instance.getAmountIngredient(ingredient);
								
						if(neededAmount > availableAmount)
						{
							int amountOfTimes = ((neededAmount - availableAmount) / ingredient.getRestockAmount()) + 1;
								
							if(StockManagement.instance.ingredientQueueContains(ingredient) == false)
							{
								for(int i = 0; i < amountOfTimes;i++)
								{
									StockManagement.instance.addIngredientQueue(ingredient);
								}
							}
								
							satisfiable = false;
						}
					}
						
					if(satisfiable)
					{
						for(Ingredient ingredient : recipe.keySet())
						{
							int neededAmount = (Integer) recipe.get(ingredient);
									
							StockManagement.instance.decreaseIngredientAmount(ingredient, neededAmount);
						}

						try {
							Thread.sleep(workingTime * 1000);
						} catch (InterruptedException e) {
							StockManagement.instance.removeWorkingOnDish(staff.workingOn);
							staff.workingOn = null;
						}
							
						if(staff.workingOn != null)
						{
							StockManagement.instance.removeWorkingOnDish(staff.workingOn);
							StockManagement.instance.increaseDishAmount(staff.workingOn, staff.workingOn.getRestockAmount());
							staff.workingOn = null;
						}
					}
				}
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					StockManagement.instance.removeWorkingOnDish(staff.workingOn);
					staff.workingOn = null;
				}
			}
		}
	}

}
