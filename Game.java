import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private boolean end;
    //private boolean 
   // Random rand = new Random ();

    public Game() {
        System.out.println("Player 1");
        player1 = new Player();
        System.out.println("Player 2");
        player2 = new Player();
        //end = false;

    }
    public void play() {
        int currentPlayer = (int) ((Math.random() * 2));

        while(Board.getPieces() > 1) {
            int pieces = Board.getPieces();
            Scanner sc = new Scanner(System.in);
            currentPlayer = (currentPlayer % 2);
            System.out.println("\nIt's Player " + (currentPlayer+1) + "'s turn");

            while (true) { 
                System.out.println("How many pieces would you like to take from half of " + pieces + "?");
                int takePieces = sc.nextInt();
                if (takePieces <= (int) pieces/2 && takePieces > 0) {
                    Board.removePieces(takePieces);
                    System.out.println("Num of pieces: " + Board.getPieces());
                    currentPlayer++;
                    break;
                } else {
                    System.out.println("Please enter a valid input");
                }
            }
        }
        if (currentPlayer%2 == 0) {
            System.out.println("Player 2 won the game\n");
        } else {
            System.out.println("Player 1 won the game\n");
        }
        playAgain();
    }

    public void playAgain() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to play again? (Y/N)");
        if (sc.nextLine().toUpperCase().equals("Y")) {
            System.out.println();
            Board.populate();
            play();
        }

    }

}
