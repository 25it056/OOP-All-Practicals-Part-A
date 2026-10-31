import java.util.Random;
import java.util.Scanner;

public class RPSLS {
    enum Move { ROCK, PAPER, SCISSORS, LIZARD, SPOCK }

    // 1: player a wins, -1: player b wins, 0: tie
    static int winner(Move a, Move b) {
        if (a == b) return 0;
        return switch (a) {
            case ROCK -> (b == Move.SCISSORS || b == Move.LIZARD) ? 1 : -1;
            case PAPER -> (b == Move.ROCK || b == Move.SPOCK) ? 1 : -1;
            case SCISSORS -> (b == Move.PAPER || b == Move.LIZARD) ? 1 : -1;
            case LIZARD -> (b == Move.SPOCK || b == Move.PAPER) ? 1 : -1;
            case SPOCK -> (b == Move.SCISSORS || b == Move.ROCK) ? 1 : -1;
        };
    }

    public static void main(String[] args) {
        Random random = new Random();
        int playerScore = 0, computerScore = 0;

        try (Scanner sc = new Scanner(System.in)) {
            for (int round = 1; round <= 5; round++) {
                System.out.println("\nRound " + round);
                Move player;
                while (true) {
                    System.out.print("Enter ROCK, PAPER, SCISSORS, LIZARD or SPOCK: ");
                    try {
                        player = Move.valueOf(sc.nextLine().trim().toUpperCase());
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid move. Try again.");
                    }
                }

                Move computer = Move.values()[random.nextInt(Move.values().length)];
                System.out.println("Player Move: " + player);
                System.out.println("Computer Move: " + computer);
                int result = winner(player, computer);
                if (result == 1) {
                    playerScore++;
                    System.out.println("You win this round!");
                } else if (result == -1) {
                    computerScore++;
                    System.out.println("Computer wins this round!");
                } else {
                    System.out.println("It's a tie!");
                }
            }
        }

        System.out.println("\nPlayer Score: " + playerScore);
        System.out.println("Computer Score: " + computerScore);
        if (playerScore > computerScore) System.out.println("You win " + playerScore + "-" + computerScore);
        else if (computerScore > playerScore) System.out.println("Computer wins " + computerScore + "-" + playerScore);
        else System.out.println("The game is a tie!");
    }
}
