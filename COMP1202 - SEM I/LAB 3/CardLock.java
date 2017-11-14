
public class CardLock
{
    private SmartCard lastCard;
    private boolean staffAccess;
    
    public CardLock()
    {
        staffAccess = true;
    }
    
    public boolean isUnlocked()
    {
        if(lastCard.isStaff() == true)
        {
            return true;
        }
        else
        {  //the card is a student card 
            return !staffAccess; //return true if staffAccess if false, and return false if staffAccess is true
        }
    }
    
    public void toggleStudentAccess()
    {
        staffAccess = !staffAccess;
    }
    
    public void swipeCard(SmartCard card)
    {
        lastCard = card;
    }
    
    public SmartCard getLastCardSeen()
    {
        return lastCard;
    }
}
