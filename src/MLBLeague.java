import java.util.ArrayList;
import java.util.Collections;


public class MLBLeague {
	ArrayList<Player> players;
	ArrayList<MLBTeam> teams;
	public MLBLeague()
	{
		players = new ArrayList<Player>();
		teams = new ArrayList<MLBTeam>();
	}
	MLBTeam getTeam(String teamName)
	{
		MLBTeam ret_val = null;
		int i;
		for(i=0;i<teams.size();i++)
		{
			ret_val = teams.get(i);
			if(teamName.compareTo(ret_val.getTeamName())==0)
			{
				return ret_val;
			}
		}
		return new MLBTeam(teamName);
	}
	public MLBTeam addTeam(String teamName)
	{
		MLBTeam ret_val = new MLBTeam(teamName);
		teams.add(ret_val);
		return ret_val;
	}
	public Player addPlayer(Player p)
	{
		int index = Collections.binarySearch(players, p);
		if(index>=0)
		{
			Player ret_val = players.get(index);
			ret_val.addInformation(p);
			return ret_val;
		} else
		{
			players.add(-1*(index+1), p);
			return p;
		}
	}
	
	public Player findPlayer(Player p)
	{
		int index = Collections.binarySearch(players, p);
		if(index>=0)
		{
			return players.get(index);
		} else
		{
			return null;
		}
	}
}
