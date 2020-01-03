import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);
        char[][] field = new char[3][3];

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++)
                field[i][j] = ' ';
        }

        displayField(field);
        int loc1, loc2, flag = 0, blankCount = 9;
        while(true) {
            System.out.println("Enter the co-ordinates:");
            try {
                sc = new Scanner(System.in);
                loc1 = sc.nextInt();
                loc2 = sc.nextInt();
            }

            catch(InputMismatchException ex) {
                System.out.println("You should enter numbers!");
                continue;
            }
            if(loc1 < 1 || loc1 > 3 || loc2 < 1 || loc2 > 3)
                System.out.println("Coordinates should be from 1 to 3!");

            else {
                boolean xWon, oWon;
                if(field[3 - loc2][loc1 - 1] == 'X' || field[3 - loc2][loc1 - 1] == 'O') {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }

                if (flag == 0) {
                    field[3 - loc2][loc1 - 1] = 'X';
                    xWon = hasWon(field, 'X', false, true) || hasWon(field, 'X', true, false) ||
                            hasWon(field, 'X', false, false);
                    displayField(field);
                    if(xWon) {
                        System.out.println("X wins");
                        break;
                    }
                    flag = 1;
                }

                else {
                    field[3 - loc2][loc1 - 1] = 'O';
                    oWon = hasWon(field, 'O', false, true) || hasWon(field, 'O', true, false) ||
                            hasWon(field, 'O', false, false);
                    displayField(field);
                    if(oWon) {
                        System.out.println("O wins");
                        break;
                    }
                    flag = 0;
                }

                blankCount--;
                if(blankCount == 0) {
                    displayField(field);
                    System.out.println("Draw");
                    break;
                }
            }
        }

        /* boolean xWon = hasWon(field, 'X', false, true) || hasWon(field, 'X', true, false) ||
                 hasWon(field, 'X', false, false),

                 oWon = hasWon(field, 'O', false, true) || hasWon(field, 'O', true, false) ||
                         hasWon(field, 'O', false, false);

         if(xWon && oWon)
             System.out.println("Impossible");

         else if(xWon)
             System.out.println("X wins");

         else if(oWon)
             System.out.println("O wins");

         else {
             // Game is either not finished, drawn or the state is invalid (more X's or more O's)
             boolean blankFound = false;

             int xCount = 0, oCount = 0;
             for(int i = 0; i < 3; i++) {
                 for(int j = 0; j < 3; j++) {
                     if(field[i][j] == 'X') xCount++;
                     else if(field[i][j] == 'O') oCount++;
                     else blankFound = true;
                 }
             }

             if(blankFound && Math.abs(xCount - oCount) >= 2)
                 System.out.println("Impossible");

             else if(Math.abs(xCount - oCount) >= 2)
                 System.out.println("Impossible");

             else if(blankFound)
                 System.out.println("Game not finished");

             else
                 System.out.println("Draw");
         }*/
    }

    private static void displayField(char[][] field) {
        System.out.println("---------");
        for(int i = 0; i < 3; i++) {
            System.out.print("| ");
            for(int j = 0; j < 3; j++)
                System.out.print(field[i][j] + " ");
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static boolean hasWon(char[][] input, char ch, boolean rowCol, boolean diag) {
        if(diag) {
            if(input[1][1] != ch)
                return false;

            boolean oneDiag = true;
            for(int i = 0; i < 3; i++) {
                if(input[i][i] != ch) {
                    oneDiag = false;
                    break;
                }
            }
            if(!oneDiag) {
                for(int i = 0 ; i < 3; i++) {
                    if(input[i][2-i] != ch)
                        return false;
                }
                return true;
            }
            return true;
        }

        else if(rowCol) {
            for(int i = 0; i < 3; i++) {
                if(input[i][0] == ch && input[i][1] == ch && input[i][2] == ch)
                    return true;
            }
            return false;
        }

        else {
            for(int j = 0; j < 3; j++) {
                if(input[0][j] == ch && input[1][j] == ch && input[2][j] == ch)
                    return true;
            }
            return false;
        }
    }
}