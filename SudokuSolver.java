import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* This class represents a program that takes an input file specifying a valid Sudoku
 * puzzle and displays the original board and one solution for the Sudoku puzzle if at
 * least one solution exists.
 *
 * @author Kelly Shaw, Raj Amarnani
 * @version 1.0
 * @since 04/04/2017
 *
 */
public class SudokuSolver {
    private int [][] myClue;
    private int [][] mySolution;
    /** Symbol used to indicate a blank grid position */
    public static final int BLANK = 0;
    /** Overall size of the grid */
    public static final int DIMENSION = 9;
    /** Size of a sub region */
    public static final int REGION_DIM = 3;

    // For debugging purposes -- see solve() skeleton.
    private Scanner kbd;
    private static final boolean DEBUG = false;
    
    /**
     * Run the solver. If args.length >= 1, use args[0] as the name of
     * a file containing a puzzle, otherwise, allow the user to browse
     * for a file.
     *
     */
    public static void main(String [] args){
        String filename = null;
        if (args.length < 1) {
            // file dialog
            //filename = args[0];
            JFileChooser fileChooser = new JFileChooser();
            try {
                File f = new File(new File(".").getCanonicalPath());
                fileChooser.setCurrentDirectory(f);
            } catch (Exception ex) { System.out.println(ex.getMessage()); }
                        
            int retValue = fileChooser.showOpenDialog(new JFrame());
            
            if (retValue == JFileChooser.APPROVE_OPTION) {
                File theFile = fileChooser.getSelectedFile();
                filename = theFile.getAbsolutePath();
            } else {
                System.out.println("No file selected: exiting.");
                System.exit(0);
            }
        } else {
            filename = args[0];
        }
        
        SudokuSolver s = new SudokuSolver(filename);
        if (DEBUG)
            s.print();
        
        if (s.solve(0, 0)){
	    // Pop up a window with the clue and the solution.
            s.display();
        } else {
            System.out.println("No solution is possible.");
            System.exit(0);
        }
        
    }
    
    /**
     * Create a solver given the name of a file containing a puzzle. We
     * expect the file to contain nine lines each containing nine digits
     * separated by whitespace. A digit from {1...9} represents a given
     * value in the clue, and the digit 0 indicates a position that is
     * blank in the initial puzzle.
     *
     * @param puzzleName The string of the filename containing the puzzle
     *
     */
    public SudokuSolver(String puzzleName){
        myClue = new int[DIMENSION][DIMENSION];
        mySolution = new int[DIMENSION][DIMENSION];
	// Set up keyboard input if we need it for debugging.
        if (DEBUG)
            kbd = new Scanner(System.in);
        
        File pf = new File(puzzleName);
        Scanner s = null;
        try {
            s = new Scanner(pf);
        } catch (FileNotFoundException f){
            System.out.println("Couldn't open file.");
            System.exit(1);
        }
        
        for (int i = 0; i < DIMENSION; i++){
            for (int j = 0; j < DIMENSION; j++){
                myClue[i][j] = s.nextInt();
            }
        }
        
        // Copy to solution
        for (int i = 0; i < DIMENSION; i++){
            for (int j = 0; j < DIMENSION; j++){
                mySolution[i][j] = myClue[i][j];
            }
        }
    }
    
