
public class Hitter extends Player {
	private int catcherGames;
	private int firstBaseGames;
	private int secondBaseGames;
	private int shortstopGames;
	private int thirdBaseGames;
	private int outfieldGames;
	private int designatedHitterGames;
	boolean bAddedPlayerGames = false;
	public static int GAMES_REQUIRED = 20;
	public static double PERCENTAGE_REQUIRED = 0.4;
	public Hitter(PlayerName name, String mlbTeam, MLBLeague league, int c, int firstBase, int secondBase, int shortstop, int thirdBase, int outfield, int dh)
	{
		super(name, mlbTeam, league);
		catcherGames = c;
		firstBaseGames = firstBase;
		secondBaseGames = secondBase;
		shortstopGames = shortstop;
		thirdBaseGames = thirdBase;
		outfieldGames = outfield;
		designatedHitterGames = dh;
		bAddedPlayerGames = true;
	}
	public Hitter(PlayerName name, String mlbTeam, MLBLeague league, HittingProjection projection)
	{
		super(name, mlbTeam, league, projection);
	}
	public boolean canPlayPosition(int positionGames)
	{
		if(!bAddedPlayerGames)
		{
			return false;
		}
		if(positionGames > GAMES_REQUIRED)
		{
			return true;
		}
		if((positionGames >= catcherGames)
				&& (positionGames >= firstBaseGames)
				&& (positionGames >= secondBaseGames)
				&& (positionGames >= thirdBaseGames)
				&& (positionGames >= shortstopGames)
				&& (positionGames >= outfieldGames)
				&& (positionGames >= designatedHitterGames))
		{
			return true;
		}
		if(PERCENTAGE_REQUIRED<=(double)positionGames/(double)(catcherGames+firstBaseGames+secondBaseGames+shortstopGames+thirdBaseGames+outfieldGames+designatedHitterGames))
		{
			return true;
		}
		return false;
	}
	public boolean canPlayCatcher()
	{
		return canPlayPosition(catcherGames);
	}
	public boolean canPlayFirstBase()
	{
		return canPlayPosition(firstBaseGames);
	}
	public boolean canPlaySecondBase()
	{
		return canPlayPosition(secondBaseGames);
	}
	public boolean canPlayThirdBase()
	{
		return canPlayPosition(thirdBaseGames);
	}
	public boolean canPlayShortstop()
	{
		return canPlayPosition(shortstopGames);
	}
	public boolean canPlayOutfield()
	{
		return canPlayPosition(outfieldGames);
	}
	public boolean canPlayDesignatedHitter()
	{
		return canPlayPosition(designatedHitterGames);
	}
	@Override
	public void addInformation(Player p) {
		super.addInformation(p);
		if(p instanceof Hitter)
		{
			if(((Hitter) p).catcherGames >0)
			{
				catcherGames = ((Hitter) p).catcherGames;
			}
			if(((Hitter) p).firstBaseGames>0)
			{
				firstBaseGames = ((Hitter) p).firstBaseGames;
			}
			if(((Hitter) p).secondBaseGames>0)
			{
				secondBaseGames = ((Hitter) p).secondBaseGames;
			}
			if(((Hitter) p).shortstopGames>0)
			{
				shortstopGames = ((Hitter) p).shortstopGames;
			}
			if(((Hitter) p).thirdBaseGames>0)
			{
				thirdBaseGames = ((Hitter) p).thirdBaseGames;
			}
			if(((Hitter) p).outfieldGames>0)
			{
				outfieldGames = ((Hitter) p).outfieldGames;
			}
			if(((Hitter) p).designatedHitterGames>0)
			{
				designatedHitterGames = ((Hitter) p).designatedHitterGames;
			}
			if(((Hitter)p).bAddedPlayerGames)
			{
				this.bAddedPlayerGames = true;
			}
		}
	}

	public String getFullString(String delimiter)
	{
		String ret_val = super.getFullString(delimiter) + delimiter;
		if(this.bAddedPlayerGames)
		{
			if(canPlayCatcher())
			{
				ret_val += "C";
			}
			if(canPlayFirstBase())
			{
				ret_val += "1";
			}
			if(canPlaySecondBase())
			{
				ret_val += "2";
			}
			if(canPlayShortstop())
			{
				ret_val += "S";
			}
			if(canPlayThirdBase())
			{
				ret_val += "3";
			}
			if(canPlayOutfield())
			{
				ret_val += "O";
			}
			if(canPlayDesignatedHitter())
			{
				ret_val += "D";
			}
		}
		return ret_val;
	}
	public String getPlayerHeader(String delimiter)
	{
		String ret_val = super.getPlayerHeader(delimiter);
		ret_val += delimiter + "Pos";
		return ret_val;
	}
}
