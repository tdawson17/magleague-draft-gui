
public class PlayerName implements Comparable<PlayerName>{
	String firstName;
	String lastName;
	public PlayerName(String fullName)
	{
		String[] names = fullName.split(",");
		if(names.length == 1)
		{
			int endOfFirstName = names[0].lastIndexOf(" ");
			if(endOfFirstName == -1)
			{
				firstName = "";
				lastName = names[0];
			} else
			{
				firstName = names[0].substring(0, endOfFirstName);
				lastName = names[0].substring(endOfFirstName+1);
			}
		} else
		{
			firstName = names[1].trim();
			lastName = names[0].trim();
		}
	}
	public PlayerName() {
		firstName = "";
		lastName = "";
	}
	@Override
	public int compareTo(PlayerName o) {
		int ret_val = lastName.compareToIgnoreCase(o.lastName);
		if(ret_val == 0)
		{
			ret_val = firstName.compareToIgnoreCase(o.firstName);
		}
		
		return ret_val;
	}
	public String toString()
	{
		return firstName + " " + lastName;
	}
}
