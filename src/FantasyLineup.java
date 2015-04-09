import java.util.ArrayList;


public class FantasyLineup {
	ArrayList<Hitter> catchers;
	Hitter firstBase;
	Hitter secondBase;
	Hitter shortstop;
	Hitter thirdBase;
	ArrayList<Hitter> outfield;
	Hitter corner;
	Hitter middleInfield;
	Hitter designatedHitter;
	static int NUMBER_OF_OUTFIELDERS = 5;
	static int NUMBER_OF_CATCHERS = 2;
	static int CATCHER = 1;
	static int FIRST_BASE = 2;
	static int SECOND_BASE = 4;
	static int SHORTSTOP = 8;
	static int THIRD_BASE = 16;
	static int CORNER = 32;
	static int MIDDLE_INFIELD = 64;
	static int DESIGNATED_HITTER = 128;
	static int OUTFIELD = 256;
	static int NO_POSITION = 0;
	public FantasyLineup()
	{
		catchers = new ArrayList<Hitter>(NUMBER_OF_CATCHERS);
		firstBase = null;
		secondBase = null;
		thirdBase = null;
		shortstop = null;
		outfield = new ArrayList<Hitter>(NUMBER_OF_OUTFIELDERS);
		corner = null;
		middleInfield = null;
		designatedHitter = null;
	}
	public void addHitter(Hitter hitter) throws NoSpotAvailableException
	{
		
		if(!canHitterBeAdded(hitter, true, NO_POSITION))
		{
			throw new NoSpotAvailableException(hitter);
		}
		
	}
	public boolean canHitterBeAdded(Hitter hitter, boolean bAdd, int positionFlags)
	{
		boolean bCanC = false;
		boolean bCanDH = false;
		boolean bCan1B = false;
		boolean bCan2B = false;
		boolean bCanSS = false;
		boolean bCan3B = false;
		boolean bCanOF = false;
		if(hitter.canPlayCatcher())
		{
			if(hasCatcherSpotAvailable(hitter, bAdd))
			{
				return true;
			}
			bCanC = true;
		}
		if(hitter.canPlayDesignatedHitter())
		{
			if(hasDesignatedHitterSpotAvailable(hitter, bAdd))
			{
				return true;
			}
			bCanDH = true;
		}
		if(hitter.canPlayFirstBase())
		{
			if(hasFirstBaseSpotAvailable(hitter, bAdd))
			{
				return true;
			}
			bCan1B = true;
		}
		if(hitter.canPlaySecondBase())
		{
			if(hasSecondBaseSpotAvailable(hitter, bAdd))
			{
				return true;
			}
			bCan2B = true;
		}
		if(hitter.canPlayThirdBase())
		{
			if(hasThirdBaseSpotAvailable(hitter, bAdd))
			{
				return true;
			}
			bCan3B = true;
		}
		if(hitter.canPlayShortstop())
		{
			if(hasShortstopSpotAvailable(hitter, bAdd))
			{
				return true;
			}
			bCanSS = true;
		}
		if(hitter.canPlayOutfield())
		{
			if(hasOutfieldSpotAvailable(hitter, bAdd))
			{
				return true;
			}
			bCanOF = true;
		}
		if(bCan1B)
		{
			if((positionFlags & FIRST_BASE) == NO_POSITION) 
			{
				if(canHitterBeAdded(firstBase, bAdd, positionFlags | FIRST_BASE))
				{
					if(bAdd)
					{
						secondBase = hitter;
					}
					return true;
				}
			}
		}
		if(bCan2B)
		{
			if((positionFlags & SECOND_BASE) == NO_POSITION) 
			{
				if(canHitterBeAdded(secondBase, bAdd, positionFlags | SECOND_BASE))
				{
					if(bAdd)
					{
						secondBase = hitter;
					}
					return true;
				}
			}
		}
		if(bCanSS)
		{
			if((positionFlags & SHORTSTOP) == NO_POSITION) 
			{
				if(canHitterBeAdded(shortstop, bAdd, positionFlags | SHORTSTOP))
				{
					if(bAdd)
					{
						shortstop = hitter;
					}
					return true;
				}
			}
		}
		if(bCan3B)
		{
			if((positionFlags & THIRD_BASE) == NO_POSITION) 
			{
				if(canHitterBeAdded(thirdBase, bAdd, positionFlags | THIRD_BASE))
				{
					if(bAdd)
					{
						thirdBase = hitter;
					}
					return true;
				}
			}
		}
		if(bCan1B || bCan3B)
		{
			if((positionFlags & CORNER) == NO_POSITION) 
			{
				if(canHitterBeAdded(corner, bAdd, positionFlags | CORNER))
				{
					if(bAdd)
					{
						corner = hitter;
					}
					return true;
				}
			}
		}
		if(bCan2B || bCanSS)
		{
			if((positionFlags & MIDDLE_INFIELD) == NO_POSITION) 
			{
				if(canHitterBeAdded(middleInfield, bAdd, positionFlags | MIDDLE_INFIELD))
				{
					if(bAdd)
					{
						middleInfield = hitter;
					}
					return true;
				}
			}
		}
		if(bCanDH)
		{
			if((positionFlags & DESIGNATED_HITTER) == NO_POSITION) 
			{
				if(canHitterBeAdded(designatedHitter, bAdd, positionFlags | DESIGNATED_HITTER))
				{
					if(bAdd)
					{
						designatedHitter = hitter;
					}
					return true;
				}
			}
		}
		if(bCanC)
		{
			if((positionFlags & CATCHER) == NO_POSITION) 
			{
				for(int i = 0;i<catchers.size();i++)
				{
					if(canHitterBeAdded(catchers.get(i), bAdd, positionFlags | CATCHER))
					{
						if(bAdd)
						{
							catchers.set(i, hitter);
						}
						return true;
					}
				}
			}
		}
		if(bCanOF)
		{
			if((positionFlags & OUTFIELD) == NO_POSITION) 
			{
				for(int i = 0;i<outfield.size();i++)
				{
					if(canHitterBeAdded(outfield.get(i), bAdd, positionFlags | OUTFIELD))
					{
						if(bAdd)
						{
							catchers.set(i, hitter);
						}
						return true;
					}
				}
			}
		}
		return false;
	}
		
