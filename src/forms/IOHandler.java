package forms;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javafx.util.Pair;

public class IOHandler {
	private Scanner scanner;
	private Form loadedForm;
	
	public IOHandler()
	{
		scanner = new Scanner(System.in);
		loadedForm = null;
	}
	
	public void MainMenu()
	{
		List<String> allowedInputs = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8"); //Create immutable list to check for valid input
		String input;
		
		System.out.println("What would you like to do? Enter the number corresponding to the chosen option.");
		System.out.println("1 - Create a form");
		System.out.println("2 - Load a form");
		System.out.println("3 - Display loaded form");
		System.out.println("4 - Take loaded form");
		System.out.println("5 - Tabulate loaded form");
		System.out.println("6 - Grade loaded test");
		System.out.println("7 - Modify laoded form");
		System.out.println("8 - Exit");
		
		input = getCorrectInput(allowedInputs);

		switch (input)
		{
			case "1": CreateMenu();
				break;
			case "2": LoadMenu();
				break;
			case "3": DisplayForm();
				break;
			case "4": TakeForm();
				break;
			case "5": TabulateForm();
				break;
			case "6": GradeTest();
				break;
			case "7": ModifyForm();
				break;
			case "8":
				break;
			default: MainMenu();
				break;
		}
	}
	
	private void CreateMenu()
	{
		List<String> allowedInputs = Arrays.asList("1", "2", "3"); //Create immutable list to check for valid input
		String input;
		
		System.out.println("What type of form would you like to create?");
		System.out.println("1 - Test");
		System.out.println("2 - Survey");
		System.out.println("3 - Go back to main menu");
		
		input = getCorrectInput(allowedInputs);
		
		switch (input)
		{
			case "1": CreateTest();
				break;
			case "2": CreateSurvey();
				break;
			case "3": MainMenu();
				break;
		}
	}

	private void CreateTest()
	{
		String testName;
		Test test;
		List<Prompt> prompts = null;
		
		prompts = CreatePrompts(true); //True signifies that answers are needed for the questions
		test = new Test(prompts);
		
		System.out.println("Please enter a name for your test:");
		testName = scanner.nextLine();
		
		test.formName = testName;
		test.setResponses(new ArrayList<List<Response>>());
		
		FileHandler.SaveObject(test, testName + ".ser");
		
		MainMenu();
	}
	
	private void CreateSurvey()
	{
		String surveyName;
		Survey survey;
		List<Prompt> prompts = null;
		
		prompts = CreatePrompts(false); //False signifies that correct answers are not needed for the prompts
		survey = new Survey(prompts);
		
		System.out.println("Please enter a name for your survey:");
		surveyName = scanner.nextLine();
		
		survey.formName = surveyName;
		survey.setResponses(new ArrayList<List<Response>>());
		
		FileHandler.SaveObject(survey, surveyName + ".ser");
		
		MainMenu();
	}
	
	private List<Prompt> CreatePrompts(Boolean isTest)
	{
		List<Prompt> prompts = new ArrayList<>();
		List<String> allowedInputs = Arrays.asList("1", "2", "3", "4", "5", "6", "10"); //Create immutable list to check for valid input
		String input;
		
		System.out.println("What type of prompt would you like to make?");
		System.out.println("1 - Essay");
		System.out.println("2 - Short Answer");
		System.out.println("3 - Multiple Choice");
		System.out.println("4 - True/False");
		System.out.println("5 - Matching");
		System.out.println("6 - Ranking");
		System.out.println("10 - No more prompts");
		
		input = getCorrectInput(allowedInputs);
		
		while (!input.equals("10"))
		{
			switch (input)
			{
				case "1": prompts.add(CreateEssay(isTest));
					break;
				case "2": prompts.add(CreateShortAnswerPrompt(isTest));
					break;
				case "3": prompts.add(CreateMultipleChoicePrompt(isTest));
					break;
				case "4": prompts.add(CreateTrueFalsePrompt(isTest));
					break;
				case "5": prompts.add(CreateMatchingPrompt(isTest));
					break;
				case "6": prompts.add(CreateRankingPrompt(isTest));
					break;
			}
			
			System.out.println("What type of prompt would you like to make?");
			System.out.println("1 - Essay");
			System.out.println("2 - Short Answer");
			System.out.println("3 - Multiple Choice");
			System.out.println("4 - True/False");
			System.out.println("5 - Matching");
			System.out.println("6 - Ranking");
			System.out.println("10. No more prompts");
			input = getCorrectInput(allowedInputs);
		}
		
		return prompts;
	}
	
