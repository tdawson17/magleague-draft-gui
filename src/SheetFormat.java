import java.io.IOException;
import java.util.ArrayList;


public class SheetFormat {
	ArrayList<Column> columns;
	public SheetFormat(ArrayList<Column> columns)
	{
		this.columns = columns;
	}
	public void getColumnNumbers(String[] headers) throws IOException
	{
		int i;
		for(i=0; i<headers.length; i++)
		{
			for(Column column:columns)
			{
				if(column.columnName.compareTo(headers[i]) == 0)
				{
					column.columnNumber = i;
					column.exists = true;
				}
			}
		}
		for(Column column:columns)
		{
			if(column.mustBeThere && !column.exists)
			{
				throw new IOException("couldn't find at least one column");
			}
		}		
	}
	public Column getColumn(String string) {
		for(Column column:columns)
		{
			if(string.compareTo(column.columnName) == 0)
			{
				return column;
			}
		}		
		return null;
	}
	public int getColumnNumber(String string) throws Exception{
		for(Column column:columns)
		{
			if(string.compareTo(column.columnName) == 0)
			{
				return column.columnNumber;
			}
		}
		throw new Exception("Couldn't find column");
	}
}
