
public class TextCell implements Cell, Comparable<TextCell>
{
    private String input;
    
    public TextCell(String input)
    {
        this.input = input;
    }
    
    @Override
    public String abbreviatedCellText()
    {
        return Spreadsheet.getAbbreviatedCellText(input.substring(1, input.length() - 1));
    }
    
    @Override
    public String fullCellText()
    {
        return input;
    }
    
    public int compareTo(TextCell comparison)
    {
        return this.fullCellText().compareTo(comparison.fullCellText()); //https://www.javatpoint.com/java-string-compareto
    }        

}
