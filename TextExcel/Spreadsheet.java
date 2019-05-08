public class Spreadsheet implements Grid
{
    private Cell[][] cells;
    
    public Spreadsheet()
    {	
        cells = new Cell[getRows()][getCols()];
        for (int i = 0; i < getRows(); i++)
        {
            for (int j = 0; j < getCols(); j++)
            {
                cells[i][j] = new EmptyCell();
            }
        }
    }

    @Override
    public String processCommand(String command)
    {
        if(command.equals("")) {
            return "";
        }
        else if(command.toUpperCase().equals("CLEAR"))  { //Clearing the entire sheet
            clearSpreadsheet();
            return getGridText();
        }

        else if (command.length() > 5 && command.toUpperCase().substring(0,5).equals("CLEAR")) {  //Clearing a particular cell
            SpreadsheetLocation location = new SpreadsheetLocation(command.substring(6));
            cells[location.getRow()][location.getCol()] = new EmptyCell();
            return getGridText();
        }
        
        else if (command.substring(0,4).toUpperCase().equals("SORT")) {
            SpreadsheetLocation[] cells = new SpreadsheetLocation[2];
            String[] things = command.split(" ");
            String[] range = things[1].split("-");
            SpreadsheetLocation first = new SpreadsheetLocation(range[0]);
            SpreadsheetLocation last = new SpreadsheetLocation(range[1]);
            cells[0] = first;
            cells[1] = last;
            return bubbleSort( cells);///bubbleSort(isAssending, cells);
        }
        
        else if (!command.contains(" ")) { //Cell Inspection because it has no spaces (Courtesy of Enock Luk :P )
            SpreadsheetLocation location = new SpreadsheetLocation(command);
            return cells[location.getRow()][location.getCol()].fullCellText();
        }
        
        else { //Assignment to string values
            SpreadsheetLocation location = new SpreadsheetLocation(command.substring(0,command.indexOf(" ")));
            String input = command.substring(command.indexOf("=") + 2);
            if(input.substring(0,1).equals("\"")){ //String
                cells[location.getRow()][location.getCol()] = new TextCell(input);
            }
            else if(input.substring(0,1).equals("(")) { //Formula
                cells[location.getRow()][location.getCol()] = new FormulaCell(input, this);
            }
            else if(input.contains("%")) { //Percent
                PercentCell percentCell = PercentCell.createPercentCell(input);
                cells[location.getRow()][location.getCol()] = percentCell;
            }
            else { //Value
                cells[location.getRow()][location.getCol()] = new ValueCell(input);
            }
            return getGridText();
        } 
        
        //percent assignment
        //real value assignment
    }
    
    public String bubbleSort( SpreadsheetLocation[] sort) {
        int count = (sort[1].getRow() - sort[0].getRow() + 1) * (sort[1].getCol() - sort[0].getCol() + 1);
        Cell[] x = new Cell[count];
        int counter = 0;
        for(int i = sort[0].getRow(); i <= sort[1].getRow(); i++) {
            for(int j = sort[0].getCol(); j <= sort[1].getCol(); j++) {
                x[counter] = cells[i][j];
                counter++;

            }
        }
        for(int boi = 0; boi < counter; boi++) {
            for(int cell = 0; cell < count - 1; cell++) {
                int compare = compareCells(x[cell], x[cell+1]);
                if(compare > 0){
                    Cell temp = x[cell];
                    x[cell] = x[cell+1];
                    x[cell+1] = temp;
                }
            }
            counter = 0;
            for(int i = sort[0].getRow(); i <= sort[1].getRow(); i++) {
                for( int j = sort[0].getCol(); j <= sort[1].getCol(); j++) {
                    cells[i][j] = x[counter];
                    counter++;
                }
            }
        }
        return getGridText();
    }
    
    
    @Override
    public int getRows()
    {
        return 20;
    }
    
    @Override
    public int getCols()
    {
        return 12;
    }

    @Override
     public Cell getCell(Location location)
    {
        return cells[location.getRow()][location.getCol()];
    }

    @Override
    public String getGridText()
    {
        String grid = "";
        grid += "   |"; //this starts the first line
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWZXYZ";
        for(int col = 0; col < getCols(); col++) {  //This loop creates the 1st line with all the letters. 
            grid += (alphabet.charAt(col));
            grid += "         |";
        }
        grid += "\n";
        for (int row = 1; row <= getRows(); row++) //this creates each row with all the numbers
        {
            grid += row;
            if (row < 10)
                grid += "  |";
            else
                grid += " |";
            for (int col = 0; col < getCols(); col++)
            {
                grid += cells[row - 1][col].abbreviatedCellText();
                grid += "|";
            }
            grid += "\n";
        }
        return grid;        
    }
    
    public static String getAbbreviatedCellText(String input) {
        if (input.length() > 10)  {
            return input.substring(0, 10);
        }
        else if (input.length() < 10) {
            String ACT = input;
            for(int i  = 0; i < 10 - input.length(); i++)  {
                ACT = ACT + " ";
            }
            return ACT;
        }
        else  {
            return input;
        }
    }
    
    public void clearSpreadsheet()
    {
        for(int i = 0; i < getRows(); i++) {
            for(int j = 0; j < getCols(); j++) {
            cells[i][j] = new EmptyCell();
            }  
        }
    }
    
    public int compareCells(Cell x, Cell y) 
    {/***
        if(x instanceof EmptyCell && y instanceof EmptyCell) {
            return 0;
        }
        if(x instanceof EmptyCell) {
            return -1;
        }
        if(y instanceof EmptyCell) {
            return 1;
        }
        if((x instanceof TextCell) && (y instanceof RealCell)) {
            return -1;
        }
        if ((y instanceof TextCell) && (x instanceof RealCell)) {
            return 1;
        }
        ***/
        return ((Comparable<Object>) x).compareTo(y); //help. thanks Brandon and google LOL
    }

}