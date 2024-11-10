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
        end = false;

    }
    public void play() {
        int currentPlayer = 1;
        while(Board.getPieces() > 1) {
            int pieces = Board.getPieces();
            Scanner sc = new Scanner(System.in);
            while (true) { 
                System.out.println("How many pieces would you like to take from half of " + pieces);
                int takePieces = sc.nextInt();
                if (takePieces <= pieces/2 && takePieces > 0) {
                    Board.removePieces(takePieces);
                    break;
                } else {
                    System.out.println("Please enter a valid input");
                }
            }
            

        }
        if (currentPlayer == 1) {
            // they winn
        } else {
            // player 2 win
        }

    }

}
