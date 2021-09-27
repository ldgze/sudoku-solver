package SudokuView;

import javax.swing.*;

public class SudokuCell extends JTextField {
    public int row, col;
    private int val;

    public SudokuCell(int row, int col) {
        this.row = row;
        this.col = col;
        setHorizontalAlignment(CENTER);
    }

    public SudokuCell(int row, int col, int val)
    {
        setVal(val);
        this.row = row;
        this.col = col;
        this.val = val;
        setHorizontalAlignment(CENTER);

    }

    public void setVal(int inVal)
    {
        this.val = inVal;
        String str = Integer.toString(this.val);
        if( inVal < 1 || inVal > 9)
        {
            str=""; // empty
        }
        setText(str);
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public int getVal()
    {
        return val;
    }

}
