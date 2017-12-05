public class Train extends Transport
{
	@Override
	public int getNumberOfSeats() {
		return 200;
	}

	@Override
	public boolean start() {
		return true;
	}
	
}