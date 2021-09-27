package SudokuView;

import SudokuCommon.SudokuBoard;
import SudokuCommon.SudokuViewInterface;

import javax.swing.*;
import java.awt.*;

public class SudokuGUIView implements SudokuViewInterface{

    private static final int N = 9;
    private JFrame frame;
    private JPanel[][] theBlocks;
    public SudokuCell[][] viewBoard;
    private JPanel nPanel, cPanel, sPanel;
    public JButton solveB, clearB;
    public JLabel msgLabel;


    public SudokuGUIView() {
        this.frame = new JFrame("Sudoku Solver");
        theBlocks = new JPanel[N /3][N /3];
        viewBoard = new SudokuCell[N][N];
        makeNorthpanel();
        makeCenterPanel();
        makeSouthPanel();
    }

    private void makeNorthpanel() {
        nPanel = new JPanel();

        msgLabel = new JLabel();
        msgLabel.setHorizontalAlignment(JLabel.CENTER);
        nPanel.add(msgLabel, BorderLayout.CENTER);

        frame.add(nPanel, BorderLayout.NORTH);
    }

    private void makeCenterPanel()
    {
        cPanel = new JPanel(new GridLayout(N /3, N /3));
        makeBlocksAndSquares();

        frame.add(cPanel, BorderLayout.CENTER);
    }

    private void makeBlocksAndSquares()
    {
        //blocks.
        for (int r = 0; r < N /3; r=r+1)
        {
            for (int c = 0; c < N /3; c=c+1)
            {
                theBlocks[r][c] = new JPanel(new GridLayout(3, 3));
                theBlocks[r][c].setBorder(BorderFactory.createLineBorder(Color.lightGray,3));
                cPanel.add(theBlocks[r][c]);
            }
        }

        //squares.
        for (int r = 0; r< N; r++) {
            for(int c = 0; c< N; c++) {
                viewBoard[r][c] = new SudokuCell( r, c, 0);
                //viewBoard[r][c] = new SudokuSquare(r, c, modelInterface.getBoardVal(r,c)); //"PULL-MODEL"
                theBlocks[r/3][c/3].add(viewBoard[r][c]);
            }
        }
    }

    private void makeSouthPanel() {
        sPanel = new JPanel();

        clearB = new JButton("Clear");
        sPanel.add(clearB);

        solveB = new JButton("Solve");
        sPanel.add(solveB);


        frame.add(sPanel, BorderLayout.SOUTH);
    }

    public void clearViewBoard() {
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                viewBoard[r][c].setVal(0);
                viewBoard[r][c].setEditable(true);
            }
        }
    }

    public void setViewBoard(SudokuBoard solution) {
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                viewBoard[r][c].setVal(solution.board[r][c]);
                viewBoard[r][c].setEditable(false);
            }
        }

    }


    @Override
    public void paint() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

}
