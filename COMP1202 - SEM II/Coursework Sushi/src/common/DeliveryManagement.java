/*
 * @author Andronache Simone
 */
package common;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class DeliveryManagement {
	
	/** The orders which need to be delivered */
	private Queue<Order> orders;
	
	/** This class static instance */
	public static DeliveryManagement instance;
	
	/**
	 * Instantiates a new delivery management object.
	 */
	public DeliveryManagement()
	{
		DeliveryManagement.instance = this;
		orders = new ArrayDeque<Order>();
	}
	
	/**
	 * Adds to the order queue.
	 *
	 * @param order the order
	 */
	public synchronized void addOrderQueue(Order order)
	{
		this.orders.add(order);
	}
	
	/**
	 * Remove first element from the order queue.
	 *
	 * @return the order
	 */
	public synchronized Order popOrderQueue()
	{
		return this.orders.poll();
	}
	
	/**
	 * Remove a order from the queue.
	 *
	 * @param order the order
	 */
	public synchronized void cancelOrder(Order order)
	{
		this.orders.remove(order);
	}
}
