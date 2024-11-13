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
        System.out.println("Each player has one hint that they are able to use at any point in the game. Just type hint to receive your hint.");

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
                if (currentPlayer == 0){
                    if (player1.getHint() == 1) {
                        if (sc.nextLine().equals("hint")) {
                            hint(pieces);
                            player1.subtractHint();
                        }
                    }
                }
                else
                    if (player2.getHint() == 1) {
                        if (sc.nextLine().equals("hint")) {
                            hint(pieces);
                            player2.subtractHint();
                        }
                    }
                
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
    public static void hint(int pieces) {
        if (pieces > ((int)Math.pow(2,5)-1)) {
            int take_away =  pieces - 31;
            System.out.println("Take away " + take_away + " pieces");
        }
        else if (pieces > ((int)Math.pow(2,4)-1)) {
            int take_away = pieces - 15;
            System.out.println("Take away " + take_away + " pieces");
        }
        else if (pieces > ((int)Math.pow(2,3)-1)) {
            int take_away = pieces - 7;
            System.out.println("Take away " + take_away + " pieces");
        }
        else if (pieces > ((int)Math.pow(2,2)-1)) {
            int take_away = pieces - 3;
            System.out.println("Take away " + take_away + " pieces");
        }
        else if (pieces == 3) {
            System.out.println("Sorry buddy, you're on your own.");
        }
        else
            System.out.println("Bruh, just take 1. It's not that deep.");
    }
}