	private EssayPrompt CreateEssay(Boolean isTest)
	{
		EssayPrompt prompt;
		String promptString;
		
		System.out.println("Please enter a prompt:");
		promptString = scanner.nextLine();
		
		prompt = new EssayPrompt(promptString);
		
		return prompt;
	}
	
	private ShortAnswerPrompt CreateShortAnswerPrompt(Boolean isTest)
	{
		ShortAnswerPrompt prompt;
		String promptString;
		
		System.out.println("Please enter a prompt:");
		promptString = scanner.nextLine();
		
		prompt = new ShortAnswerPrompt(promptString);
		
		if (isTest) 
		{
			System.out.println("Please enter the correct answer:");
			prompt.addCorrectAnswer(scanner.nextLine());
			
			System.out.println("Would you like to add another answer? (y/n)");
			String input = scanner.nextLine();
			while (input.equals("y") || input.equals("Y"))
			{
				System.out.println("Enter an answer:");
				prompt.addCorrectAnswer(scanner.nextLine());
				System.out.println("Would you like to add another answer? (y/n)");
				input = scanner.nextLine();
			}
		}
		
		return prompt;
	}
	
	private MultipleChoicePrompt CreateMultipleChoicePrompt(Boolean isTest)
	{
		MultipleChoicePrompt prompt;
		String promptString;
		String choiceString;
		List<Choice> choices = new ArrayList<Choice>();
		
		System.out.println("Please enter a prompt:");
		promptString= scanner.nextLine();
		
		System.out.println("Please enter a choice:");
		int choiceID = 0;
		choiceString = scanner.nextLine();
		
		while (!choiceString.equals("/exit"))
		{
			choiceID++;
			choices.add(new Choice(Integer.toString(choiceID), choiceString));
			System.out.println("Please enter a choice (type /exit to finish):");
			choiceString = scanner.nextLine();
		}
		
		prompt = new MultipleChoicePrompt(promptString, choices);
		
		if (isTest)
		{
			prompt.getSolution(scanner, choices);
			
			String input;
			System.out.println("Would you like to add another answer? (y/n)");
			input = scanner.nextLine();
			while (input.equals("y") || input.equals("Y"))
			{
				prompt.getSolution(scanner, choices);
				System.out.println("Would you like to add another answer? (y/n)");
				input = scanner.nextLine();
			}
		}
		
		return prompt;
	}
	
	private TrueFalsePrompt CreateTrueFalsePrompt(Boolean isTest)
	{
		TrueFalsePrompt prompt;
		String promptString;
		List<Choice> choices = Arrays.asList(new Choice("1", "True"), new Choice("2", "False"));
		
		System.out.println("Please enter a prompt");
		promptString = scanner.nextLine();
		
		prompt = new TrueFalsePrompt(promptString, choices);
		
		if (isTest)
		{
			prompt.getSolution(scanner, choices);
		}
		
		return prompt;
		
	}
	
