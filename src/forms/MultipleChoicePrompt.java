package forms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultipleChoicePrompt extends Prompt {
	private static final long serialVersionUID = 7117149641257730933L;
	private List<Choice> choices;
	private List<Choice> correctAnswer;
	
	public List<Choice> getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(List<Choice> correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public MultipleChoicePrompt(String prompt, List<Choice> c)
	{
		setPrompt(prompt);
		choices = c;
	}
	
	public List<Choice> getChoices() {
		return choices;
	}
	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}
	
	@Override
	public Response getResponse(Scanner scanner) {
		String input;
		
		System.out.println(getPrompt());
		for (Choice choice : choices)
		{
			System.out.println(choice.ChoiceID + " - " + choice.ChoiceString);
		}
		
		input = scanner.nextLine();
		try {
			if (Integer.parseInt(input) > choices.size())
			{
				System.out.println("Please enter valid input:");
				return getResponse(scanner);
			}
		} catch (NumberFormatException e) {
			System.out.println("Please enter valid input:");
			return getResponse(scanner);
		}
		
		return new MultipleChoiceResponse(choices.get(Integer.parseInt(input) - 1));
	}
	
	@Override
	public void display()
	{
		System.out.println(getPrompt());
		for (Choice choice : choices)
		{
			System.out.println(choice.ChoiceID + " - " + choice.ChoiceString);
		}
		if (correctAnswer != null)
		{
			for (Choice c : correctAnswer)
				System.out.println("Answer: " + c.ChoiceString);
		}
	}
	
	public void getSolution(Scanner scanner, List<Choice> choices)
	{
		String input;
		
		if (correctAnswer == null)
		{
			correctAnswer = new ArrayList<Choice>();
		}
		
		System.out.println("What is the correct answer?");
		for (Choice choice : choices)
		{
			System.out.println(choice.ChoiceID + " - " + choice.ChoiceString);
		}
		
		input = scanner.nextLine();
		try {
			if (Integer.parseInt(input) > choices.size())
			{
				System.out.println("Please enter valid input:");
				getSolution(scanner, choices);
			}
		} catch (NumberFormatException e) {
			System.out.println("Please enter valid input:");
			getSolution(scanner, choices);
		}
		
		correctAnswer.add(choices.get(Integer.parseInt(input) - 1));
		
		//answers.add(choices.get(Integer.parseInt(input) - 1));
		//setCorrectAnswer(answers);
	}

}
