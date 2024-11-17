import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private boolean aI = false;

    public Game() {
        System.out.println("Do you want to play with a computer? (Y/N)");
        Scanner sc = new Scanner(System.in);
        if(sc.nextLine().equals("Y")) {
            player1 = new Player();
            player2 = new Player("AI");
            aI = true;
            System.out.println("You'll have one hint");
        } else {
            System.out.println("Player 1");
            player1 = new Player();
            System.out.println("Player 2");
            player2 = new Player();
            System.out.println("Each player has one hint that they are able to use at any point in the game.");
        }
        

    }
    public void play() {
        if (aI == true) {
            singlePlayer();
        } else {
            multiplayer();
        }
    }

    public void singlePlayer() {
        int currentPlayer = 0; // Ai
        while(Board.getPieces() > 1) {

            int pieces = Board.getPieces();
            Scanner sc = new Scanner(System.in);

            currentPlayer = (currentPlayer % 2);
            if (currentPlayer == 0) {
                System.out.println("\nIt's " + player2.getName() + "'s turn");
                int aiMove = computerMove(pieces);
                System.out.println("\nAI took " + aiMove + " pieces");
                currentPlayer++;
            } else {
                System.out.println("\nIt's " + player1.getName() + "'s turn");
                while (true) { 
                    System.out.println("How many pieces would you like to take from half of " + pieces + "?");
                    if (player1.getHint() == 1) {
                        System.out.println("Would you like to use your hint? (Y/N)");
                        if (sc.nextLine().equals("Y")) {
                            hint(pieces);
                            player1.subtractHint();
                        }
                    }
                    Scanner sc2 = new Scanner(System.in);    
                    int takePieces = sc2.nextInt();

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

        }   
        endGame(currentPlayer-1);
    }
    public void multiplayer() {
        int currentPlayer = (int) ((Math.random() * 2));
        while(Board.getPieces() > 1) {
            int pieces = Board.getPieces();
            Scanner sc = new Scanner(System.in);
            currentPlayer = (currentPlayer % 2);
            if (currentPlayer == 0) {
                System.out.println("\nIt's " + player2.getName() + "'s turn");
            } else {
                System.out.println("\nIt's " + player1.getName() + "'s turn");
            }

            while (true) { 
                
                System.out.println("How many pieces would you like to take from half of " + pieces + "?");
                // if player 2 has a hint
                if (currentPlayer == 0) {
                    if (player1.getHint() == 1) {
                        System.out.println("Would you like to use your hint? (Y/N)");
                        if (sc.nextLine().equals("Y")) {
                            hint(pieces);
                            player1.subtractHint();
                        }
                    }
                }
                // if player 1 has a hint
                else {
                    if (player2.getHint() == 1) {
                        System.out.println("Would you like to use your hint? (Y/N)");
                        if (sc.nextLine().equals("Y")) {
                            hint(pieces);
                            player2.subtractHint();
                            
                        }
                    }
                } 
                Scanner sc2 = new Scanner(System.in);    
                int takePieces = sc2.nextInt();
                System.out.println("pieces: " + (int)pieces/2);
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
        // end game
        endGame(currentPlayer);
    }

    public int computerMove(int pieces) {
        int power = 2;
        int take;
        while(power < pieces) {
            power *= 2;
        }
        power /= 2;
        take = pieces - (power - 1);
        if(take <= (int) pieces/2) {
            Board.removePieces(take);
            return take;
        } else {
            Board.removePieces(1);
            return 1;
        }

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

    public void endGame(int currentPlayer){
        if (currentPlayer%2 == 0) {
            System.out.println( player2.getName() + " won the game\n");
            player2.addToScore();
        } else {
            System.out.println( player1.getName() + " won the game\n");
            player1.addToScore();
        }
        System.out.println("Scores: " + player1.getName() +": "+ player1.getScore() + " | "  + player2.getName() +": "+ player2.getScore());
        playAgain();
    }

    // unique feature 
    public static void hint(int pieces) {
        int take_away;
        if (pieces > (Math.pow(2,5)-1) && (int) pieces/2 >= pieces - 31) {
            take_away =  pieces - 31;
            System.out.println("Take away " + take_away + " pieces");
        }
        else if ( pieces > (Math.pow(2,4)-1) && (int) pieces/2 >= pieces - 15 ) {
            take_away = pieces - 15;
            System.out.println("Take away " + take_away + " pieces");
        }
        else if (pieces > (Math.pow(2,3)-1) && (int) pieces/2 >= pieces - 7 ) {
            take_away = pieces - 7;
            System.out.println("Take away " + take_away + " pieces");
        }
        else if (pieces > (Math.pow(2,2)-1) && (int) pieces/2 >= pieces - 3 ) {
            take_away = pieces - 3;
            System.out.println("Take away " + take_away + " pieces"); 
        }
        else if (pieces == 3) {
            System.out.println("Sorry buddy, you're on your own.");
        }
        else if (pieces == 2) {
            System.out.println("Bruh, just take 1. It's not that deep.");
        } 
        else {
            System.out.println("Take away " + 1 + " pieces");
        }
    }
}
