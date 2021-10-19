package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import main.java.game.board.Board;
import main.java.game.positions.Move;
import main.java.game.validation.ValidateMove;
import main.java.game.gameover.GameWon;
import main.java.game.movement.SingleMove;



public class RunGame {

    public static String error_msg = ("\n(Error) Enter a valid move in the format a4xb4\n(NOTE) Input must be in ranges (a-h) and (1-8)\n");

    public static int oldRow;
    public static int oldColum;
    public static int newRow;
    public static int newColum;

    public static int redcheckers = 12; // Number of red checkers on the board
    public static int whitecheckers = 12; // Number of black checkers on the board
    public static String whosemove = "red"; // Either "red" or "white", for who's move it currently is.
    public static boolean isKing = false;


    public static void main(String[] args) {


        // List of characters to check for the input
        String letters = "abcdefgh";
        String numbers = "12345678";


        // Map the inputs (letter part) to be easier to convert it
        Map<String, String> map_input = new HashMap<String, String>();

        map_input.put("a", "1");
        map_input.put("b", "2");
        map_input.put("c", "3");
        map_input.put("d", "4");
        map_input.put("e", "5");
        map_input.put("f", "6");
        map_input.put("g", "7");
        map_input.put("h", "8");


        // Create a new board with default positions
        String[][] newboard =  Board.getBoard();

        Scanner scan = new Scanner(System.in);


        boolean run = true;

        while(run){

            // Count the total number of remaining pieces on the board
            // and check if a player won
            int[] countPieces = GameWon.countPieces(newboard);

            if (GameWon.gameEnd(countPieces)) {
                break;
            }

            // if (GameWon.allBlocked(newboard)) {
            // 	break;
            // }


            // -------------------------------------------------------------------------


            // Display new board
            Board.displayBoard(newboard);


            // [TAKE USER INPUT (as STRING)]
            // -------------------------------------------------------------------------


            // Display the score
            System.out.println(String.format("\nRed pieces: %s | White pieces: %s\n", countPieces[0], countPieces[1]));


            // Prompt to enter position to "MOVE FROM"
            System.out.print(String.format("\nPlayer %s enter position pair (a3xb4): ", whosemove));

            // Get input for current position (as a string)
            String pos_scan = scan.nextLine();



            // Determine whose playing
            // -----------------------

            if (whosemove == "red"){


                String[] correct_format_inputs = Move.validateInputFormat(pos_scan, letters, numbers);

                if (correct_format_inputs[0] == "invalid"){
                    continue;
                }


                // -----------------------------------------------------------------------------------------
                // Map, convert and split the correct foramt input into its two integer digits (row, column)
                // -----------------------------------------------------------------------------------------

                int[] old_pos = Move.getInputCoords(0, correct_format_inputs, map_input);
                int[] new_pos = Move.getInputCoords(1, correct_format_inputs, map_input);

                oldRow = old_pos[0];
                oldColum = old_pos[1];
                newRow = new_pos[0];
                newColum = new_pos[1];


                // Return the validated inpute values (old and new)
                int[][] user_moves = {{oldRow, oldColum}, {newRow, newColum}};


                // Check if red player tries to move anything else than "R_P" or "R_K"
                if (!newboard[oldRow][oldColum].contains("R_P") && !newboard[oldRow][oldColum].contains("R_K")) {

                    System.out.println("\n(Error) You can only move R_P or R_K pieces\n");
                    whosemove = "red";
                    continue;
                }

                // If it contains the "R_K" string set the isKing = true to pass it in the validateMove() method
                // and move the pawn accordingly
                if (newboard[oldRow][oldColum].contains("R_K")) {

                    isKing = true;
                }


                // -------------
                // VALIDATE MOVE
                // -------------

                if (ValidateMove.validMove(user_moves[0], user_moves[1], newboard, whosemove)) {


                    // Signle move red pawn (down-right or down-left)
                    if (SingleMove.singleMove(user_moves[0], user_moves[1], newboard, isKing, whosemove)) {

                        String[][] updated_board = Move.makeMove(user_moves[0], user_moves[1], newboard, isKing, whosemove);

                        newboard = updated_board;

                    }
                    // else {
                    // 	continue;
                    // }


                    // Signle move red king
                    if (isKing && whosemove == "red") {

                        // Assign the nest moves(x,y) to the board and display the updated version
                        String[][] updated_board = Move.makeMove(user_moves[0], user_moves[1], newboard, isKing, whosemove);

                        newboard = updated_board;
                    }


                }
                else {
                    whosemove = "red";
                    System.out.println("\nThat was an invalid move, try again.\n");
                    continue;
                }
                // -------------


                // Clear terminal
                Board.clearTerminal();

            }


            // -----------------
            // White player turn
            // -----------------

            else {

                String[] correct_format_inputs = Move.validateInputFormat(pos_scan, letters, numbers);

                if (correct_format_inputs[0] == "invalid"){
                    continue;
                }


                // -----------------------------------------------------------------------------------------
                // Map, convert and split the correct foramt input into its two integer digits (row, column)
                // -----------------------------------------------------------------------------------------

                int[] old_pos = Move.getInputCoords(0, correct_format_inputs, map_input);
                int[] new_pos = Move.getInputCoords(1, correct_format_inputs, map_input);

                oldRow = old_pos[0];
                oldColum = old_pos[1];
                newRow = new_pos[0];
                newColum = new_pos[1];


                // Return the validated inpute values (old and new)
                int[][] user_moves = {{oldRow, oldColum}, {newRow, newColum}};


                // Check if red player tries to move anything else than "R_P" or "R_K"
                if (!newboard[oldRow][oldColum].contains("W_P") && !newboard[oldRow][oldColum].contains("W_K")) {

                    System.out.println("\n(Error) You can only move W_P or W_K pieces\n");
                    whosemove = "white";
                    continue;
                }

                // If it contains the "W_K" string set the isKing = true to pass it in the validateMove() method
                // and move the pawn accordinglyQ
                if (newboard[oldRow][oldColum].contains("W_K")) {

                    isKing = true;
                }


                // -------------
                // VALIDATE MOVE
                // -------------

                if (ValidateMove.validMove(user_moves[0], user_moves[1], newboard, whosemove)) {

                    // Signle move white pawn (down-right or down-left)
                    if (SingleMove.singleMove(user_moves[0], user_moves[1], newboard, isKing, whosemove)) {

                        String[][] updated_board = Move.makeMove(user_moves[0], user_moves[1], newboard, isKing, whosemove);

                        newboard = updated_board;
                    }

                    // else {
                    // 	continue;
                    // }


                    if (isKing && whosemove == "white") {

                        // Assign the next moves(x,y) to the board and display the updated version
                        String[][] updated_board = Move.makeMove(user_moves[0], user_moves[1], newboard, isKing, whosemove);

                        newboard = updated_board;
                    }

                }

                else {

                    System.out.println("\nThat was an invalid move, try again.\n");
                    whosemove = "white";
                    continue;

                }

                // -------------


                // Clear terminal
                Board.clearTerminal();
            }


            // Update whosemove it is.
            if (whosemove == "red") {
                whosemove = "white";
            }
            else {
                whosemove = "red";
            }


        }

    }
}


