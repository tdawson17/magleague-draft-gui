import java.util.ArrayList;


public class MLBTeam extends Team {
	ArrayList<Player> players;
	public MLBTeam(String teamName)
	{
		super(teamName);
		players = new ArrayList<Player>();
	}
	
}
