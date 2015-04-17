import java.util.ArrayList;

public class FantasyTeam extends Team {
	FantasyPitchingStaff pitchers;
	FantasyLineup hitters;
	ArrayList<Player> minorLeaguers;
	public static int NUM_COLUMNS_ROSTER = 3;
	public static int MAX_MINOR_LEAGUERS = 3;
	public static int TEAM_SALARY = 260;
	private static Hitter DUMMY_C = new Hitter(new PlayerName(), "", null, Hitter.GAMES_REQUIRED, 0, 0, 0, 0, 0, 0);
	private static Hitter DUMMY_1B = new Hitter(new PlayerName(), "", null, 0, Hitter.GAMES_REQUIRED, 0, 0, 0, 0, 0);
	private static Hitter DUMMY_2B = new Hitter(new PlayerName(), "", null, 0, 0, Hitter.GAMES_REQUIRED, 0, 0, 0, 0);
	private static Hitter DUMMY_SS = new Hitter(new PlayerName(), "", null, 0, 0, 0, Hitter.GAMES_REQUIRED, 0, 0, 0);
	private static Hitter DUMMY_3B = new Hitter(new PlayerName(), "", null, 0, 0, 0, 0, Hitter.GAMES_REQUIRED, 0, 0);
	private static Hitter DUMMY_OF = new Hitter(new PlayerName(), "", null, 0, 0, 0, 0, 0, Hitter.GAMES_REQUIRED, 0);
	private static Hitter DUMMY_DH = new Hitter(new PlayerName(), "", null, 0, 0, 0, 0, 0, 0, Hitter.GAMES_REQUIRED);
	ArrayList<Player> unclassifiedPlayers;
	public FantasyTeam(String teamName)
	{
		super(teamName);
		pitchers = new FantasyPitchingStaff();
		hitters = new FantasyLineup();
		minorLeaguers = new ArrayList<Player>();
	}
	public int getTotalSalary()
	{
		return pitchers.getTotalSalary() + hitters.getTotalSalary();
	}
	public int getTotalAvailableSalary()
	{
		return TEAM_SALARY - getTotalSalary();
	}
	public int getMaxBid()
	{
		int totalFreeSpots;
		int totalSalary;
		totalFreeSpots = pitchers.getNumberFreeSpots() + hitters.getNumberFreeSpots();
		if(totalFreeSpots == 0)
		{
			return 0;
		}
		totalSalary = pitchers.getTotalSalary() + hitters.getTotalSalary();
		return TEAM_SALARY - totalSalary - totalFreeSpots + 1;
	}
	public void addPlayer(Player player) throws NoSpotAvailableException 
	{
		if(player.salary == 0)
		{
			minorLeaguers.add(player);
		}
		else
		{
			if(player instanceof Hitter)
			{
				hitters.addHitter((Hitter) player);
			} else if(player instanceof Pitcher)
			{
				pitchers.addPitcher((Pitcher)player);
			}
		}
	}
	
	public String getFullString(String delimiter)
	{
		String ret_val = hitters.getFullString(delimiter);
		ret_val += pitchers.getFullString(delimiter);
		for(Player p:minorLeaguers)
		{
			ret_val += "ML" + delimiter + p.getFullString(delimiter) + System.lineSeparator();
		}
		return ret_val;
	}
	
	public static ArrayList<String> getRosterHeaderStrings()
	{
		ArrayList<String> ret_val = new ArrayList<String>();
		ret_val.add("POS");
		ret_val.add("Name");
		ret_val.add("Sal");
		return ret_val;
	}
	public ArrayList<String> getRosterStrings()
	{
		ArrayList<String> ret_val = FantasyTeam.getRosterHeaderStrings();
		ret_val.addAll(hitters.getLineupStrings());
		ret_val.addAll(pitchers.getPitchingStaffStrings());
		for(int i=0;i<MAX_MINOR_LEAGUERS;i++)
		{
			ret_val.add("ML");
			if(i<minorLeaguers.size())
			{
				ret_val.add(minorLeaguers.get(i).name.toString());
				ret_val.add("");
			} else
			{
				ret_val.add("");
				ret_val.add("");
			}
		}
		return ret_val;
	}
}
