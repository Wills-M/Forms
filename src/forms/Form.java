package forms;

import java.io.Serializable;
import java.util.List;

public abstract class Form implements Serializable{
	private static final long serialVersionUID = 7117149641257730933L;
	
	private List<Prompt> prompts;
	private List<List<Response>> responses;
	public String formName;

	public List<List<Response>> getResponses() {
		return responses;
	}

	public void setResponses(List<List<Response>> responses) {
		this.responses = responses;
	}

	public List<Prompt> getPrompts() {
		return prompts;
	}

	public void setPrompts(List<Prompt> prompts) {
		this.prompts = prompts;
	}
	
	public void addPrompts(List<Prompt> prompts) {
		for (Prompt p : prompts)
			this.prompts.add(p);
	}
}
