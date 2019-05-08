
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class A_Checkpoint1
{
    // Tests for checkpoint 1.
    // Pass them all, plus ensure main loop until quit works, for full credit on checkpoint 1.
    // Note these must also pass for all subsequent checkpoints including final project.
    Grid grid;

    @Before
    public void initializeGrid()
    {
        grid = new Spreadsheet();
    }

    @Test
    public void testGetRows()
    {
        assertEquals("getRows", 20, grid.getRows());
    }

    @Test
    public void testGetCols()
    {
        assertEquals("getCols", 12, grid.getCols());
    }

    @Test
    public void testProcessCommand()
    {
        String str = grid.processCommand("");
        assertEquals("output from empty command", "", str);
    }

    @Test
    public void testLongShortStringCell()
    {
        SpreadsheetLocation loc = new SpreadsheetLocation("L20");
        assertEquals("SpreadsheetLocation column", loc.getCol(), 11);
        assertEquals("SpreadsheetLocation row", loc.getRow(), 19);

        loc = new SpreadsheetLocation("D5");
        assertEquals("SpreadsheetLocation column", loc.getCol(), 3);
        assertEquals("SpreadsheetLocation row", loc.getRow(), 4);

        loc = new SpreadsheetLocation("A1");
        assertEquals("SpreadsheetLocation column", loc.getCol(), 0);
        assertEquals("SpreadsheetLocation row", loc.getRow(), 0);
    }

    @Test
    public void testProcessCommandNonliteralEmpty()
    {
        String input = " ".trim();
        String output = grid.processCommand(input);
        assertEquals("output from empty command", "", output);
    }
}
