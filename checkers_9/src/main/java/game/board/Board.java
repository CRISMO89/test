package main.java.game.board;

public class Board {


    public static String[][] getBoard() {

        String[][] board = {

                // {"          a", "     b", "     c" , "     d" , "     e" , "     f" , "     g", "      h"},
                {"  +-------------------------------------------------+ "},
                {"8 | ", "[   ] ", "[R_P] ", "[   ] " , "[R_P] " , "[   ] " , "[R_P] " , "[   ] " , "[R_P] " , "| 8"},
                {"7 | ", "[R_P] ", "[   ] ", "[R_P] " , "[   ] " , "[R_P] " , "[   ] " , "[R_P] " , "[   ] " , "| 7"},
                {"6 | ", "[   ] ", "[R_P] ", "[   ] " , "[R_P] " , "[   ] " , "[R_P] " , "[   ] " , "[R_P] " , "| 6"},
                {"5 | ", "[   ] ", "[   ] ", "[   ] " , "[   ] " , "[   ] " , "[   ] " , "[   ] " , "[   ] " , "| 5"},
                {"4 | ", "[   ] ", "[   ] ", "[   ] " , "[   ] " , "[   ] " , "[   ] " , "[   ] " , "[   ] " , "| 4"},
                {"3 | ", "[W_P] ", "[   ] ", "[W_P] " , "[   ] " , "[W_P] " , "[   ] " , "[W_P] " , "[   ] " , "| 3"},
                {"2 | ", "[   ] ", "[W_P] ", "[   ] " , "[W_P] " , "[   ] " , "[W_P] " , "[   ] " , "[W_P] " , "| 2"},
                {"1 | ", "[W_P] ", "[   ] ", "[W_P] " , "[   ] " , "[W_P] " , "[   ] " , "[W_P] " , "[   ] " , "| 1"},
                {"  +-------------------------------------------------+ "}
                // {"          a", "     b", "     c" , "     d" , "     e" , "     f" , "     g", "      h"}
        };

        return board;
    }


    // Clear the terminal before printing the board
    // --------------------------------------------
    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }

    // Method to display a board of this type
    public static void displayBoard(String[][] myboard) {


        // Print the board
        // ---------------
        System.out.println("      a     b     c     d     e     f     g     h");
        System.out.println(myboard[0][0]);

        for (int i=1; i<9; i++){


            for (int j=0; j<10; j++){

                System.out.print(myboard[i][j]);
            }

            System.out.println("");
        }

        System.out.println(myboard[0][0]);
        System.out.println("      a     b     c     d     e     f     g     h");
        // ---------------

    }

}
