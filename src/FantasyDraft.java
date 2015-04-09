import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;


public class FantasyDraft {

	public static MLBLeague league;
	public static FantasyLeague fantasyLeague;
	public static String delimiter = "\t";
	public static String split_delimiter = "\\t";
	public static void main(String[] args) throws Exception 
	{
		league = new MLBLeague();
		fantasyLeague = new FantasyLeague();
		importHitterProjections(new FileReader(args[0]));
		importPitcherProjections(new FileReader(args[1]));
		importPlayerPositions(new FileReader(args[2]));
		importFantasyTeams(new FileReader(args[3]));
		printAvailablePositions();
		System.out.println(fantasyLeague.getFullString(delimiter));
		DialogDemo.hopeful(league, fantasyLeague);
	}

	public static void printFantasyTeams()
	{
		
	}
	public static void printPlayerList(ArrayList<Player> players)
	{
		boolean bPrintedHeader = false;
		for(Player p:players)
		{
			if(!bPrintedHeader)
			{
				System.out.println(p.getPlayerHeader(delimiter));
				bPrintedHeader = true;
			}
			System.out.println(p.getFullString(delimiter));
		}
	}

	public static void printAvailablePositions()
	{
		ArrayList<Player> availablePlayers = getAvailablePlayers();
		printPlayerList(getAvailableCatchers(availablePlayers));
		printPlayerList(getAvailable1B(availablePlayers));
		printPlayerList(getAvailable2B(availablePlayers));
		printPlayerList(getAvailableSS(availablePlayers));
		printPlayerList(getAvailable3B(availablePlayers));
		printPlayerList(getAvailableOF(availablePlayers));
		printPlayerList(getAvailableDH(availablePlayers));
		printPlayerList(getAvailableHittersWithoutPosition(availablePlayers));
		printPlayerList(getAvailableP(availablePlayers));
	}
	
	private static ArrayList<Player> getAvailableHittersWithoutPosition(
			ArrayList<Player> availablePlayers)
	{
		ArrayList<Player> players = new ArrayList<Player>();
		for(Player p:availablePlayers)
		{
			if(p instanceof Hitter)
			{
				if(!((Hitter)p).bAddedPlayerGames)
				{
					players.add(p);
				}
			}
		}
		return players;
	}

	public static ArrayList<Player> getAvailableP(ArrayList<Player> availablePlayers)
	{
		ArrayList<Player> pitchers = new ArrayList<Player>();
		for(Player p:availablePlayers)
		{
			if(p instanceof Pitcher)
			{
					pitchers.add(p);
			}
		}
		return pitchers;
		
	}
	public static ArrayList<Player> getAvailableCatchers(ArrayList<Player> availablePlayers)
	{
		ArrayList<Player> catchers = new ArrayList<Player>();
		for(Player p:availablePlayers)
		{
			if(p instanceof Hitter)
			{
				if(((Hitter)p).canPlayCatcher())
				{
					catchers.add(p);
				}
			}
		}
		return catchers;
	}
	
	public static ArrayList<Player> getAvailable1B(ArrayList<Player> availablePlayers)
	{
		ArrayList<Player> firstBasemen = new ArrayList<Player>();
		for(Player p:availablePlayers)
		{
			if(p instanceof Hitter)
			{
				if(((Hitter)p).canPlayFirstBase())
				{
					firstBasemen.add(p);
				}
			}
		}
		return firstBasemen;
	}

	public static ArrayList<Player> getAvailable2B(ArrayList<Player> availablePlayers)
	{
		ArrayList<Player> secondBasemen = new ArrayList<Player>();
		for(Player p:availablePlayers)
		{
			if(p instanceof Hitter)
			{
				if(((Hitter)p).canPlaySecondBase())
				{
					secondBasemen.add(p);
				}
			}
		}
		return secondBasemen;
	}

	public static ArrayList<Player> getAvailable3B(ArrayList<Player> availablePlayers)
	{
		ArrayList<Player> thirdBasemen = new ArrayList<Player>();
		for(Player p:availablePlayers)
		{
			if(p instanceof Hitter)
			{
				if(((Hitter)p).canPlayThirdBase())
				{
					thirdBasemen.add(p);
				}
			}
		}
		return thirdBasemen;
	}

	public static ArrayList<Player> getAvailableSS(ArrayList<Player> availablePlayers)
	{
		ArrayList<Player> shortstops = new ArrayList<Player>();
		for(Player p:availablePlayers)
		{
			if(p instanceof Hitter)
			{
				if(((Hitter)p).canPlayShortstop())
				{
					shortstops.add(p);
				}
			}
		}
		return shortstops;
	}

	public static ArrayList<Player> getAvailableOF(ArrayList<Player> availablePlayers)
	{
		ArrayList<Player> outfielders = new ArrayList<Player>();
		for(Player p:availablePlayers)
		{
			if(p instanceof Hitter)
			{
				if(((Hitter)p).canPlayOutfield())
				{
					outfielders.add(p);
				}
			}
		}
		return outfielders;
	}

