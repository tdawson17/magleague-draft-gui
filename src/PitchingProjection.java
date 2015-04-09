
public class PitchingProjection extends Projection {
	double inningsPitched;
	int wins;
	int saves;
	int hits;
	int walks;
	int earnedRuns;
	public PitchingProjection(String name, int salary)
	{
		super(name, salary);
	}
	public PitchingProjection(String name, int salary, int wins, int saves, double inningsPitched, int hits, int earnedRuns, int walks)
	{
		super(name, salary);
		this.wins = wins;
		this.saves = saves;
		this.inningsPitched = inningsPitched;
		this.hits = hits;
		this.earnedRuns = earnedRuns;
		this.walks = walks;
				
	}
	public String toString()
	{
		String ret_val = super.toString() + "\t";
		ret_val += wins + "\t" + saves + "\t" + inningsPitched + "\t" + hits + "\t" + earnedRuns + "\t" + walks;
		return ret_val;
	}
	public String getFullString(String delimiter)
	{
		String ret_val = super.getFullString(delimiter) + delimiter;
		ret_val += wins + delimiter  + saves + delimiter  + inningsPitched + delimiter  + hits + delimiter  + earnedRuns + delimiter  + walks;
		return ret_val;
	}

	public String getProjectionHeader(String delimiter) 
	{
		String ret_val = super.getProjectionHeader(delimiter) + delimiter;
		ret_val += "W" + delimiter  + "SV" + delimiter  + "IP" + delimiter  + "H" + delimiter  + earnedRuns + delimiter  + walks;
		return ret_val;
	}
}
