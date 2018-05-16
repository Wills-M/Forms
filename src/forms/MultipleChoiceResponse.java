package forms;

public class MultipleChoiceResponse extends Response{
	private Choice response;
	
	public MultipleChoiceResponse(Choice choice)
	{
		response = choice;
	}

	public Choice getResponse() {
		return response;
	}

	public void setResponse(Choice response) {
		this.response = response;
	}
}
