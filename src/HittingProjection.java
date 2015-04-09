
public class HittingProjection extends Projection {
	int homeruns;
	int rbi;
	int sb;
	int ab;
	int h;
	public HittingProjection(String name, int salary)
	{
		super(name, salary);
	}
	public HittingProjection(String name, int salary, int ab, int h, int homeruns, int rbi, int sb)
	{
		super(name, salary);
		this.ab = ab;
		this.h = h;
		this.homeruns = homeruns;
		this.rbi = rbi;
		this.sb = sb;
	}
	public String toString()
	{
		String ret_val = super.toString() + "\t";
		ret_val += ab + "\t" + h + "\t" + homeruns + "\t" + rbi + "\t" + sb;
		return ret_val;
	}
	public String getFullString(String delimiter)
	{
		String ret_val = super.getFullString(delimiter) + delimiter;
		ret_val += ab + delimiter + h + delimiter + homeruns + delimiter + rbi + delimiter + sb;
		return ret_val;
	}

	public String getProjectionHeader(String delimiter) 
	{
		String ret_val = super.getProjectionHeader(delimiter) + delimiter;
		ret_val += "AB" + delimiter + "H" + delimiter + "HR" + delimiter + "RBI" + delimiter + "SB";
		return ret_val;
	}
}
