
public class Column {
	String columnName;
	int columnNumber;
	boolean mustBeThere;
	boolean exists;
	public Column(String columnName, boolean mustBeThere)
	{
		this.columnName = columnName;
		this.mustBeThere = mustBeThere;
	}
}
