public class JetPlane extends Transport implements Refuelable
{
	private int gasTank=0;
	
	@Override
	public void refuel(int liters) {
		gasTank += liters;
	}

	@Override
	public int getNumberOfSeats() {
		return 150;
	}

	@Override
	public boolean start() {
		if(gasTank <= 0)
			return false;
		return true;
	}
	
}