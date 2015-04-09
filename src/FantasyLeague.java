import java.util.ArrayList;
import java.util.Collections;


public class FantasyLeague {
	ArrayList<FantasyTeam> fantasyTeams;
	public FantasyLeague()
	{
		fantasyTeams = new ArrayList<FantasyTeam>();
	}
	public void addTeam(FantasyTeam team)
	{
		fantasyTeams.add(team);
	}
	public FantasyTeam getTeam(String teamName)
	{
		int index;
		FantasyTeam team = new FantasyTeam(teamName);
		index = Collections.binarySearch(fantasyTeams, team);
		if(index>=0)
		{
			return fantasyTeams.get(index); 
		} else
		{
			fantasyTeams.add(-1*(index+1), team);
			return team;
		}
		
	}

	public String getFullString(String delimiter) {
		String ret_val = "";
		for(FantasyTeam t: fantasyTeams)
		{
			ret_val += t.getFullString(delimiter) + System.lineSeparator();
		}
		return ret_val;
	}
}
