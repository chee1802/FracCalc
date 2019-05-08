  public class FormulaCell extends RealCell
{
    private Spreadsheet spreadsheet;
    
    public FormulaCell(String input, Spreadsheet spreadsheet) {
        super(input);
        this.spreadsheet = spreadsheet;
    }
    
    @Override
    public double getDoubleValue() //getDoubleValue evaluate expression
    {
	String cell = fullCellText();
	String[] things = cell.split(" "); //https://www.w3docs.com/snippets/java/how-to-split-a-string-in-java.html
	                                 //Used very cool split method in array from url above
	                                 //creates an array with everything, number/methods/operations
	if (things[1].toUpperCase().equals("SUM") || things[1].toUpperCase().equals("AVG")) {
	    return getMethodFormula(things);
	   }
	   else {
	       return getNormalFormula(things);
	   }
    }
    
    private double getNormalFormula(String[] things)
    {
        double x;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if(alphabet.indexOf(things[1].charAt(0)) > -1) { //if thing is a character, it's a realcell
            SpreadsheetLocation location = new SpreadsheetLocation(things[1]);
            RealCell cell = (RealCell)spreadsheet.getCell(location);
            x = cell.getDoubleValue();
        }
        else {
            x = Double.parseDouble(things[1]);
        }
        for (int i = 3; i < things.length - 1; i += 2) { //start at 3 b/c 0 = (, 1 = 1st num already define
                                                         //2 = operator, 3 = 2nd numberskip every 2
            String operator = things[i-1]; //operator = the thing before the number
            double y;
            if(alphabet.indexOf(things[i].charAt(0)) > -1) { 
                SpreadsheetLocation location = new SpreadsheetLocation(things[i]);
                RealCell cell = (RealCell)spreadsheet.getCell(location);
                y = cell.getDoubleValue();
            }
            else {
                y = Double.parseDouble(things[i]);
            }            
            if (operator.equals("+")) {
                x += y;
            }
            else if (operator.equals("-")) {
                x -= y;
            }
            else if (operator.equals("*")) {
                x *= y;
            }
            else if (operator.equals("/")) {
                x /= y;
            }
        }
        return x;	
    }
    public double getValue(String value)
    {
        if (Character.isLetter(value.charAt(0))) {
            SpreadsheetLocation location = new SpreadsheetLocation(value);
	    RealCell realCell = (RealCell)spreadsheet.getCell(location);
	    return realCell.getDoubleValue();
	}
        else {
            return Double.parseDouble(value);	
	}
    }
    
    private double getMethodFormula(String[] args) 
    {
        double x = 0.0;
        String first = args[2].substring(0, args[2].indexOf("-"));
        SpreadsheetLocation fLocation = new SpreadsheetLocation(first);
        String last = args[2].substring(args[2].indexOf("-") + 1);
        SpreadsheetLocation lLocation = new SpreadsheetLocation(last);
        int count = 0;
        for (int row = fLocation.getRow(); row <= lLocation.getRow(); row++) { //pretty cool trick b/c char A is less than char B
            for (int col = fLocation.getCol(); col <= lLocation.getCol(); col++) {
                SpreadsheetLocation location = new SpreadsheetLocation(row, col);
		RealCell cell = (RealCell)spreadsheet.getCell(location);		
		x += cell.getDoubleValue();	//does the math based on DoubleValue	
		count++;		
	    }
	}
	if (args[1].toUpperCase().equals("AVG")) {
	    x /= count;
	    return x;
	}
	else {
	    return x;
	}
    }
    

}
