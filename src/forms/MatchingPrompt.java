package forms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javafx.util.Pair;

public class MatchingPrompt extends Prompt {
	private static final long serialVersionUID = 7117149641257730933L;
	private Pair<List<String>, List<String>> matches;
	public boolean isTest;
	
	public MatchingPrompt(Pair<List<String>, List<String>> m)
	{
		matches = m;
	}

	@Override
	public Response getResponse(Scanner scanner) {
		MatchingResponse response = new MatchingResponse();
		List<String> allowedInputs = new ArrayList<String>(); //List of valid inputs
		List<String> temp = new ArrayList<String>(matches.getValue()); //copies list
		List<String> leftShuffle = null;
		String input;
		
		//Output the prompt
		System.out.println(getPrompt());
		
		//shuffle our "Answers" into a leftShuffle but keep the ordering in matches
		Collections.shuffle(matches.getValue());
		leftShuffle = new ArrayList<String>(matches.getValue());
		Collections.copy(matches.getValue(), temp);
		
		System.out.println("Here are the possible answers: ");
		for (int i = 0; i < matches.getKey().size(); i++)
		{
			System.out.println(leftShuffle.get(i)); //Output possible answers
			allowedInputs.add(leftShuffle.get(i)); //Add them to list of valid inputs
		}
		
		for (int i = 0; i < matches.getKey().size(); i++)
		{
			System.out.println("Select the match for: " + matches.getKey().get(i));
			input = scanner.nextLine();
			while (!allowedInputs.contains(input))
			{
				System.out.println("The answer you have entered is either not a valid answer or you have already used it; please enter a valid answer.");
				input = scanner.nextLine();
			}
			response.matches.getKey().add(matches.getKey().get(i)); //set left side of pair to left side match
			response.matches.getValue().add(input); //set right side of pair to right side match
			
			allowedInputs.remove(input);
		}
		
		return response;
	}

	@Override
	public void display() {
		System.out.println(getPrompt());
		for (int i = 0; i < matches.getKey().size(); i++)
		{
			System.out.println(matches.getKey().get(i) + " <-> " + matches.getValue().get(i));
		}
		
	}

	public Pair<List<String>, List<String>> getMatches() {
		return matches;
	}

	public void setMatches(Pair<List<String>, List<String>> matches) {
		this.matches = matches;
	}

}
