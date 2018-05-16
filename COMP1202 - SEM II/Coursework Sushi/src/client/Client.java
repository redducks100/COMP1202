/**
 * @author Andronache Simone
 **/
package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import common.Dish;
import common.Ingredient;
import common.Model;
import common.Order;
import common.Postcode;
import common.UpdateListener;
import common.User;
import networking.Communication;
import networking.CustomNetMessage.*;


public class Client extends Model implements ClientInterface {
	
	/** The communication link object */
	private Communication communication = null;
	
	/** The basket object*/
	private HashMap<Dish, Number> basket = null;
	
	/**
	 * Instantiates a new client.
	 */
	public Client()
	{
		try {
			this.communication = new Communication("localhost", 4444);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Couldn't find host. (Server is not opened)", "ERROR", JOptionPane.OK_OPTION);
			System.exit(0);
		}
		this.basket = new HashMap<Dish, Number>();
	}
	
	@Override
	public String getName() {
		return "Client";
	}

	@Override
	public User register(String username, String password, String address, Postcode postcode) {
		User newUser = new User(username, password, address, postcode);
		RegisterUserMessage message = new RegisterUserMessage(newUser);
		Communication.sendMessage(communication.client.outputStream, message);
		message = (RegisterUserMessage) Communication.receiveMessage(communication.client.inputStream);
		
		return message.user;
	}

	@Override
	public User login(String username, String password) {
		User newUser = new User(username, password, null, null);
		LoginUserMessage message = new LoginUserMessage(newUser);
		Communication.sendMessage(communication.client.outputStream, message);
		message = (LoginUserMessage) Communication.receiveMessage(communication.client.inputStream);
		
		return message.user;
	}

	@Override
	public List<Postcode> getPostcodes() {
		GetPostcodesMessage message = new GetPostcodesMessage(null);
		Communication.sendMessage(communication.client.outputStream, message);
		message = (GetPostcodesMessage) Communication.receiveMessage(communication.client.inputStream);
		
		return message.postcodes;
	}

	@Override
	public List<Dish> getDishes() {
		GetDishesMessage message = new GetDishesMessage(null);
		Communication.sendMessage(communication.client.outputStream, message);
		message = (GetDishesMessage) Communication.receiveMessage(communication.client.inputStream);
		
		return message.dishes;
	}

	@Override
	public String getDishDescription(Dish dish) {
		return dish.getDescription();
	}

	@Override
	public Number getDishPrice(Dish dish) {
		return dish.getPrice();
	}

	@Override
	public Map<Dish, Number> getBasket(User user) {
		return this.basket;
	}

	@Override
	public Number getBasketCost(User user) {
		int sum = 0;
		
		for(Dish dish : this.basket.keySet())
		{
			sum += dish.getPrice() * this.basket.get(dish).intValue();
		}
		
		return sum;
	}

	@Override
	public void addDishToBasket(User user, Dish dish, Number quantity) {
		if(quantity.intValue() <= 0)
			return;
		this.basket.put(dish, quantity);
	}

	@Override
	public void updateDishInBasket(User user, Dish dish, Number quantity) {
		if(quantity.intValue() <= 0)
		{
			this.basket.remove(dish);
		}
		
		this.basket.put(dish, quantity);
	}

	@Override
	public Order checkoutBasket(User user) {
		Order newOrder = new Order(user, this.basket);
		AddOrderMessage message = new AddOrderMessage(newOrder);
		Communication.sendMessage(communication.client.outputStream, message);
		this.basket = new HashMap<Dish, Number>();
		return newOrder;
	}

	@Override
	public void clearBasket(User user) {
		this.basket.clear();
	}

	@Override
	public List<Order> getOrders(User user) {
		GetOrdersMessage message = new GetOrdersMessage(user, null);
		Communication.sendMessage(communication.client.outputStream, message);
		message = (GetOrdersMessage) Communication.receiveMessage(communication.client.inputStream);
		return message.orders;
	}

	@Override
	public boolean isOrderComplete(Order order) {
		if(order == null)
			return false;
		return order.getCompleted();
	}

	@Override
	public String getOrderStatus(Order order) {
		return order.getStatus();
	}

	@Override
	public Number getOrderCost(Order order) {
		return order.getCost();
	}

	@Override
	public void cancelOrder(Order order) {
		CancelOrderMessage message = new CancelOrderMessage(order);
		Communication.sendMessage(communication.client.outputStream, message);
	}
	
}
