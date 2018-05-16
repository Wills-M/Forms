package forms;

public class EssayResponse extends Response{
	private String response;
	
	public EssayResponse(String r)
	{
		response = r;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
