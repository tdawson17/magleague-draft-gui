import java.util.Comparator;


public class ProjectionComparator implements Comparator<Player> {

	@Override
	public int compare(Player arg0, Player arg1) {
		if((arg0.projections.size()==0) | (arg1.projections.size()==0))
		{
			return arg1.projections.size() - arg0.projections.size();
		}
		Projection p0 = arg0.projections.get(0);
		Projection p1 = arg1.projections.get(0);
		
		return p1.salary - p0.salary;
	}

}
