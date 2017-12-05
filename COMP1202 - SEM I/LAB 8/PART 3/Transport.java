public abstract class Transport
{
	/**
	 * @return the number of seats available
	 */
	public abstract int getNumberOfSeats();
	/**
	 * Start up the machine.
	 * @return the state of the machine
	 */
	public abstract boolean start();
}