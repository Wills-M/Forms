package forms;

import java.io.Serializable;
import java.util.Scanner;

public class EssayPrompt extends Prompt implements Serializable {
	private static final long serialVersionUID = 7117149641257730933L;
	
	public EssayPrompt(String prompt)
	{
		setPrompt(prompt);
	}
	
	public Response getResponse(Scanner scanner)
	{
		System.out.println(this.getPrompt());
		return new EssayResponse(scanner.nextLine());
	}
	
	public void display()
	{
		System.out.println(getPrompt());
	}
}
