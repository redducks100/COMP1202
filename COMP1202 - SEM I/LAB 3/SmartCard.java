public class SmartCard
{
    private String owner;
    private boolean staff;

    public SmartCard(String owner)
    {
        this.owner = owner;
        this.staff = false;
    }
    
    public boolean isStaff()
    {
        return staff;
    }
    
    public void setStaff(boolean staff)
    {
        this.staff = staff;
    }
    
    public String getOwner()
    {
        return owner;
    }
}
