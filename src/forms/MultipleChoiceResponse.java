package forms;

public class MultipleChoiceResponse extends Response{
	private static final long serialVersionUID = 7117149641257730933L;
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
