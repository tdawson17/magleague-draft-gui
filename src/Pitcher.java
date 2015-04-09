
public class Pitcher extends Player {
	public Pitcher(PlayerName name, String mlbTeam, MLBLeague league)
	{
		super(name, mlbTeam, league);
	}

	public Pitcher(PlayerName name, String mlbTeam, MLBLeague league, PitchingProjection projection)
	{
		super(name, mlbTeam, league, projection);
	}
	
	@Override
	public void addInformation(Player p) {
		super.addInformation(p);
	}

	public String getPlayerHeader(String delimiter)
	{
		return super.getPlayerHeader(delimiter);
	}

	public String getFullString(String delimiter)
	{
		return super.getFullString(delimiter);
	}
}
