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
		List<String> temp = new ArrayList<String>(matches.getValue()); //copies list
		List<String> leftShuffle = null;
		
		System.out.println(getPrompt());
		
		//shuffle our "Answers" into a leftShuffle but keep the ordering in matches
		Collections.shuffle(matches.getValue());
		leftShuffle = new ArrayList<String>(matches.getValue());
		Collections.copy(matches.getValue(), temp);
		
		for (int i = 0; i < matches.getKey().size(); i++)
		{
			System.out.println(Integer.toString(i+1) + " - " + matches.getKey().get(i) + " <-> " + leftShuffle.get(i));
		}
		
		return null;
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
