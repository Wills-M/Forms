package forms;

import java.io.Serializable;

public class Choice implements Serializable {
	private static final long serialVersionUID = 7117149641257730933L;
	public String ChoiceID;
	public String ChoiceString;
	
	public Choice(String choiceID, String choiceString)
	{
		ChoiceID = choiceID;
		ChoiceString = choiceString;
	}
}
