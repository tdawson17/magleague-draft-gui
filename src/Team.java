
public abstract class Team implements Comparable<Team>{
	protected String teamName;
	public Team(String teamName)
	{
		this.teamName = teamName;
	}
	public void setTeamName(String teamName)
	{
		this.teamName = teamName;
	}
	public String getTeamName()
	{
		return teamName;
	}
	public int compareTo(Team o) {
		return teamName.compareTo(o.teamName);
	}
	public String toString(){
		return teamName;
	}
}
