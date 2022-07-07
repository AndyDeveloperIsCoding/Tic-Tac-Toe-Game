
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] collection = createCollection();
        printWholeField(collection);

        while (xWins != true && oWins != true && draw != true) { // Earlier: xWins != true | oWins != true | draw != true
            handleCoordinatesInput(scanner, collection);
            fieldFillIn(collection, index);
            printWholeField(collection);
            checkWinner(collection);
        }

    }
    private static void checkWinner(String[] collection) {

        // Communicate the game situation

        //Start of flagging

        for (int i = 0; i < collection.length; i++) {
            // 8 conditions for X to win
            // a) Three rows
            if (collection[0].equals("X") && collection[1].equals("X") && collection[2].equals("X")) {
                xWins = true;
                break; // earlier continue or break;
            }
            if (collection[3].equals("X") && collection[4].equals("X") && collection[5].equals("X")) {
                xWins = true;
                break;
            }
            if (collection[6].equals("X") && collection[7].equals("X") && collection[8].equals("X")) {
                xWins = true;
                break;
            }
            // b) Three columns
            if (collection[0].equals("X") && collection[3].equals("X") && collection[6].equals("X")) {
                xWins = true;
                break;
            }
            if (collection[1].equals("X") && collection[4].equals("X") && collection[7].equals("X")) {
                xWins = true;
                break;
            }
            if (collection[2].equals("X") && collection[5].equals("X") && collection[8].equals("X")) {
                xWins = true;
                break;
            }
            // b) Two diagonals
            if (collection[0].equals("X") && collection[4].equals("X") && collection[8].equals("X")) {
                xWins = true;
                break;
            }
            if (collection[6].equals("X") && collection[4].equals("X") && collection[2].equals("X")) {
                xWins = true;
                break;
            }


            // Flagging for Os

            // 8 conditions for O to win
            // a) Three rows
            if (collection[0].equals("O") && collection[1].equals("O") && collection[2].equals("O")) {
                //System.out.println("O wins");
                oWins = true;
                break;
            }
            if (collection[3].equals("O") && collection[4].equals("O") && collection[5].equals("O")) {
                //System.out.println("O wins");
                oWins = true;
                break; // earlier continue;
            }
            if (collection[6].equals("O") && collection[7].equals("O") && collection[8].equals("O")) {
                //System.out.println("O wins");
                oWins = true;
                break;
            }
            // b) Three columns
            if (collection[0].equals("O") && collection[3].equals("O") && collection[6].equals("O")) {
                //System.out.println("O wins");
                oWins = true;
                break;
            }
            if (collection[1].equals("O") && collection[4].equals("O") && collection[7].equals("O")) {
                //System.out.println("O wins");
                oWins = true;
                break;
            }
            if (collection[2].equals("O") && collection[5].equals("O") && collection[8].equals("O")) {
                //System.out.println("O wins");
                oWins = true;
                break;
            }
            // b) Two diagonals
            if (collection[0].equals("O") && collection[4].equals("O") && collection[8].equals("O")) {
                //System.out.println("O wins");
                oWins = true;
                break;
            }
            if (collection[6].equals("O") && collection[4].equals("O") && collection[2].equals("O")) {
                //System.out.println("O wins");
                oWins = true;
                break;
            }
            // Condition for draw

            if (collection[0] != "_" && collection[1] != "_" && collection[2] != "_" && collection[3] != "_" &&
                    collection[4] != "_" && collection[5] != "_" && collection[6] != "_" && collection[7] != "_" &&
                    collection[8] != "_") {
                draw = true;
                break;
            }
        }

        //Start of communicating

        if (xWins == true && oWins == false) {
            System.out.println("X wins");
            return;

        }
        if (oWins == true && xWins == false) {
            System.out.println("O wins");
            return;
        }
        if (draw == true) {
            System.out.println("Draw");
            return;
        }
    }


    private static String symbolCalculation() {

        if (turn % 2 != 0) {
            symbol = "X";
        } else if (turn % 2 == 0) {
            symbol = "O";
        }
        return symbol;
    }

    private static String[] createCollection() {

        String[] collection = new String[9];
        for (int i = 0; i < collection.length; i++) {
            collection[i] = "_";
        }

        return collection;
    }

    /*
        private static int[] handleCoordinatesInput_ifInRange(Scanner scanner) {

            while (true) {
                if (rangeValidation()) {
                    // rangeCorrect = true;
                    break;
                } else {
                    System.out.println("Coordinates should be from 1 to 3");
                    break;
                }
            }

            return arrayTemp;
        }
     */
    public static boolean rangeValidation() {

        int rowGiven = arrayTemp[0];
        int columnGiven = arrayTemp[1];

        if (rowGiven > 3 || columnGiven > 3 || rowGiven < 1 || columnGiven < 1) {
            return false;
        }
        return true;
    }

    private static void handleCoordinatesInput(Scanner scanner, String[] collection) {

        // First check- is it int-type
        while (true) {
            if (validateInput(scanner)) {
                break;
            }
        }

        // Second check- is int-type in the allowed range
        while (true) {
            if (rangeValidation()) {
                break;
            }
            System.out.println("Coordinates should be from 1 to 3");
            handleCoordinatesInput(scanner, collection);
        }

        // Third check- is the user-requested field available or already booked
        index = calculateGivenFieldIndex();

        while (true) {
            if (givenFieldCheck(collection, index)) {
                break;
            }
            System.out.println("This cell is occupied! Choose another one!");
            handleCoordinatesInput(scanner, collection);
        }
    }

    public static int[] arrayTemp = new int[2];
    public static int index = 0;

    public static int turn = 0;
    public static String symbol = "Blank";

    public static boolean xWins = false;
    public static boolean oWins = false;
    public static boolean draw = false;

    private static boolean validateInput(Scanner scanner) {

        System.out.println("Enter the coordinates:");

        try {
            String rowGiven = scanner.next();
            String columnGiven = scanner.next();

            int rowGivenInt = Integer.valueOf(rowGiven);
            int columnGivenInt = Integer.valueOf(columnGiven);

            arrayTemp[0] = rowGivenInt;
            arrayTemp[1] = columnGivenInt;

            return true;

        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("You should enter numbers!");
        }

        return false;
    }

    private static void fieldFillIn(String[] collection, int index) {
        turn = turn + 1;
        collection[index] = symbolCalculation();
    }

    private static int calculateGivenFieldIndex() {

        int index = 1000;

        int rowGiven = arrayTemp[0];
        int columnGiven = arrayTemp[1];

        if (rowGiven == 1 && columnGiven == 1) {
            index = 0;
        } else if (rowGiven == 1 && columnGiven == 2) {
            index = 1;
        } else if (rowGiven == 1 && columnGiven == 3) {
            index = 2;
        } else if (rowGiven == 2 && columnGiven == 1) {
            index = 3;
        } else if (rowGiven == 2 && columnGiven == 2) {
            index = 4;
        } else if (rowGiven == 2 && columnGiven == 3) {
            index = 5;
        } else if (rowGiven == 3 && columnGiven == 1) {
            index = 6;
        } else if (rowGiven == 3 && columnGiven == 2) {
            index = 7;
        } else if (rowGiven == 3 && columnGiven == 3) {
            index = 8;
        }

        return index;
    }

    private static boolean givenFieldCheck(String[] collection, int index) {
        if (collection[index].contains("_")) {
            return true;
        }
        return false;
    }

    private static void printWholeField(String[] collection) {
        System.out.println("--------- ");
        System.out.print("| ");
        for (int i = 0; i < 3; i++) {
            System.out.print(collection[i] + " ");

        }
        System.out.print("|");
        System.out.println();
        System.out.print("| ");
        for (int j = 3; j < 6; j++) {
            System.out.print(collection[j] + " ");
        }
        System.out.println("|");
        System.out.print("| ");
        for (int k = 6; k < 9; k++) {
            System.out.print(collection[k] + " ");
        }
        System.out.println("|");
        System.out.println("--------- ");

    }


}