	private boolean hasOutfieldSpotAvailable(Hitter hitter, boolean bAdd) {
		if(outfield.size()<NUMBER_OF_OUTFIELDERS)
		{
			if(bAdd)
			{
				outfield.add(hitter);
			}
			return true;
		}
		return false;
	}
	private boolean hasShortstopSpotAvailable(Hitter hitter, boolean bAdd) {
		if(shortstop == null)
		{
			if(bAdd)
			{
				shortstop = hitter;
			}
			return true;
		}
		if(middleInfield == null)
		{
			if(bAdd)
			{
				middleInfield = hitter;
			}
			return true;
		}
		return false;
	}

	private boolean hasThirdBaseSpotAvailable(Hitter hitter, boolean bAdd) {
		if(thirdBase == null)
		{
			if(bAdd)
			{
				thirdBase = hitter;
			}
			return true;
		}
		if(corner == null)
		{
			if(bAdd)
			{
				corner = hitter;
			}
			return true;
		}
		return false;
	}
	private boolean hasSecondBaseSpotAvailable(Hitter hitter, boolean bAdd) {
		if(secondBase == null)
		{
			if(bAdd)
			{
				secondBase = hitter;
			}
			return true;
		}
		if(middleInfield == null)
		{
			if(bAdd)
			{
				middleInfield = hitter;
			}
			return true;
		}
		return false;
	}
	private boolean hasFirstBaseSpotAvailable(Hitter hitter, boolean bAdd) {
		if(firstBase == null)
		{
			if(bAdd)
			{
				firstBase = hitter;
			}
			return true;
		}
		if(corner == null){
			if(bAdd)
			{
				corner = hitter;
			}
			return true;
		}
		return false;
	}
	private boolean hasDesignatedHitterSpotAvailable(Hitter hitter, boolean bAdd) {
		if(designatedHitter == null)
		{
			if(bAdd)
			{
				designatedHitter = hitter;
			}
			return true;
		}
		return false;
	}
	private boolean hasCatcherSpotAvailable(Hitter hitter, boolean bAdd) {
		if(catchers.size() < NUMBER_OF_CATCHERS)
		{
			if(bAdd)
			{
				catchers.add(hitter);
			}
			return true;
		}
		return false;
	}
	public int getTotalSalary()
	{
		int totalSalary = 0;
		int i;
		for(i=0; i<catchers.size(); i++)
		{
			totalSalary += catchers.get(i).getSalary();
		}
		for(i=0; i<outfield.size(); i++)
		{
			totalSalary += outfield.get(i).getSalary();
		}
		if(firstBase!=null)
		{
			totalSalary += firstBase.getSalary();
		}
		if(secondBase!=null)
		{
			totalSalary += secondBase.getSalary();
		}
		if(thirdBase!=null)
		{
			totalSalary += thirdBase.getSalary();
		}
		if(shortstop!=null)
		{
			totalSalary += shortstop.getSalary();
		}
		if(corner!=null)
		{
			totalSalary += corner.getSalary();
		}
		if(middleInfield!=null)
		{
			totalSalary += middleInfield.getSalary();
		}
		return totalSalary;
	}

