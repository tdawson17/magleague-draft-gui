import java.util.ArrayList;


public class Player implements Comparable<Player>{
	public PlayerName name;
	MLBTeam mlbTeam;
	FantasyTeam fantasyTeam;
	int salary;
	ArrayList<Projection> projections;
	protected Player(PlayerName name, String mlbTeam, MLBLeague league)
	{
		this.name = name;
		if(league != null)
		{
			this.mlbTeam = league.getTeam(mlbTeam);
		}
		this.fantasyTeam = null;
		this.salary = 0;
		projections = new ArrayList<Projection>();
	}
	protected Player(PlayerName name, String mlbTeam, MLBLeague league, Projection projection)
	{
		this.name = name;
		this.mlbTeam = league.getTeam(mlbTeam);
		this.fantasyTeam = null;
		this.salary = 0;
		projections = new ArrayList<Projection>();
		projections.add(projection);
	}
	public Player(PlayerName name, FantasyTeam fantasyTeam, int salary)
	{
		this.name = name;
		this.fantasyTeam = fantasyTeam;
		this.salary = salary;
		projections = new ArrayList<Projection>();
	}
	public Player()
	{
		
	}
	public String toString()
	{
		return name.toString();
	}
	public int getSalary()
	{
		return salary;
	}
	public int compareTo(Player o) {
		int ret_val = name.compareTo(o.name);
		if(ret_val == 0)
		{
			if((mlbTeam != null) && (o.mlbTeam != null))
			{
				ret_val = mlbTeam.compareTo(o.mlbTeam);
			}
		}
		return ret_val; 
	}
	public void addInformation(Player p) {
		if(p.fantasyTeam != null)
		{
			fantasyTeam = p.fantasyTeam;
			salary = p.salary;
		}
	}
	public String getFullString(String delimiter) {
		String ret_val = this.name.toString() + delimiter + this.mlbTeam.teamName + delimiter;
		if(this.fantasyTeam != null)
		{
			ret_val += this.fantasyTeam.teamName + delimiter + this.salary; 
		} else
		{
			ret_val += delimiter;
		}
		ret_val += delimiter;
		if(this.fantasyTeam != null)
		{
			ret_val += this.salary;
		}
		
		for(Projection p:this.projections)
		{
			ret_val += delimiter + p.getFullString(delimiter);
		}
		
		return ret_val;
	}
	public String getPlayerHeader(String delimiter) {
		String ret_val = "Name" + delimiter + "MLBTeam" + delimiter + "FantasyTeam" + delimiter + "Salary";
		for(Projection p:this.projections)
		{
			ret_val += delimiter + p.getProjectionHeader(delimiter);
		}
		return ret_val;
	}
	
}
