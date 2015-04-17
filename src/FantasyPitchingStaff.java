import java.util.ArrayList;
import java.util.Collection;


public class FantasyPitchingStaff {
	ArrayList<Pitcher> pitchers;
	static int NUMBER_OF_PITCHERS = 9;
	public FantasyPitchingStaff()
	{
		pitchers = new ArrayList<Pitcher>(NUMBER_OF_PITCHERS);
	}
	public int getTotalSalary()
	{
		int totalSalary = 0;
		int i;
		for(i=0; i<pitchers.size(); i++)
		{
			totalSalary += pitchers.get(i).getSalary();
		}
		return totalSalary;
	}
	public int getNumberFreeSpots() {
		return NUMBER_OF_PITCHERS - pitchers.size();
	}
	public void addPitcher(Pitcher pitcher) throws NoSpotAvailableException
	{
		if(pitchers.size() >= NUMBER_OF_PITCHERS)
		{
			throw new NoSpotAvailableException(pitcher);
		}
		pitchers.add(pitcher);
	}
	public String getFullString(String delimiter) {
		String ret_val = "";
		int i;
		for(i = 0; i<9; i++)
		{
			if(pitchers.size() > i)
			{
				ret_val += "P" + delimiter + pitchers.get(i).getFullString(delimiter) + System.lineSeparator();
			} else 
			{
				ret_val += "P" + System.lineSeparator();
			}
		}
		return ret_val;
	}

	public ArrayList<String> getPitchingStaffStrings() {
		ArrayList<String> ret_val = new ArrayList<String>();
		for(int i=0;i<NUMBER_OF_PITCHERS;i++)
		{
			ret_val.add("P");
			if(pitchers.size()>i)
			{
				ret_val.addAll(pitchers.get(i).getPlayerStrings());
			} else 
			{
				ret_val.addAll(Player.getNullPlayerStrings());
			}
		}
		return ret_val;
	}
}
