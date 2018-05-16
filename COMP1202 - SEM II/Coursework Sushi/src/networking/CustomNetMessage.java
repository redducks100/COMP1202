/*
 * @author Andronache Simone
 */
package networking;

import java.io.Serializable;
import java.util.ArrayList;

import common.*;

public class CustomNetMessage implements Serializable
{

	/**
	 * Instantiates a new custom net message.
	 */
	public CustomNetMessage()
	{
		
	}
	
	
	/**
	 * The Class RegisterUserMessage.
	 */
	public static class RegisterUserMessage extends CustomNetMessage
	{
		
		/** The user. */
		public User user;
		
		/**
		 * Instantiates a new register user message.
		 *
		 * @param user the user
		 */
		public RegisterUserMessage(User user)
		{
			this.user = user;
		}
	}
	
	/**
	 * The Class LoginUserMessage.
	 */
	public static class LoginUserMessage extends CustomNetMessage
	{
		
		/** The user. */
		public User user;
		
		/**
		 * Instantiates a new login user message.
		 *
		 * @param user the user
		 */
		public LoginUserMessage(User user)
		{
			this.user = user;
		}
	}
	
	/**
	 * The Class GetPostcodesMessage.
	 */
	public static class GetPostcodesMessage extends CustomNetMessage
	{
		
		/** The postcodes. */
		public ArrayList<Postcode> postcodes;
		
		/**
		 * Instantiates a new gets the postcodes message.
		 *
		 * @param postcodes the postcodes
		 */
		public GetPostcodesMessage(ArrayList<Postcode> postcodes)
		{
			this.postcodes = postcodes;
		}
	}
	
	/**
	 * The Class GetDishesMessage.
	 */
	public static class GetDishesMessage extends CustomNetMessage
	{
		
		/** The dishes. */
		public ArrayList<Dish> dishes;
		
		/**
		 * Instantiates a new gets the dishes message.
		 *
		 * @param dishes the dishes
		 */
		public GetDishesMessage(ArrayList<Dish> dishes)
		{
			this.dishes = dishes;
		}
	}
	
	/**
	 * The Class AddOrderMessage.
	 */
	public static class AddOrderMessage extends CustomNetMessage
	{
		
		/** The order. */
		public Order order;
		
		/**
		 * Instantiates a new adds the order message.
		 *
		 * @param order the order
		 */
		public AddOrderMessage(Order order)
		{
			this.order = order;
		}
	}
	
	/**
	 * The Class GetOrdersMessage.
	 */
	public static class GetOrdersMessage extends CustomNetMessage
	{
		
		/** The user. */
		public User user;
		
		/** The orders. */
		public ArrayList<Order> orders;
		
		/**
		 * Instantiates a new gets the orders message.
		 *
		 * @param user the user
		 * @param orders the orders
		 */
		public GetOrdersMessage(User user, ArrayList<Order> orders)
		{
			this.user = user;
			this.orders = orders;
		}
	}
	
	/**
	 * The Class CancelOrderMessage.
	 */
	public static class CancelOrderMessage extends CustomNetMessage
	{
		
		/** The order. */
		public Order order;
		
		/**
		 * Instantiates a new cancel order message.
		 *
		 * @param order the order
		 */
		public CancelOrderMessage(Order order)
		{
			this.order = order;
		}
	}
}