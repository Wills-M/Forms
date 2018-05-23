package forms;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public class MatchingResponse extends Response{
	private static final long serialVersionUID = 7117149641257730933L;
	public Pair<List<String>, List<String>> matches;
	public MatchingResponse()
	{
		matches = new Pair<List<String>, List<String>>(new ArrayList<String>(), new ArrayList<String>());
	}
	@Override
	public boolean equals(Object obj) {
		return this.matches.equals(((MatchingResponse)obj).matches);
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}
