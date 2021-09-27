package DLX;

import SudokuModel.ConstraintMatrix;
import SudokuModel.SudokuConstraint;

import java.util.ArrayList;

public class DLXImpl implements DLX{
    private int N, SIZE;
    private ColumnHead head;
    private ArrayList ans;

    public DLXImpl(ConstraintMatrix m) {
        this.SIZE = m.SIZE;
        this.N = SIZE * SIZE;
        this.head = new ColumnHead();
        this.ans = new ArrayList();
        ColumnHead curColumn = head;
        for(int col = 0; col < m.matrix[0].length; col++)
        {
            curColumn.right = new ColumnHead();
            curColumn.right.left = curColumn;
            curColumn = (ColumnHead)curColumn.right;
            curColumn.columnHead = curColumn;

            if(col < 3*N*N)
            {
                int digit = (col / (3*N)) + 1;
                curColumn.number = digit;
                int index = col-(digit-1)*3*N;
                if(index < N)
                {
                    curColumn.constraint = SudokuConstraint.ROW;
                    curColumn.position = index;
                } else if(index < 2*N)
                {
                    curColumn.constraint = SudokuConstraint.COL;
                    curColumn.position = index-SudokuConstraint.COL.getValue()*N;
                } else
                {
                    curColumn.constraint = SudokuConstraint.SUB;
                    curColumn.position = index-SudokuConstraint.SUB.getValue()*N;
                }
            } else
            {
                curColumn.constraint = SudokuConstraint.SLOT;
                curColumn.position = col - SudokuConstraint.SLOT.getValue()*N*N;
            }

        }
        curColumn.right = head;
        head.left = curColumn;


        for(int row = 0; row < m.matrix.length; row++)
        {
            curColumn = (ColumnHead) head.right;
            Node lastCreatedElement = null;
            Node firstElement = null;
            for(int col = 0; col < m.matrix[row].length; col++) {
                if(m.matrix[row][col] == 1)
                {
                    Node colElement = curColumn;
                    while(colElement.down != null)
                    {
                        colElement = colElement.down;
                    }
                    colElement.down = new Node();
                    if(firstElement == null) {
                        firstElement = colElement.down;
                    }
                    colElement.down.up = colElement;
                    colElement.down.left = lastCreatedElement;
                    colElement.down.columnHead = curColumn;
                    if(lastCreatedElement != null)
                    {
                        colElement.down.left.right = colElement.down;
                    }
                    lastCreatedElement = colElement.down;
                    curColumn.size++;
                }
                curColumn = (ColumnHead)curColumn.right;
            }
            if(lastCreatedElement != null)
            {
                lastCreatedElement.right = firstElement;
                firstElement.left = lastCreatedElement;
            }
        }
        curColumn = (ColumnHead) head.right;
        for(int i = 0; i < m.matrix[0].length; i++)
        {
            Node colElement = curColumn;
            while(colElement.down != null)
            {
                colElement = colElement.down;
            }
            colElement.down = curColumn;
            curColumn.up = colElement;
            curColumn = (ColumnHead)curColumn.right;
        }

    }

    @Override
    public ArrayList solve()
    {
        if (!algorithmX(0)) {
            return null;
        }
        return ans;

    }


    private boolean algorithmX(int k)
    {
        if(head.right == head)
        {
            return true;
        }
        ColumnHead c = smallestColumn();
        removeColumn(c);

        for (Node r = c.down; r != c; r = r.down)
        {
            if(k < ans.size())
            {
                ans.remove(k);
            }
            ans.add(k,r);

            for (Node j = r.right; j!=r; j = j.right) {
                removeColumn(j.columnHead);
            }

            if (algorithmX(k+1)) {
                return true;
            }

            Node r2 = (Node) ans.get(k);
            for (Node j2 = r2.left; j2 != r2; j2 = j2.left) {
                restoreColumn(j2.columnHead);
            }
        }

        restoreColumn(c);
        return false;
    }

    private ColumnHead smallestColumn() {
        ColumnHead small = null;
        for (ColumnHead curr = (ColumnHead) head.right; curr.right != head; curr = (ColumnHead) curr.right) {
            small = curr;
            if (curr.size < small.size)
            {
                small = curr;
            }
        }
        return small;
    }


    private void removeColumn(Node column) {
        column.right.left = column.left;
        column.left.right = column.right;

        for(Node curRow = column.down; curRow != column; curRow = curRow.down) {
            for (Node curNode = curRow.right; curNode != curRow; curNode = curNode.right) {
                curNode.down.up = curNode.up;
                curNode.up.down = curNode.down;
                curNode.columnHead.size--;
            }
        }
    }

    private void restoreColumn(Node column) {
        for (Node curRow = column.up; curRow != column; curRow = curRow.up) {
            for (Node curNode = curRow.left; curNode != curRow; curNode = curNode.left) {
                curNode.columnHead.size++;
                curNode.down.up = curNode;
                curNode.up.down = curNode;
            }
        }
        column.right.left = column;
        column.left.right = column;
    }

}