    /**
     * Starting at a given grid position, generate values for all remaining
     * grid positions that do not violate the game constraints.
     *
     * @param row The row of the position to begin with
     * @param col The column of the position to begin with.
     *
     * @return true if a solution was found starting from this position,
     *          false if not.
     */
    public boolean solve(int row, int col){
	// This code will print the solution array and then wait for 
	// you to type "Enter" before proceeding. Helpful for debugging.
	// Set the DEBUG constant to true at the top of the class
	// declaration to turn this on.
        if (DEBUG) {
            System.out.println("solve(" + row + ", " + col + ")");
            print();
            kbd.nextLine();
        }

        //If we are positioned after the last column, reset column to zero and increment
        // row. If we are also at the last row, then we have solved the puzzle
        if(col == DIMENSION){

            if(row == DIMENSION-1){
                return true;
            }

            col = 0;
            row++;
        }

        //If the current clue is already filled, move to the next cell
        if(myClue[row][col] != 0){
            return solve(row,col+1);
        }

        for(int i = 1; i <= DIMENSION; ++i){

            //If the number is safe, fill the number in and recursively call solve on 
            // the next cell
            if(numberIsSafe(i,row,col)){
                mySolution[row][col] = i;

                //If solve returns false, set the entry equal to zero and continue looping through potential
                // solutions for the cell. If successful return true
                if(!solve(row,col+1)){
                    mySolution[row][col] = 0;
                }
                else{
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * This method determines if the int num parameter passed to it is safe to
     * place in the position determined by the parameters row and col. This 
     * method returns true if the number is legal in the specified position
     * and false otherwise. 
     *
     * @param num The number we check to see is safe in the current position
     * @param row The row of the position we are currently in
     * @param col The column of the position we are currently in
     *
     * @return true if the number specified is safe in the position determined by 
     *          parameters row and col, false otherwise. 
     */
    private boolean numberIsSafe(int num, int row, int col){
        
        //determines the endpoints for the row and column search in a region given position row,col. 
        int maxRowSearch = ((row/REGION_DIM)*REGION_DIM)+(REGION_DIM-1);
        int maxColSearch = ((col/REGION_DIM)*REGION_DIM)+(REGION_DIM-1);

        //if number is present in the current region, return false
        for(int rowIndex = (row/REGION_DIM)*REGION_DIM; rowIndex <= maxRowSearch; ++rowIndex){

            for(int colIndex = (col/REGION_DIM)*REGION_DIM; colIndex <= maxColSearch; ++colIndex){

                if(mySolution[rowIndex][colIndex] == num){
                    return false;
                }

            }

        }

        //if number is present in the current row or column, return false 
        for(int i = 0; i < DIMENSION; ++i){

            if(mySolution[row][i] == num){
                return false;
            }

            if(mySolution[i][col] == num){
                return false;
            }
        }

        return true;
    }
    
    /**
     * Print a character-based representation of the solution array
     * on standard output.
     */
    public void print(){
        System.out.println("+---------+---------+---------+");
        for (int i = 0; i < DIMENSION; i++){
            System.out.println("|         |         |         |");
            System.out.print("|");
            for (int j = 0; j < DIMENSION; j++){
                System.out.print(" " + mySolution[i][j] + " ");
                if (j % REGION_DIM == (REGION_DIM - 1)){
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i % REGION_DIM == (REGION_DIM - 1)){
                System.out.println("|         |         |         |");
                System.out.println("+---------+---------+---------+");
            }
        }
    }
    
    /**
     * Pop up a window containing a nice representation of the original
     * puzzle and out solution.
     */
    public void display(){
        JFrame f = new DisplayFrame();
        f.pack();
        f.setVisible(true);
    }
    
    /**
     * GUI display for the clue and solution arrays.
     */
    private class DisplayFrame extends JFrame implements ActionListener {
        private JPanel mainPanel;
        
        private DisplayFrame(){
            mainPanel = new JPanel();
            mainPanel.add(buildBoardPanel(myClue, "Clue"));
            mainPanel.add(buildBoardPanel(mySolution, "Solution"));
            add(mainPanel, BorderLayout.CENTER);
            
            JButton b = new JButton("Quit");
            b.addActionListener(this);
            add(b, BorderLayout.SOUTH);
        }
        
        private JPanel buildBoardPanel(int [][] contents, String label){
            JPanel holder = new JPanel();
            JLabel l = new JLabel(label);
            BorderLayout b = new BorderLayout();
            holder.setLayout(b);
            holder.add(l, BorderLayout.NORTH);
            JPanel board = new JPanel();
            GridLayout g = new GridLayout(9,9);
            g.setHgap(0);
            g.setVgap(0);
            board.setLayout(g);
            Color [] colorChoices = new Color[2];
            colorChoices[0] = Color.WHITE;
            colorChoices[1] = Color.lightGray;
            int colorIdx = 0;
            int rowStartColorIdx = 0;
            
            for (int i = 0; i < DIMENSION; i++){
                if (i > 0 && i % REGION_DIM == 0)
                    rowStartColorIdx = (rowStartColorIdx+1)%2;
                colorIdx = rowStartColorIdx;
                for (int j = 0; j < DIMENSION; j++){
                    if (j > 0 && j % REGION_DIM == 0)
                        colorIdx = (colorIdx+1)%2;
                    JTextField t = new JTextField(""+ contents[i][j]);
                    if (contents[i][j] == 0)
                        t.setText("");
                    t.setPreferredSize(new Dimension(35,35));
                    t.setEditable(false);
                    t.setHorizontalAlignment(JTextField.CENTER);
                    t.setBackground(colorChoices[colorIdx]);
                    board.add(t);
                }
            }
            holder.add(board, BorderLayout.CENTER);
            return holder;
        }
        
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }
}