	private MatchingPrompt CreateMatchingPrompt(Boolean isTest)
	{
		MatchingPrompt prompt;
		String promptString;
		String input;
		List<String> leftSide = new ArrayList<String>();
		List<String> rightSide = new ArrayList<String>();
		
		System.out.println("Please enter a prompt");
		promptString = scanner.nextLine();
		
		System.out.println("Please enter all of one \"side\" of your pairs. Enter /exit when you're finished.");
		input = scanner.nextLine();
		while (!input.equals("/exit"))
		{
			leftSide.add(input);
			input = scanner.nextLine();
		}
		
		System.out.println("Please enter all of the other \"side\" of your pairs.");
		if (isTest)
			System.out.println("To set correct matches, please enter them in the same order as the other \"side\"");
		
		while (rightSide.size() < leftSide.size())
		{
			input = scanner.nextLine();
			rightSide.add(input);
		}
		
		prompt = new MatchingPrompt(new Pair<List<String>, List<String>>(leftSide, rightSide));
		prompt.setPrompt(promptString);
		
		if (isTest)
			prompt.isTest = true;
		
		return prompt;
	}
	
	private RankingPrompt CreateRankingPrompt(Boolean isTest)
	{
		RankingPrompt prompt;
		String promptString;
		String input;
		List<String> numbers = new ArrayList<String>();
		List<String> choices = new ArrayList<String>();
		
		System.out.println("Please enter a prompt");
		promptString = scanner.nextLine();
		
		System.out.println("Please enter all of the choices. Enter /exit when you're finished.");
		if (isTest)
			System.out.println("Please enter them in order starting with Rank 1");
		
		int i = 1;
		input = scanner.nextLine();
		while (!input.equals("/exit"))
		{
			choices.add(input);
			numbers.add("Rank " + Integer.toString(i));
			i++;
			input = scanner.nextLine();
		}
		
		prompt = new RankingPrompt(new Pair<List<String>, List<String>>(numbers, choices));
		prompt.setPrompt(promptString);
		
		if (isTest)
			prompt.isTest = true;
		
		return prompt;
	}
	
	private void LoadMenu()
	{
		File folder = new File(".");
		String input;
		
		File[] listOfFiles = folder.listFiles(new FilenameFilter() { 
		    public boolean accept(File dir, String filename) {
		        return filename.toLowerCase().endsWith(".ser");
		    }
		});

		System.out.println("Please select a form to load:");
		int count = 1;
	    for (File file : listOfFiles)
	    {
	    	System.out.println(count + " - " + file.getName().substring(0, file.getName().lastIndexOf("."))); //Print number and filename without ".ser"
	    	count++;
	    }
	    System.out.println(count + " - Exit");
	    input = getCorrectFile(listOfFiles);
	    
	    if (Integer.parseInt(input) == listOfFiles.length + 1)
	    {
	    	MainMenu();
	    }
	    else
	    {
	    	File file = listOfFiles[Integer.parseInt(input) - 1];
	    	String filename = file.getName();
	    	loadedForm = (Form)FileHandler.LoadObject(filename);
	    	MainMenu();
	    }
	}
	
	private void DisplayForm()
	{
		if (loadedForm == null)
		{
			MainMenu();
		}
		else
		{
			int count = 1;
			for (Prompt prompt : loadedForm.getPrompts())
			{
				System.out.println(Integer.toString(count) + ")");
				prompt.display();
				System.out.println();
				count++;
			}
			MainMenu();
		}
	}
	
	private void TakeForm()
	{
		if (loadedForm == null)
			MainMenu();
		else
		{
			List<Response> responses = new ArrayList<Response>();
			
			for (Prompt p : loadedForm.getPrompts())
			{
				responses.add(p.getResponse(scanner));
			}
			
			loadedForm.getResponses().add(responses);
			
			FileHandler.SaveObject(loadedForm, loadedForm.formName + ".ser");
			loadedForm = (Form)FileHandler.LoadObject(loadedForm.formName + ".ser");
			
			MainMenu();
		}
	}
	
