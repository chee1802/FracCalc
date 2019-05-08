public abstract class RealCell implements Cell, Comparable<RealCell>
{
    private String input;
    
    public RealCell(String input)
    {
        this.input = input;
    }
    
    public abstract double getDoubleValue();
    
    @Override
    public String abbreviatedCellText()
    {
        String valueString = "" + getDoubleValue();
        return Spreadsheet.getAbbreviatedCellText(Double.toString(getDoubleValue()));
    }
    
    @Override
    public String fullCellText()
    {
        return input;
    }
    
    public int compareTo(RealCell compare)
    {
        if (this.getDoubleValue() < compare.getDoubleValue()) {
            return -1;
        }
        if (this.getDoubleValue() > compare.getDoubleValue()) {
            return 1;
        }
        return 0;
    }
}
