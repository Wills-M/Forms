package forms;

public class ShortAnswerPrompt extends EssayPrompt {
	private static final long serialVersionUID = 7117149641257730933L;
	private String correctAnswer;
	
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public ShortAnswerPrompt(String prompt) {
		super(prompt);
	}
	
	public void display()
	{
		System.out.println(getPrompt());
		if (correctAnswer != null)
			System.out.println("Answer: " + correctAnswer);
	}
}