	private void TabulateForm()
	{
		if (loadedForm != null) {
			for (int i = 0; i < loadedForm.getPrompts().size(); i++) {
				System.out.println("Question " + (i + 1));
				Class<? extends Prompt> cls = loadedForm.getPrompts().get(i).getClass();

				switch (cls.getName()) {
				case "forms.MatchingPrompt":
				case "forms.RankingPrompt":
					List<Pair<MatchingResponse, Integer>> tabulatedResponses = new ArrayList<Pair<MatchingResponse, Integer>>();
					List<MatchingResponse> encounteredResponses = new ArrayList<MatchingResponse>();
					for (List<Response> list : loadedForm.getResponses()) {
						MatchingResponse currentResponse = (MatchingResponse) list.get(i);
						if (encounteredResponses.contains(currentResponse)) {
							int j = encounteredResponses.indexOf(currentResponse);
							Pair<MatchingResponse, Integer> pairToChange = tabulatedResponses.get(j);
							tabulatedResponses.set(j, new Pair<MatchingResponse, Integer>(pairToChange.getKey(),
									pairToChange.getValue() + 1));
						} else {
							encounteredResponses.add(currentResponse);
							tabulatedResponses.add(new Pair<MatchingResponse, Integer>(currentResponse, 1));
						}
					}

					for (Pair<MatchingResponse, Integer> pair : tabulatedResponses) {
						System.out.println(pair.getValue() + " times:");
						for (int j = 0; j < pair.getKey().matches.getKey().size(); j++) {
							System.out.println(pair.getKey().matches.getKey().get(j) + " <-> "
									+ pair.getKey().matches.getValue().get(j));
						}
					}
					break;
				case "forms.EssayPrompt":
					for (List<Response> list : loadedForm.getResponses()) {
						System.out.println(((EssayResponse) list.get(i)).getResponse());
					}
					break;
				case "forms.ShortAnswerPrompt":
					List<Pair<String, Integer>> tabulatedStrings = new ArrayList<Pair<String, Integer>>();
					List<String> encounteredStrings = new ArrayList<String>();
					for (List<Response> list : loadedForm.getResponses()) {
						String currentString = ((EssayResponse) list.get(i)).getResponse();
						if (encounteredStrings.contains(currentString)) {
							int j = encounteredStrings.indexOf(currentString);
							Pair<String, Integer> pairToChange = tabulatedStrings.get(j);
							tabulatedStrings.set(j,
									new Pair<String, Integer>(pairToChange.getKey(), pairToChange.getValue() + 1));
						} else {
							encounteredStrings.add(currentString);
							tabulatedStrings.add(new Pair<String, Integer>(currentString, 1));
						}
					}

					for (Pair<String, Integer> p : tabulatedStrings) {
						System.out.println(p.getKey() + " - " + p.getValue() + " times");
					}
					break;
				case "forms.TrueFalsePrompt":
				case "forms.MultipleChoicePrompt":
					List<Pair<Choice, Integer>> tabulatedChoices = new ArrayList<Pair<Choice, Integer>>();
					List<Choice> encounteredChoices = new ArrayList<Choice>();

					for (List<Response> list : loadedForm.getResponses()) {
						Choice currentChoice = ((MultipleChoiceResponse) list.get(i)).getResponse();
						if (encounteredChoices.contains(currentChoice)) {
							int j = encounteredChoices.indexOf(currentChoice);
							Pair<Choice, Integer> pairToChange = tabulatedChoices.get(j);
							tabulatedChoices.set(j,
									new Pair<Choice, Integer>(pairToChange.getKey(), pairToChange.getValue() + 1));
						} else {
							encounteredChoices.add(currentChoice);
							tabulatedChoices.add(new Pair<Choice, Integer>(currentChoice, 1));
						}
					}

					for (Pair<Choice, Integer> p : tabulatedChoices) {
						System.out.println(p.getKey().ChoiceString + " - " + p.getValue() + " times");
					}
					break;
				}
				System.out.println();
			} 
		}
		MainMenu();
	}
	
