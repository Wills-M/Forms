package forms;

import java.util.ArrayList;
import java.util.List;

public class ShortAnswerPrompt extends EssayPrompt {
	private static final long serialVersionUID = 7117149641257730933L;
	private List<String> correctAnswer = new ArrayList<String>();
	
	public List<String> getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(List<String> correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	public void addCorrectAnswer(String correctAnswer) {
		this.correctAnswer.add(correctAnswer);
	}

	public ShortAnswerPrompt(String prompt) {
		super(prompt);
	}
	
	public void display()
	{
		System.out.println(getPrompt());
		if (correctAnswer != null)
		{
			for(String s : correctAnswer)
				System.out.println("Answer: " + s);
		}
	}
}
