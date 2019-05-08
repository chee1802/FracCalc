public class PercentCell extends ValueCell
{
    public static PercentCell createPercentCell(String input)
    {   
        input = input.substring(0, input.length() - 1); //this removes the percent sign
        double inputDouble = Double.parseDouble(input) / 100; //this turns the percent into a decimal    
        return new PercentCell(Double.toString(inputDouble));
    }
    
    public PercentCell(String input)
    {
        super(input);
    }

    public String abbreviatedCellText() 
    {
        String percent = "" + ((int)(getDoubleValue() * 100)) + "%"; //turns the double into an String integer + %
        return Spreadsheet.getAbbreviatedCellText(percent);
    }
}
