/*
 * @author Andronache Simone
 */
package common;

import java.util.HashMap;

public class Drone extends Model {

	/** The speed. */
	private Number speed;
	
	/** The thread. */
	private DroneThread thread;
	
	/** The working status of the drone. */
	private boolean working = true;
	
	/** Order that is currently being delivered */
	private Order deliveringOrder = null;
	
	/** Ingredient that is currently being resupplied. */
	private Ingredient gettingSupplies = null;
	
	/**
	 * Instantiates a new drone.
	 *
	 * @param speed the speed
	 */
	public Drone(Number speed)
	{
		this.name = "Drone" + (1 + (int)(Math.random() * ((100000 - 1) + 1)));
		this.speed = speed;
		if(this.speed.intValue() <= 0) this.speed = 1;
		this.thread = new DroneThread(this);
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus()
	{
		if(deliveringOrder != null)
		{
			return "Delivering " + deliveringOrder.getName();
		}
		else if(gettingSupplies != null)
		{
			return "Resupplying " + gettingSupplies.getName();
		}
		
		return "Idle";
	}
	
	/**
	 * Gets the speed.
	 *
	 * @return the speed
	 */
	public Number getSpeed() {
		return this.speed;
	}

	/**
	 * Sets the speed.
	 *
	 * @param speed the new speed
	 */
	public void setSpeed(Number speed) {
		if(speed.intValue() <= 0) speed = 1;
		this.notifyUpdate("speed", this.speed, speed);
		this.speed = speed;
	}
	
	/**
	 * Stop working.
	 */
	public void stopWorking()
	{
		this.working = false;
		this.gettingSupplies = null;
		this.deliveringOrder = null;
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
	 * Gets the object data in a string format.
	 *
	 * @return the string
	 */
	public String toPersistanceString()
	{
		return "DRONE:" + this.getSpeed().toString();
	}
	
	public class DroneThread extends Thread
	{
		
		/** The drone this thread belongs to. */
		public Drone drone;
		
		/**
		 * Instantiates a new drone thread.
		 *
		 * @param drone the drone
		 */
		public DroneThread(Drone drone)
		{
			this.drone = drone;
		}
		
		@Override
		public void run() {
			while(drone.working)
			{
				//Check the ingredient queue (if there are any ingredients who need to be restocked)
				drone.gettingSupplies = StockManagement.instance.popIngredientQueue();
				
				if(drone.gettingSupplies != null)
				{
					int workingTime = (drone.gettingSupplies.getSupplier().getDistance() / drone.getSpeed().intValue()) * 10;
					
					try {
						Thread.sleep(workingTime * 1000);
					} catch (InterruptedException e) {
						drone.gettingSupplies = null;
					}
					
					if(drone.working == false) break;
					
					if(drone.gettingSupplies != null)
					{
						StockManagement.instance.increaseIngredientAmount(drone.gettingSupplies, drone.gettingSupplies.getRestockAmount());
					}
				}
				else 
				{
					//Check the order queue (if there is any order which needs to be delivered)
					drone.deliveringOrder = DeliveryManagement.instance.popOrderQueue();
					
					if(drone.deliveringOrder != null)
					{
						int workingTime = ((drone.deliveringOrder.getUser().getPostcode().getDistance().intValue() / drone.getSpeed().intValue()) + 1) * 10;
						boolean satisfiable = true;
						
						HashMap<Dish, Number> dishes = drone.deliveringOrder.getDishes();
						
						for(Dish dish : dishes.keySet())
						{
							int neededAmount = dishes.get(dish).intValue();
							int currentAmount = StockManagement.instance.getAmountDish(dish).intValue();
							
							//Make sure we have enough dishes for the order
							if(neededAmount > currentAmount)
							{
								int amountOfTimes = ((neededAmount - currentAmount) / dish.getRestockAmount()) + 1;
								
								if(StockManagement.instance.dishQueueContains(dish) == false && StockManagement.instance.workingOnDish(dish) == false)
								{
									//If there are not enough dishes add the required amount to the queue and wait 
									for(int i = 0; i < amountOfTimes;i++)
									{
										StockManagement.instance.addDishQueue(dish);
									}
								}
								
								satisfiable = false;
							}
						}
						
						if(satisfiable)
						{
							//Remove the dishes from the stock, wait for workingTime seconds and then set the order completed status to true
							for(Dish dish : dishes.keySet())
							{
								int neededAmount = dishes.get(dish).intValue();
								StockManagement.instance.decreaseDishAmount(dish, neededAmount);
							}
							try {
								Thread.sleep(workingTime * 1000);
							} catch (InterruptedException e) {
								drone.deliveringOrder = null;
							}
							
							if(drone.working == false) break;
							
							if(drone.deliveringOrder != null)
							{
								drone.deliveringOrder.setCompleted(true);
							}
							
							drone.deliveringOrder = null;
						}
						else
						{
							DeliveryManagement.instance.addOrderQueue(drone.deliveringOrder);
							drone.deliveringOrder = null;
						}
					}
				}
				try {
					Thread.sleep(100);
				} 
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
