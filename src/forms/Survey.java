package forms;

import java.util.List;

public class Survey extends Form{
	private static final long serialVersionUID = 7117149641257730933L;
	
	public Survey(List<Prompt> prompts)
	{
		super.setPrompts(prompts);
	}
}