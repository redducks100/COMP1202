public class FlashCard
{
	private String question;
	private String answer;
	
	public FlashCard(String question, String answer)
	{
		this.question = question;
		this.answer = answer;
	}
	
	/**
	 * @return the question value
	 */
	public String getQuestion()
	{
		return question;
	}
	
	/**
	 * @return the answer value
	 */
	public String getAnswer()
	{
		return answer;
	}
}