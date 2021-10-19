package main.java.game.validation;

import java.util.*;

import main.java.game.board.Board;


public class ValidateMove {

    public static String label;


    // Safe indexing
    public static String getPiece(String[][] board, int row, int colum){

        if (row > 8 || row < 1 || colum > 8 || colum < 1) {

            return "E";
        }

        return board[row][colum];
    }

    public static boolean  validMove(int[] moveFrom, int[] moveTo, String[][] myboard, String whosemove) {

        // if conditions here
        //

        int oldX = moveFrom[0];
        int oldY = moveFrom[1];
        int newX = moveTo[0];
        int newY = moveTo[1];


        // --------------------------------------
        // [1] Check if pawn and king are blocked
        // --------------------------------------
        //
        // ---------------
        // [Blocked_way_1]
        // ---------------
        //
        // NOTE: You need to add in the conditions in the if statements below
        //		 that the pawn position is
        boolean red_pawn_condition_1 = (getPiece(myboard, oldX + 1, oldY + 1).contains("R_P")
                || getPiece(myboard, oldX + 1, oldY - 1).contains("R_P")
                || getPiece(myboard, oldX + 1, oldY + 1).contains("R_K")
                || getPiece(myboard, oldX + 1, oldY - 1).contains("R_K"));

        boolean white_pawn_condition_1 = (getPiece(myboard, oldX - 1, oldY + 1).contains("W_P")
                || getPiece(myboard, oldX - 1, oldY - 1).contains("W_P")
                || getPiece(myboard, oldX - 1, oldY + 1).contains("W_K")
                || getPiece(myboard, oldX - 1, oldY - 1).contains("W_K"));

        boolean red_king_condition_1 = (getPiece(myboard, oldX + 1, oldY + 1).contains("R_P")
                || getPiece(myboard, oldX + 1, oldY - 1).contains("R_P")
                || getPiece(myboard, oldX + 1, oldY + 1).contains("R_K")
                || getPiece(myboard, oldX + 1, oldY - 1).contains("R_K")

                || getPiece(myboard, oldX - 1, oldY + 1).contains("R_P")
                || getPiece(myboard, oldX - 1, oldY - 1).contains("R_P")
                || getPiece(myboard, oldX - 1, oldY + 1).contains("R_K")
                || getPiece(myboard, oldX - 1, oldY - 1).contains("R_K"));

        boolean white_king_condition_1 = (getPiece(myboard, oldX + 1, oldY + 1).contains("W_P")
                || getPiece(myboard, oldX + 1, oldY - 1).contains("W_P")
                || getPiece(myboard, oldX + 1, oldY + 1).contains("W_K")
                || getPiece(myboard, oldX + 1, oldY - 1).contains("W_K")

                || getPiece(myboard, oldX - 1, oldY + 1).contains("W_P")
                || getPiece(myboard, oldX - 1, oldY - 1).contains("W_P")
                || getPiece(myboard, oldX - 1, oldY + 1).contains("W_K")
                || getPiece(myboard, oldX - 1, oldY - 1).contains("W_K"));


        // For the red and white pawns
        // ---------------------------

        if (whosemove == "red" && myboard[oldX][oldY].contains("R_P")  && red_pawn_condition_1) {

            System.out.println("\n(Error) Red pawn is blocked");
            return false;
        }

        if ( whosemove == "white" && myboard[oldX][oldY].contains("W_P") && white_pawn_condition_1) {

            System.out.println("\n(Error) White pawn is blocked");
            return false;
        }


        // For the red and white Kings
        // ---------------------------

        if (whosemove == "red" && myboard[oldX][oldY].contains("R_K") && red_king_condition_1) {

            System.out.println("\n(Error) Red king is blocked");
            return false;
        }

        if (whosemove == "white" && myboard[oldX][oldY].contains("W_K") && white_king_condition_1) {

            System.out.println("\n(Error) White king is blocked");
            return false;
        }

        // --------------
        // [Blocke_way_2] Blocked because we have piece -> enemy -> friend in a row
        // --------------
        //
        //
        //
        // ----------------------------------------------------------------


        // -------------------------------------------------------------------------------
        // [2] Ensure the pawn cannot move backwards (depending if it is "red" or "white")
        // -------------------------------------------------------------------------------

        // NOTE: The following conditions ensure that the pawns "R_P" and "R_K" cannot
        //		 move backwords to their respected direction. Therefore, we don't have
        //		 to create a condition for the king pieces as this does NOT affect them
        //		 and so can move backwards.

        if (whosemove == "red" && myboard[oldX][oldY].contains("R_P") && newX < oldX){

            System.out.println("\n(Error) Red pawn cannot move backwards");
            return false;
        }

        if (whosemove == "white" && myboard[oldX][oldY].contains("W_P") && newX > oldX){

            System.out.println("\n(Error) White pawn cannot move backwards");
            return false;
        }


        // -------------------------------------------------
        // [3] Check if player tries to capture friend piece
        // -------------------------------------------------

        if (whosemove == "red" && myboard[newX][newY].contains("R_P") || myboard[newX][newY].contains("R_K")) {

            System.out.println("\n(Error) Cannot capture friend piece");
            return false;
        }

        if (whosemove == "white" && myboard[newX][newY].contains("W_P") || myboard[newX][newY].contains("W_K")) {

            System.out.println("\n(Error) Cannot capture friend piece");
            return false;
        }



        // --------------------------------------------------------
        // [4] Check if player tries to move the same cell he is in
        // --------------------------------------------------------
        if (oldX == newX && oldY == newY){

            System.out.println("(Error) You can't move to the same position you are in");
            return false;
        }

        // ----------------------------------------------
        // [5] Ensure that pawns cannot move on the sides
        // ----------------------------------------------

        if (oldX == newX && Math.abs(oldY - newY) >= 1){

            System.out.println("\n(Error) Pawns cannot move right/left in a row");
            return false;
        }


        // -----------------------------------------------------
        // [6] Ensure that pawns cannot move up/down in a column
        // -----------------------------------------------------

        if (oldY == newY && Math.abs(oldX - newX) >= 1){

            System.out.println("\n(Error) Pawns cannot move up/down in a column");
            return false;
        }


        // ---------------------------------------------------------------------
        // [7] Single move (maybe you need to place it in the makeMove() method)
        // ---------------------------------------------------------------------

        double distance = ((double)Math.round(100 * Math.hypot((double)newX - (double)oldX, (double)newY-(double)newY)) / 100);


        if (distance == 2.0 && Math.abs(newY-oldY) == 1 && whosemove == "white") {

            if (newX > oldX){
                return false;
            }

            if (newY - oldY == 1) {
                return true;
            }
            else{
                System.out.println("\n(Error) White pawn can only move up one cell diagonally");
                return false;
            }
        }


        if (distance == 2.0 && Math.abs(newY-oldY) == 1 && whosemove == "red") {

            if (newX < oldX){
                return false;
            }

            if (newY - oldY == -1) {
                return true;
            }
            else{
                System.out.println("\n(Error) Red pawn can only move down one cell diagonally");
                return false;
            }
        }


        return true;

    }

}





