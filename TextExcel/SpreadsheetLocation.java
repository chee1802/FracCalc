public class SpreadsheetLocation implements Location
{
    
    private int row;
    private int col;

    public SpreadsheetLocation(String cellName)
    {
        String alphabet = "ABCDEFGHIJKL";
        row = Integer.parseInt(cellName.substring(1)) - 1;
        col = alphabet.indexOf(cellName.toUpperCase().substring(0,1));
    }   
        
    public SpreadsheetLocation(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    @Override
    public int getRow()
    {
        return row;
    }

    @Override
    public int getCol()
    {
        return col;
    }
    
    public String toString()
    {
	return "" + (char) ('A' + col) + (row + 1);
    }

}
