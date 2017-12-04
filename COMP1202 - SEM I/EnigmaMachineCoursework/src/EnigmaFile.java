import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EnigmaFile {
	
	private EnigmaMachine enigmaMachine;
	
	public EnigmaFile()
	{
		enigmaMachine = new EnigmaMachine();
	}
	
	public EnigmaFile(EnigmaMachine enigmaMachine)
	{
		this.enigmaMachine = enigmaMachine;
	}
	
	/**
	 * Get method for the <i>enigmaMachine</i> variable.
	 * @return an EnigmaMachine object 
	 */
	public EnigmaMachine getMachine()
	{
		return enigmaMachine;
	}
	
	/**
	 * Set method for the <i>enigmaMachine</i> variable
	 * @param enigmaMachine an EnigmaMachine object 
	 */
	public void setMachine(EnigmaMachine enigmaMachine)
	{
		this.enigmaMachine = enigmaMachine;
	}
	
	/**
	 * Encrypt a file's content and write it to another file.
	 * @param fileName the name of the file to encrypt
	 * @param encryptedFileName the name of the file to save the encryption in 
	 */
	public void encryptFile(String fileName, String encryptedFileName)
	{
		String content = getFileContent(fileName);
		
		if(content == null || content.isEmpty())
		{
			System.err.println("EnigmaFile : The file is empty!");
			return;
		}
		
		String encryptedMessage = enigmaMachine.encodeString(content);
		
		writeContent(encryptedFileName, encryptedMessage);
	}
	
	/**
	 * Reads a file's content and return it as a single string.
	 * @param fileName the name of the file to read 
	 * @return String the file's content
	 */
	private String getFileContent(String fileName)
	{
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName)))
		{    
			StringBuilder sb = new StringBuilder();
		    String line = bufferedReader.readLine();
		    
		    while (line != null) {
		        sb.append(line.trim());
		        line = bufferedReader.readLine();
		    }
		    
		    return sb.toString();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Write a string into a new or existing file.
	 * @param fileName the name of the file to write 
	 * @param content the String to write in the file
	 */
	private void writeContent(String fileName, String content)
	{
		try(BufferedWriter writer = new BufferedWriter( new FileWriter(fileName)))
		{
		    writer.write(content);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
