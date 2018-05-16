package forms;

import java.io.Serializable;
import java.util.List;

public abstract class Form implements Serializable{
	private static final long serialVersionUID = 7117149641257730933L;
	
	private List<Prompt> prompts;

	public List<Prompt> getPrompts() {
		return prompts;
	}

	public void setPrompts(List<Prompt> prompts) {
		this.prompts = prompts;
	}
}
