package main.java.game.movement;

import java.util.*;

import main.java.game.board.Board;
import main.java.game.validation.ValidateMove;


public class SingleMove {


    public static boolean singleMove(int[] moveFrom, int[] moveTo, String[][] myboard, boolean isKing, String whosemove) {

        int oldRow = moveFrom[0];
        int oldColum = moveFrom[1];
        int newRow = moveTo[0];
        int newColum = moveTo[1];

        if (whosemove == "red"
                && (newRow - oldRow == 1)
                && (newColum - oldColum == 1 || newColum - oldColum == -1)
                && ValidateMove.getPiece(myboard, newRow, newColum).contains("   ")
                && ValidateMove.getPiece(myboard, oldRow, oldColum).contains("R_P")) {

            return true;
        }

        if (whosemove == "white"
                && (newRow - oldRow == -1)
                && (newColum - oldColum == 1 || newColum - oldColum == -1)
                && ValidateMove.getPiece(myboard, newRow, newColum).contains("   ")
                && ValidateMove.getPiece(myboard, oldRow, oldColum).contains("W_P")) {

            return true;
        }

        if (isKing == true && (

                ((newRow - oldRow == 1)
                        && (newColum - oldColum == 1 || newColum - oldColum == -1)
                        && ValidateMove.getPiece(myboard, newRow, newColum).contains("   ")
                        && ValidateMove.getPiece(myboard, oldRow, oldColum).contains("R_K"))

                        ||

                        ((newRow - oldRow == -1)
                                && (newColum - oldColum == 1 || newColum - oldColum == -1)
                                && ValidateMove.getPiece(myboard, newRow, newColum).contains("   ")
                                && ValidateMove.getPiece(myboard, oldRow, oldColum).contains("W_K")))) {

            return true;
        }

        return false;

    }

}
