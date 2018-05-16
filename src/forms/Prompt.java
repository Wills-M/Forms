package forms;

import java.io.Serializable;
import java.util.Scanner;

public abstract class Prompt implements Serializable {
	private static final long serialVersionUID = 7117149641257730933L;
	private String prompt;

	public String getPrompt()
	{
		return prompt;
	}

	public void setPrompt(String prompt)
	{
		this.prompt = prompt;
	}
	
	public abstract Response getResponse(Scanner scanner);
	
	public abstract void display();
}
