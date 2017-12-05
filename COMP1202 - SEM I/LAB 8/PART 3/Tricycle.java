public class Tricycle extends Cycle
{
	@Override
	public int getNumberOfWheels() 
	{
		return 3;
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