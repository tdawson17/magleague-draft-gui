
public class NoSpotAvailableException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSpotAvailableException(Player player)
	{
		super("There is no spot available for " + player );
	}
}
