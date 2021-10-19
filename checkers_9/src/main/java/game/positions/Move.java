package main.java.game.positions;

import java.util.*;

import main.java.game.board.Board;
import main.java.game.movement.SingleMove;
import main.java.game.validation.ValidateMove;


public class Move {


    public static String label;


    public static String[] validateInputFormat(String pos_scan, String letters, String numbers) {

        /* Accepts the input the user entered and checks if its in a valid format
         * - If it is, returns the current and future move in a String[] array
         *   to be passed in the getInputCoords() method and to convert them in actual
         *   (x,y) coordinates as seen on the board.
         * - If it is not, it returns a new String[] array with one element: the string "invalid"
         *   which we use to continue with the next iteration of the main while loop and get
         *   a new valid input.
         */

        String error_msg = ("\n(Error) Enter a valid move in the format a4xb4\n(NOTE) Input must be in ranges (a-h) and (1-8)\n");



        // Check if input contains "x" and exists string before and after it (All senarios)
        boolean length_condition_1 = pos_scan.contains("x");
        int length_condition_2 = pos_scan.split("x").length;
        boolean length_condition_3 = String.valueOf(pos_scan.charAt(0)) == "x";
        boolean length_condition_4 = (pos_scan.charAt(0) == 'x' && pos_scan.length() >= 1);


        if (length_condition_1 == false || length_condition_2 <= 1 || length_condition_3 || length_condition_4) {

            System.out.println(error_msg);

            String[] arr = {"invalid"};

            return arr;
        }

        // Split the input into a char array with 2 elements of 2 characters each
        String[] moves = pos_scan.split("x");


        // Check after we checked the input to be in format a3xb4
        // Now, we just need to check if the two characters of each input position is in format "a3" and not "3a"
        boolean type_conditionFrom_1 = letters.contains(moves[0].substring(0,1)) && numbers.contains(moves[0].substring(1));

        boolean type_conditionTo_1 = letters.contains(moves[1].substring(0,1)) && numbers.contains(moves[1].substring(1));


        if (!(type_conditionFrom_1) || !(type_conditionTo_1)) {

            System.out.println(error_msg);

            String[] arr = {"invalid"};

            return arr;
        }

        return moves;
    }



    public static String[][] makeMove(int[] MoveFrom, int[] MoveTo, String[][] myboard, boolean isKing, String whosemove) {

        /*
         * Assigns the new (x,y) postition to the board and returns the updated board:
         */

        int oldRow = MoveFrom[0];
        int oldColum = MoveFrom[1];
        int newRow = MoveTo[0];
        int newColum = MoveTo[1];

        // If either the piece is already a King ("R_K" or "W_K") OR the next possible move is
        // at newX = 1 (for "red" pawn) or newX = 8 (for "white") put its respected string label
        // to the new cell and delete the label from the previous cell

        if (isKing == true || (newRow == 8 && whosemove == "red") || (newRow == 1 && whosemove == "white")){

            // Modify and add the code from below HERE so that the
            // the label it will put in the new cell of the board
            // will be either "R_K" or "W_K" depending on the
            // current cell's label

            if (whosemove == "red") {

                label = "[R_K] ";
            }

            if (whosemove == "white") {

                label = "[W_K] ";
            }

            myboard[newRow][newColum] = label;

            myboard[oldRow][oldColum] = "[   ] ";

            return myboard;
        }

        // ---------------------------------------------------------

        String label = myboard[oldRow][oldColum];

        myboard[newRow][newColum] = label;

        myboard[oldRow][oldColum] = "[   ] ";

        return myboard;


        // ---------------------------------------------------------

    }

    // ---------------------------------------------------------


    public static int[] getInputCoords(int index, String[] array, Map<String, String> map) {

        /*
         *	Map the input and convert it and split it into its two integer digits (row, column)
         *
         */

        // Convert "a3" -> "13"
        String pos_str = array[index].replace(array[index].substring(0,1), map.get((array[index].substring(0,1))));

        // Convert "13" into 13 (integer)
        int pos_int = Integer.parseInt(pos_str);

        // Split integer 13 into its 2 digits and store them in an array
        // Suctract x value from 9 to find the correct coordinated on the board
        // (This just to move accordingly when seeing this board)
        int[] pos = {9 - (pos_int % 10), pos_int / 10};

        return pos;
    }

}
