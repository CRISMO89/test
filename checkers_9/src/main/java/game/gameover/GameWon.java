package main.java.game.gameover;

import java.util.*;

public class GameWon {

    public static int[] countPieces(String[][] myboard) {

        int redpieces = 0;
        int whitepieces = 0;

        for (int i=1; i<9; i++){

            for (int j=0; j<10; j++){

                if (myboard[i][j].contains("R_P") || myboard[i][j].contains("R_K")) {

                    redpieces++;
                }

                else if (myboard[i][j].contains("W_P") || myboard[i][j].contains("W_K")) {

                    whitepieces++;
                }
            }
        }

        // Return the a 2d array with the players' pieces
        int[] piecescount = {redpieces, whitepieces};

        return piecescount;
    }


    public static boolean gameEnd(int[] countPieces) {

        if (countPieces[0] == 0) {

            System.out.println("\n-----------------");
            System.out.println("White player wins");
            System.out.println("-----------------\n");
            return true;
        }

        if (countPieces[1] == 0) {

            System.out.println("\n-------------------");
            System.out.println("| Red player wins |");
            System.out.println("-------------------\n");
            return true;
        }

        return false;

    }

    // ----------------- Not Yet ---------------------------

    // Method for Safe indexing (check if we go out of bounds)
    public static String getPiece(String[][] board, int row, int colum){

        if (row > 8 || row < 1 || colum > 8 || colum < 1) {

            return "E";
        }

        return board[row][colum];
    }


    public static boolean pieceBlocked(String[][] myboard) {

        // if red piece is blocked return true;
        // return "red";

        // if white piece is blocked return true;
        // return "white";

        return true;
    }


