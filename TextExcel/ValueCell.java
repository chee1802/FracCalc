public class ValueCell extends RealCell 
{
    public ValueCell(String input)
    {
        super(input);
    }

    @Override
    public double getDoubleValue() 
    {
	return Double.parseDouble(fullCellText()); //.parseDouble turns the string into a double (stackoverflow)
    }
}
