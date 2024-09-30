package lt.techin.games;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {

    private static String[] firstLine = new String[]{"   ", "   ", "   "};
    private static String[] secondLine = new String[]{"   ", "   ", "   "};
    private static String[] thirdLine = new String[]{"   ", "   ", "   "};
    private static List<String[]> lines = Arrays.asList(firstLine, secondLine, thirdLine);
    private static boolean stopPlaying = false;
    private static int rounds = 0;

    public static void main(String[] args) {

        while (!stopPlaying) {
            Scanner scanner = new Scanner(System.in);

            askForData("Player X", scanner);
            printTable();
            stopPlaying = checkIfWon("Player X");
            if (stopPlaying) {
                break;
            }
            askForData("Player O", scanner);
            printTable();
            stopPlaying = checkIfWon("Player O");
        }
    }

    private static boolean checkIfWon(String player) {
        String playerSign = " " + player.charAt(player.length() - 1) + " ";
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(0)[i].equals(playerSign) && lines.get(1)[i].equals(playerSign) && lines.get(2)[i].equals(playerSign)) {
                System.out.println("Congratulations! " + player + " has won!");
                return true;
            }
        }
        for (String[] strings : lines) {
            if (strings[0].equals(playerSign) && strings[1].equals(playerSign) && strings[2].equals(playerSign)) {
                System.out.println("Congratulations! " + player + " has won!");
                return true;
            }
        }

        if ((lines.get(0)[0].equals(playerSign) && lines.get(1)[1].equals(playerSign) && lines.get(2)[2].equals(playerSign))
                || (lines.get(0)[2].equals(playerSign) && lines.get(1)[1].equals(playerSign) && lines.get(2)[0].equals(playerSign))) {
            System.out.println("Congratulations! " + player + " has won!");
            return true;
        } else if (rounds == 9) {
            System.out.println("Its a draw!");
            return true;
        }
        return false;
    }

    public static void printTable() {
        for (String[] line : lines) {
            System.out.println("--------------");
            System.out.print("|");
            for (String s : line) {
                System.out.print(s);
                System.out.print("|");
            }
            System.out.print("\n");
        }

    }

    public static void askForData(String player, Scanner scanner) {
        System.out.println("Please choose row and column (separated by Enter) " + player + ":");
        int row = Integer.parseInt(scanner.nextLine()) - 1;
        int column = Integer.parseInt(scanner.nextLine()) - 1;
        if (lines.get(row)[column].equals("   ")) {
            lines.get(row)[column] = " " + player.charAt(player.length() - 1) + " ";
            rounds++;
        } else {
            System.out.println("This choice is not possible, please choose empty space:");
            askForData(player, scanner);
        }
    }
}