    public static boolean allBlocked(String[][] myboard) {

        int red_total_pieces = 0;
        int white_total_pieces = 0;
        int redblocked = 0;
        int whiteblocked = 0;

        for (int row=1; row<8; row++){


            for (int colum=1; colum<8; colum++){

                // [  ] check if piece have friend pieces at all its four corners
                //
                //		1. if piece is at any corner of the board check ONLY at
                //		   its (1) free corner
                //
                //		2. if piece is at any side of the board check ONLY at
                //		   its (2) free corners
                //
                //		3. if its anywhere else check all its (4) corners
                //
                //	NOTE: if redblocked == 12 --> red player loses the game
                //

                String redLabels = "R_P R_K";
                String whiteLabels = "W_P W_K";

                // [1] if our current position has a red piece label
                boolean isRed = redLabels.contains(myboard[row][colum]);

                // [2] if our current position has a white piece label
                boolean isWhite = whiteLabels.contains(myboard[row][colum]);


                // USE THESE DIRECTIONS FOR YOUR IF STATEMENTS FOR ANY SCENARIO
                //
                // ----------------------------------
                // [RED and WHITE pieces conditions]
                // ----------------------------------

                // [1] if there is red piece (or white for other block) at the corner (down-right) of our current piece position
                boolean isRedDownRight = redLabels.contains(getPiece(myboard, row + 1, colum + 1));
                boolean isWhiteDownRight = whiteLabels.contains(getPiece(myboard, row + 1, colum + 1));


                // [2] if there is red piece (or white for other block) at the corner (down-left) of our current piece position
                boolean isRedDownLeft = redLabels.contains(getPiece(myboard, row + 1, colum - 1));
                boolean isWhiteDownLeft = whiteLabels.contains(getPiece(myboard, row + 1, colum - 1));


                // [3] if there is white piece (or red for other block) at the corner (up-right) of our current piece position
                boolean isWhiteUpRight = whiteLabels.contains(getPiece(myboard, row - 1, colum + 1));
                boolean isRedUpRight = redLabels.contains(getPiece(myboard, row - 1, colum + 1));


                // [4] if there is white piece (or red for other block) at the corner (up-left) of our current piece position
                boolean isWhiteUpLeft = whiteLabels.contains(getPiece(myboard, row - 1, colum - 1));
                boolean isRedUpLeft = redLabels.contains(getPiece(myboard, row - 1, colum - 1));



                // [5] if there is red piece after a white piece at the diagonal (down-right) of our current piece position
                boolean isWhiteRedDownRight = isWhiteDownRight && redLabels.contains(getPiece(myboard, row + 2, colum + 2));


                // [6] if there is red piece after a white piece at the diagonal (down-left) of our current piece position
                boolean isWhiteRedDownLeft = isWhiteDownLeft && redLabels.contains(getPiece(myboard, row + 2, colum - 2));


                // [7] if there is red piece after a white piece at the diagonal (up-right) of our current piece position
                boolean isWhiteRedUpRight = isWhiteUpRight  && redLabels.contains(getPiece(myboard, row - 2, colum + 2));


                // [8] if there is red piece after a white piece at the diagonal (up-left) of our current piece position
                boolean isWhiteRedUpLeft = isWhiteUpLeft && redLabels.contains(getPiece(myboard, row - 2, colum - 2));




                // [9] if there is white piece after a red piece at the diagonal (up-right) of our current piece position
                boolean isRedWhiteUpRight = isRedUpRight && whiteLabels.contains(getPiece(myboard, row - 2, colum + 2));


                // [10] if there is white piece after a red piece at the diagonal (up-left) of our current piece position
                boolean isRedWhiteUpLeft = isRedUpLeft && whiteLabels.contains(getPiece(myboard, row - 2, colum - 2));


                // [11] if there are two white pieces (back-to-back) after a red piece at the diagonal (down-right) of our current piece position
                boolean isRedWhiteDownRight = isRedDownRight && whiteLabels.contains(getPiece(myboard, row + 2, colum + 2));


                // [12] if there is white piece after a red piece at the diagonal (down-left) of our current piece position
                boolean isRedWhiteDownLeft = isRedDownLeft && whiteLabels.contains(getPiece(myboard, row + 2, colum - 2));



                // ------------------------------------------
                //				CHECK CORNERS
                // ------------------------------------------
                // --------------
                // [Corner (1,1)]
                // --------------

                if ((row == 1 && colum == 1)) {

                    // if its a red piece and either there is friend at that corner
                    // or there 2 friend pieces after an enemy white

                    if ((isRed && isRedDownRight) || (isRed && isWhiteDownRight && isWhiteRedDownRight)) {

                        redblocked++;
                    }

                    // if its a white piece and either there is friend at that corner
                    // or there 2 friend pieces after an enemy red

                    if ((isWhite && isWhiteDownRight) || (isWhite && isRedDownRight && isRedWhiteDownRight)) {

                        whiteblocked++;

                    }
                }

                // --------------
                // [Corner (1,8)]
                // --------------

                if ((row == 1 && colum == 8)) {

                    // if its a red piece and either there is friend at that corner
                    // or there 2 friend pieces after an enemy white

                    if ((isRed && isRedDownLeft) && (isRed && isWhiteDownLeft && isWhiteRedDownLeft)) {

                        redblocked++;
                    }

                    // if its a white piece and either there is friend at that corner
                    // or there 2 friend pieces after an enemy red

                    if ((isWhite && isWhiteDownLeft) && (isWhite && isRedDownLeft && isRedWhiteDownLeft)) {

                        whiteblocked++;
                    }
                }

                // --------------
                // [Corner (8,1)]
                // --------------

                if ((row == 8 && colum == 1)) {

                    // if its a red piece and either there is friend at that corner
                    // or there 2 friend pieces after an enemy white

                    if ((isRed && isRedUpRight) && (isRed && isWhiteUpRight && isWhiteRedUpRight)) {

                        redblocked++;
                    }

                    // if its a white piece and either there is friend at that corner
                    // or there 2 friend pieces after an enemy red

                    if ((isWhite && isWhiteUpRight) && (isWhite && isRedUpRight && isRedWhiteUpRight)) {

                        whiteblocked++;
                    }
                }

                // --------------
                // [Corner (8,8)]
                // --------------

                if ((row == 8 && colum == 8)) {

                    // if its a red piece and either there is friend at that corner
                    // or there 2 friend pieces after an enemy white

                    if ((isRed && isRedUpLeft) && (isRed && isWhiteUpLeft && isWhiteRedUpLeft)) {

                        redblocked++;
                    }

                    // if its a white piece and either there is friend at that corner
                    // or there 2 friend pieces after an enemy red

                    if ((isWhite && isWhiteUpLeft) && (isWhite && isRedUpLeft && isRedWhiteUpLeft)) {

                        whiteblocked++;
                    }
                }


                // ------------------------------------------


                // ------------------------------------------
                //				CHECK SIDES
                // ------------------------------------------
                // ------------
                // [Side (top)]
                // ------------

                // if ((row == 1 && (colum > 1 && colum < 8 ))) {}

                // if its a red piece and either there is friend at that corner
                // or there 2 friend pieces after an enemy white


                boolean red_blocked_conditions =	(isRed && isRedDownRight)
                        || (isRed && isRedDownLeft)

                        || (isRed && isRedUpRight)
                        || (isRed && isRedUpLeft)

                        || (isRed && isWhiteDownRight && isWhiteRedDownRight)
                        || (isRed && isWhiteDownLeft && isWhiteRedDownLeft)

                        || (isRed && isWhiteUpRight && isWhiteRedUpRight)
                        || (isRed && isWhiteUpLeft && isWhiteRedUpLeft);


                boolean white_blocked_conditions =	   (isWhite && isWhiteDownRight)
                        || (isWhite && isWhiteDownLeft)

                        || (isWhite && isWhiteUpRight)
                        || (isWhite && isWhiteUpLeft)

                        || (isWhite && isRedDownRight && isRedWhiteDownRight)
                        || (isWhite && isRedDownLeft && isRedWhiteDownLeft)

                        || (isWhite && isRedUpRight && isRedWhiteUpRight)
                        || (isWhite && isRedUpLeft && isRedWhiteUpLeft);


                if (redLabels.contains(getPiece(myboard, row, colum)))
                    red_total_pieces++;

                if (whiteLabels.contains(getPiece(myboard, row, colum)))
                    white_total_pieces++;


                if (red_blocked_conditions) {

                    redblocked++;
                }

                if (white_blocked_conditions) {

                    whiteblocked++;
                }


                // ------------------------------------------

            }

        }

        if (red_total_pieces == redblocked) {

            System.out.println("\n-----------------");
            System.out.println("White player wins");
            System.out.println("-----------------\n");
            return true;
        }

        if (white_total_pieces == whiteblocked) {

            System.out.println("\n-------------------");
            System.out.println("| Red player wins |");
            System.out.println("-------------------\n");
            return true;
        }

        return false;

    }

}