	public static ArrayList<Player> getAvailableDH(ArrayList<Player> availablePlayers)
	{
		ArrayList<Player> dh = new ArrayList<Player>();
		for(Player p:availablePlayers)
		{
			if(p instanceof Hitter)
			{
				if(((Hitter)p).canPlayDesignatedHitter())
				{
					dh.add(p);
				}
			}
		}
		return dh;
	}

	public static void importHitterProjections(Reader characterReader) throws IOException
	{
		/*
		 * The format of the file is assumed to be:
		 * 
		 * Tab separated values
		 * 
		 * One line of headers
		 * Headers looked for are
		 * Name
		 * Team
		 * AB
		 * H
		 * HR
		 * RBI
		 * SB
		 * Salary (the salary deserved by this projection)
		 * Projection Type (not assumed to be there)
		 * The Reader will be parsed as the headers determine 
		 * 
		 */
		BufferedReader bReader = new BufferedReader(characterReader);
		String headerString = bReader.readLine();
		String playerLine;
		String[] headers = headerString.split(split_delimiter, -1);
		SheetFormat projectionSheetFormat;
		ArrayList<Column> projectionColumns = new ArrayList<Column>();
		projectionColumns.add(new Column("Name", true));
		projectionColumns.add(new Column("Team", true));
		projectionColumns.add(new Column("AB", true));
		projectionColumns.add(new Column("H", true));
		projectionColumns.add(new Column("HR", true));
		projectionColumns.add(new Column("RBI", true));
		projectionColumns.add(new Column("SB", true));
		projectionColumns.add(new Column("Salary", true));
		projectionColumns.add(new Column("Projection Type", false));
		projectionSheetFormat = new SheetFormat(projectionColumns);
		projectionSheetFormat.getColumnNumbers(headers);
		while(null!=(playerLine=bReader.readLine()))
		{
			String projectionType = "";
			String[] columns = playerLine.split(split_delimiter);
			if(projectionColumns.get(8).exists)
			{
				projectionType = columns[projectionColumns.get(8).columnNumber];
			}
			HittingProjection projection = new HittingProjection(projectionType, 
					(int)Double.parseDouble(columns[projectionColumns.get(7).columnNumber]),
					Integer.decode(columns[projectionColumns.get(2).columnNumber]),
					Integer.decode(columns[projectionColumns.get(3).columnNumber]),
					Integer.decode(columns[projectionColumns.get(4).columnNumber]),
					Integer.decode(columns[projectionColumns.get(5).columnNumber]),
					Integer.decode(columns[projectionColumns.get(6).columnNumber]));
			PlayerName playerName = new PlayerName(columns[projectionColumns.get(0).columnNumber]);
			league.addPlayer(new Hitter(playerName, 
					columns[projectionColumns.get(1).columnNumber],
					league,
					projection));
		}
	}
	
	public static void importPitcherProjections(Reader characterReader) throws IOException
	{
		/*
		 * The format of the file is assumed to be:
		 * 
		 * Tab separated values
		 * 
		 * One line of headers
		 * Headers looked for are
		 * Name
		 * Team
		 * AB
		 * H
		 * HR
		 * RBI
		 * SB
		 * Salary (the salary deserved by this projection)
		 * Projection Type (not assumed to be there)
		 * The Reader will be parsed as the headers determine 
		 * 
		 */
		BufferedReader bReader = new BufferedReader(characterReader);
		String headerString = bReader.readLine();
		String playerLine;
		String[] headers = headerString.split(split_delimiter, -1);
		SheetFormat projectionSheetFormat;
		ArrayList<Column> projectionColumns = new ArrayList<Column>();
		projectionColumns.add(new Column("Name", true));
		projectionColumns.add(new Column("Team", true));
		projectionColumns.add(new Column("W", true));
		projectionColumns.add(new Column("SV", true));
		projectionColumns.add(new Column("IP", true));
		projectionColumns.add(new Column("H", true));
		projectionColumns.add(new Column("ER", true));
		projectionColumns.add(new Column("BB", true));
		projectionColumns.add(new Column("Salary", true));
		projectionColumns.add(new Column("Projection Type", false));
		projectionSheetFormat = new SheetFormat(projectionColumns);
		projectionSheetFormat.getColumnNumbers(headers);
		while(null!=(playerLine=bReader.readLine()))
		{
			String projectionType = "";
			String[] columns = playerLine.split(split_delimiter);
			if(columns.length>8)
			{
				if(projectionColumns.get(9).exists)
				{
					projectionType = columns[projectionColumns.get(8).columnNumber];
				}
				PitchingProjection projection = new PitchingProjection(projectionType, 
						(int)Double.parseDouble(columns[projectionColumns.get(8).columnNumber]),
						Integer.parseInt(columns[projectionColumns.get(2).columnNumber]),
						Integer.parseInt(columns[projectionColumns.get(3).columnNumber]),
						Double.parseDouble(columns[projectionColumns.get(4).columnNumber]),
						Integer.parseInt(columns[projectionColumns.get(5).columnNumber]),
						Integer.parseInt(columns[projectionColumns.get(6).columnNumber]),
						Integer.parseInt(columns[projectionColumns.get(7).columnNumber]));
				PlayerName playerName = new PlayerName(columns[projectionColumns.get(0).columnNumber]);
				league.addPlayer(new Pitcher(playerName, 
						columns[projectionColumns.get(1).columnNumber],
						league,
						projection));
			}
		}
		
	}
	
