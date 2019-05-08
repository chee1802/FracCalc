 

//*******************************************************
//DO NOT MODIFY THIS FILE!!!
//*******************************************************

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    A_Checkpoint1.class,
    A_Checkpoint2.class,
    A_Checkpoint3.class,
    B_Checkpoint1.class,
    B_Final.class,
    C_Checkpoint1.class,
    C_Final.class
})

public class TestsALL
{
    public static class TestLocation implements Location
    {
        // Simple implementation of Location interface for use only by tests.
        private int row;
        private int col;

        public TestLocation(int row, int col)
        {
            this.row = row;
            this.col = col;
        }

        @Override
        public int getRow() {
            return row;
        }

        @Override
        public int getCol() {
            return col;
        }
    }

    public static class Helper
    {
        // For use only by test code, which uses it carefully.
        private String[][] items;

        public Helper()
        {
            items = new String[20][12];
            for (int i = 0; i < 20; i++)
                for (int j = 0; j < 12; j++)
                    items[i][j] = format("");
        }

        public static String format(String s)
        {
            return String.format(String.format("%%-%d.%ds", 10, 10),  s);
        }

        public void setItem(int row, int col, String text)
        {
            items[row][col] = format(text);
        }

        public String getText()
        {
            String ret = "   |";
            for (int j = 0; j < 12; j++)
                ret = ret + format(Character.toString((char)('A' + j))) + "|";
            ret = ret + "\n";
            for (int i = 0; i < 20; i++)
            {
                ret += String.format("%-3d|", i + 1);
                for (int j = 0; j < 12; j++)
                {
                    ret += items[i][j] + "|";
                }
                ret += "\n";
            }
            return ret;
        }
    }
}