	private void GradeTest()
	{
		if (loadedForm != null && loadedForm instanceof Test)
		{
			int count = 1;
			for (List<Response> testTake : loadedForm.getResponses())
			{
				int currentGrade = 0;
				int pointTotal = 0;
				
				System.out.println("Test take number " + count);
				for (int i = 0; i < testTake.size(); i++)
				{
					Response currentResponse = testTake.get(i);
					Prompt currentPrompt = loadedForm.getPrompts().get(i);
					if (!(currentPrompt.getClass().equals(EssayPrompt.class)))
					{
						pointTotal += 10;
						String responseType = currentResponse.getClass().getName();
						
						switch (responseType)
						{
							case "forms.MultipleChoiceResponse":
								Choice answerGiven = ((MultipleChoiceResponse) currentResponse).getResponse();
								MultipleChoicePrompt mcQuestion = (MultipleChoicePrompt) currentPrompt;
								if (mcQuestion.getCorrectAnswer().contains(answerGiven))
									currentGrade += 10;
								break;
							case "forms.EssayResponse": //this is actually a short answer"
								String response = ((EssayResponse) currentResponse).getResponse();
								ShortAnswerPrompt saQuestion = (ShortAnswerPrompt) currentPrompt;
								if (saQuestion.getCorrectAnswer().contains(response))
									currentGrade += 10;
								break;
							case "forms.RankingResponse":
							case "forms.MatchingResponse":
								Pair<List<String>, List<String>> matchResponse = ((MatchingResponse) currentResponse).matches;
								MatchingPrompt matchQuestion = (MatchingPrompt) currentPrompt;
								if (matchQuestion.getMatches().equals(matchResponse))
									currentGrade += 10;
						}
					}
				}
				System.out.println(currentGrade + "/" + pointTotal);
				count++;
			}
		}
		MainMenu();
	}
	
	private void ModifyForm()
	{
		List<String> allowedInputs = Arrays.asList("1", "2", "3"); //Create immutable list to check for valid input
		String input;
		
		System.out.println("What would you like to change?");
		System.out.println("1 - Add questions");
		System.out.println("2 - Delete a question");
		System.out.println("3 - Back to main menu");
		
		input = getCorrectInput(allowedInputs);
		
		while (!(input.equals("3")))
		{
			switch (input)
			{
				case "1": List<Prompt> questionsToAdd = CreatePrompts(loadedForm instanceof Test);
					loadedForm.addPrompts(questionsToAdd);
					break;
				case "2":
					List<String> allowedInputsDelete = new ArrayList<String>();
					for (int i = 1; i <= loadedForm.getPrompts().size(); i++)
						allowedInputsDelete.add(Integer.toString(i));
					
					System.out.println("What prompt do you want to delete?");
					
					int count = 1;
					for (Prompt p : loadedForm.getPrompts())
					{
						System.out.println(count + ")");
						p.display();
						count++;
					}
					
					input = getCorrectInput(allowedInputsDelete);
					loadedForm.getPrompts().remove(loadedForm.getPrompts().get(Integer.parseInt(input) - 1));
					break;
			}
			
			System.out.println("What would you like to change?");
			System.out.println("1 - Add questions");
			System.out.println("2 - Delete questions");
			System.out.println("3 - Back to main menu");
			
			input = getCorrectInput(allowedInputs);
		}
		
		FileHandler.SaveObject(loadedForm, loadedForm.formName + ".ser");
		MainMenu();
	}
	
	private String getCorrectInput(List<String> allowedInputs)
	{
		String input = scanner.nextLine();
		while (!allowedInputs.contains(input)) //Invalid input if condition is true
		{
			System.out.println("Please enter a valid option.");
			input = scanner.nextLine();
		}
		return input;
	}
	
	private String getCorrectFile(File[] listOfFiles)
	{
		String input;
		input = scanner.nextLine();
		
	    try 
	    {
	    	while (Integer.parseInt(input) > listOfFiles.length + 1)
	    	{
	    		System.out.println("Please enter a valid option.");
	    		input = scanner.nextLine();
	    	}
	    }
	    catch (NumberFormatException ex)
	    {
	    	System.out.println("Please enter a valid option.");
	    	return getCorrectFile(listOfFiles);
	    }
	    
	    return input;
	}
}
