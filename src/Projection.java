
public class Projection {
	private String name;
	public int salary;
	public Projection(String name, int salary)
	{
		this.name = name;
		this.salary = salary;
	}
	public String toString()
	{
		String ret_val = "";
		if(this.name != null)
		{
			if(this.name.length() > 0)
			{
				ret_val += this.name;
			}
		}
		return ret_val;
	}

	public String getFullString(String delimiter)
	{
		String ret_val = "";
		if(this.name != null)
		{
			if(this.name.length() > 0)
			{
				ret_val += this.name + delimiter;
			}
		}
		ret_val += Integer.toString(salary);
		return ret_val;
	}

	public String getProjectionHeader(String delimiter) {
		String ret_val = "";
		if(this.name != null)
		{
			if(this.name.length() > 0)
			{
				ret_val += this.name + delimiter;
			}
		}
		ret_val += "Projected Salary";
		return ret_val;
	}
}
