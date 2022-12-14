package com.company.Games;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuSolver extends JFrame implements ActionListener {
    private static int BOARD_SIZE = 9;
    private JButton[][] button = new JButton[BOARD_SIZE][BOARD_SIZE];
    boolean crosTurn = true;
    SudokuSolver(int [][] sudoku) {
        JPanel pan = new JPanel();
        pan.setBorder(new LineBorder(Color.black, 20));
        super.setTitle("Tic-Tac-Toe");
        super.setSize(700, 700);
        GridLayout gameGrid = new GridLayout(BOARD_SIZE, BOARD_SIZE);
        super.setLayout(gameGrid);
        Font font = new Font("Serif", Font.CENTER_BASELINE, 40);
        for (int rowSize = 0; rowSize < BOARD_SIZE; rowSize++) {
            for (int colSize = 0; colSize < BOARD_SIZE; colSize++) {
                String add = "";
                if (sudoku[rowSize][colSize]!=0){
                    add = add+sudoku[rowSize][colSize];
                }
                JButton buttons = new JButton(add);
                button[rowSize][colSize] = buttons;
                buttons.setFont(font);
                super.add(buttons);
            }
        }
        super.setResizable(false);
        super.setVisible(true);
    }
    public  boolean isItSafeToPlace(int [][] sudoku,int row,int col,int num){
        // Checking the Row and Column
        for (int i = 0; i <=8 ; i++) {
            if (sudoku[row][i]==num){
                return false;
            }
        }
        for (int i = 0; i <=8 ; i++) {
            if (sudoku[i][col]==num){
                return false;
            }
        }
        // Checking the GRID
        int CR = (row/3)*3;
        int CC = (col/3)*3;
        for (int i = CR; i <CR+3 ; i++) {
            for (int j = CC; j <CC+3 ; j++) {
                if (sudoku[i][j]==num){
                    return false;
                }
            }
        }
        return true;
    }
     void print(int[][] sudoku)
    {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                System.out.print(sudoku[i][j] + " ");
            System.out.println();
        }
    }

    public  boolean sudokuSolver(int [][] sudoku,int row,int col) throws InterruptedException {
        // Base Case
        if (row==8 && col==9){
            return true;
        }
        if (col==9){
            row = row+1;
            col = 0;
        }
        if (sudoku[row][col]!=0){
            return sudokuSolver(sudoku,row,col+1);
        }
        for (int i = 1; i <=9 ; i++) {
            if (isItSafeToPlace(sudoku,row,col,i)){

                button[row][col].setText(i+"");
                Thread.sleep(40);
                sudoku[row][col] = i;
                if (sudokuSolver(sudoku,row,col+1)){
                    return true;
                }
            }
            sudoku[row][col] = 0;
        }
        return false;
    }
    public static void main(String[] args) throws InterruptedException {
        int [][] sudoku = { {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0} };

        SudokuSolver pt = new SudokuSolver(sudoku);
        if (pt.sudokuSolver(sudoku, 0, 0))
            pt.print(sudoku);
        else
            System.out.println("No Solution exists");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}