	public static void importPlayerPositions(Reader characterReader) throws IOException
	{
		/*
		 * The format of the file is assumed to be:
		 * 
		 * Tab separated values
		 * 
		 * One line of headers
		 * Headers looked for are
		 * Name
		 * Team
		 * AB
		 * H
		 * HR
		 * RBI
		 * SB
		 * Salary (the salary deserved by this projection)
		 * Projection Type (not assumed to be there)
		 * The Reader will be parsed as the headers determine 
		 * 
		 */
		BufferedReader bReader = new BufferedReader(characterReader);
		String headerString = bReader.readLine();
		String playerLine;
		String[] headers = headerString.split(split_delimiter, -1);
		SheetFormat positionSheetFormat;
		ArrayList<Column> positionColumns = new ArrayList<Column>();
		positionColumns.add(new Column("Name", true));
		positionColumns.add(new Column("Team", true));
		positionColumns.add(new Column("C", true));
		positionColumns.add(new Column("1B", true));
		positionColumns.add(new Column("2B", true));
		positionColumns.add(new Column("SS", true));
		positionColumns.add(new Column("3B", true));
		positionColumns.add(new Column("OF", true));
		positionColumns.add(new Column("DH", true));
		positionSheetFormat = new SheetFormat(positionColumns);
		positionSheetFormat.getColumnNumbers(headers);
		while(null!=(playerLine=bReader.readLine()))
		{
			String[] columns = playerLine.split(split_delimiter);
			PlayerName playerName = new PlayerName(columns[positionColumns.get(0).columnNumber]);
			Hitter hitter = new Hitter(playerName,
					columns[positionColumns.get(1).columnNumber],
					league,
					Integer.parseInt(columns[positionColumns.get(2).columnNumber]),
					Integer.parseInt(columns[positionColumns.get(3).columnNumber]),
					Integer.parseInt(columns[positionColumns.get(4).columnNumber]),
					Integer.parseInt(columns[positionColumns.get(5).columnNumber]),
					Integer.parseInt(columns[positionColumns.get(6).columnNumber]),
					Integer.parseInt(columns[positionColumns.get(7).columnNumber]),
					Integer.parseInt(columns[positionColumns.get(8).columnNumber])
					);
			Player p = league.findPlayer(hitter);
			if(p != null)
			{
				p.addInformation(hitter);
			}
			else
			{
				System.out.println("Couldn't find player " + hitter.name);
			}
		}
		
		
	}
	
	public static void importFantasyTeams(Reader characterReader) throws IOException
	{
		BufferedReader bReader = new BufferedReader(characterReader);
		String headerString = bReader.readLine();
		String[] headers = headerString.split(split_delimiter, -1);
		ArrayList<Column> teamColumns = new ArrayList<Column>();
		teamColumns.add(new Column("Name", true));
		teamColumns.add(new Column("Team", true));
		teamColumns.add(new Column("Salary", true));
		SheetFormat teamSheetFormat;
		teamSheetFormat = new SheetFormat(teamColumns);
		teamSheetFormat.getColumnNumbers(headers);
		String playerLine;
		while(null!=(playerLine=bReader.readLine()))
		{
			String[] columns = playerLine.split(split_delimiter);
			int salary = 0;
			PlayerName playerName = new PlayerName(columns[teamColumns.get(0).columnNumber]);
			FantasyTeam fantasyTeam = fantasyLeague.getTeam(columns[teamColumns.get(1).columnNumber]);
			try
			{
				salary = Integer.parseInt(columns[teamColumns.get(2).columnNumber]);
			} catch (Exception e)
			{
				
			}
			Player player = new Player(playerName, fantasyTeam, salary);
			player = league.addPlayer(player);
			try
			{
				fantasyTeam.addPlayer(player);
				player.fantasyTeam = fantasyTeam;
			} catch (NoSpotAvailableException e)
			{
				System.out.println("Can't add " + player + "to team " + fantasyTeam.teamName);
			}
		}		
	}
	
	public static ArrayList<Player> getAvailablePlayers()
	{
		ArrayList<Player> availablePlayers = new ArrayList<Player>();
		availablePlayers = new ArrayList<Player>(league.players);
		for(Player p:league.players)
		{
			if(p.fantasyTeam != null)
			{
				System.out.println(p + " is in team " + p.fantasyTeam.teamName);
				availablePlayers.remove(p);
			}
		}
		Collections.sort(availablePlayers, new ProjectionComparator());
		return availablePlayers;
	}

}