	public int getNumberFreeSpots() {
		int numFreeSpots = 0;
		numFreeSpots += NUMBER_OF_CATCHERS - catchers.size();
		numFreeSpots += NUMBER_OF_OUTFIELDERS - outfield.size();
		if(firstBase == null)
		{
			numFreeSpots++;
		}
		if(secondBase == null)
		{
			numFreeSpots++;
		}
		if(thirdBase == null)
		{
			numFreeSpots++;
		}
		if(shortstop == null)
		{
			numFreeSpots++;
		}
		if(corner == null)
		{
			numFreeSpots++;
		}
		if(middleInfield == null)
		{
			numFreeSpots++;
		}
		if(designatedHitter == null)
		{
			numFreeSpots++;
		}
		return numFreeSpots;
	}
	
	public String getFullString(String delimiter) {
		String ret_val = "";
		int i;
		
		for(i = 0; i<2; i++)
		{
			if(catchers.size() > i)
			{
				ret_val += "C" + delimiter + catchers.get(i).getFullString(delimiter) + System.lineSeparator();
			} else 
			{
				ret_val += "C" + System.lineSeparator();
			}
		}
		if(firstBase != null)
		{
			ret_val += "1B" + delimiter + firstBase.getFullString(delimiter) + System.lineSeparator();
		} else
		{
			ret_val += "1B" + System.lineSeparator();
		}
		if(secondBase != null)
		{
			ret_val += "2B" + delimiter + secondBase.getFullString(delimiter) + System.lineSeparator();
		} else
		{
			ret_val += "2B" + System.lineSeparator();
		}
		if(shortstop != null)
		{
			ret_val += "SS" + delimiter + shortstop.getFullString(delimiter) + System.lineSeparator();
		} else
		{
			ret_val += "SS" + System.lineSeparator();
		}
		if(thirdBase != null)
		{
			ret_val += "3B" + delimiter + thirdBase.getFullString(delimiter) + System.lineSeparator();
		} else
		{
			ret_val += "3B" + System.lineSeparator();
		}
		if(middleInfield != null)
		{
			ret_val += "MI" + delimiter + middleInfield.getFullString(delimiter) + System.lineSeparator();
		} else
		{
			ret_val += "MI" + System.lineSeparator();
		}
		if(corner != null)
		{
			ret_val += "CR" + delimiter + corner.getFullString(delimiter) + System.lineSeparator();
		} else
		{
			ret_val += "CR" + System.lineSeparator();
		}
		for(i = 0; i<5; i++)
		{
			if(outfield.size() > i)
			{
				ret_val += "OF" + delimiter + outfield.get(i).getFullString(delimiter) + System.lineSeparator();
			} else 
			{
				ret_val += "OF" + System.lineSeparator();
			}
		}
		if(designatedHitter != null)
		{
			ret_val += "DH" + delimiter + designatedHitter.getFullString(delimiter) + System.lineSeparator();
		} else
		{
			ret_val += "DH" + System.lineSeparator();
		}
		return ret_val;
	}
}
