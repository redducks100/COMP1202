public class Bicycle extends Cycle
{
	@Override
	public int getNumberOfWheels() {
		return 2;
	}

	@Override
	public int getNumberOfSeats() {
		return 1;
	}

	@Override
	public boolean start() {
		return true;
	}
	
}