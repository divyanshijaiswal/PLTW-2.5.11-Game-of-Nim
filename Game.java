import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private boolean ai;

    public Game() {
        System.out.println("Do you want to play with a computer? (Y/N)");
        Scanner sc = new Scanner(System.in);
        if(sc.nextLine().equals("Y")) {
            player1 = new Player();
            player2 = new Player("AI");
            ai = true;
            System.out.println("You'll have one hint");
        } else {
            System.out.println("Player 1");
            player1 = new Player();
            System.out.println("Player 2");
            player2 = new Player();
            System.out.println("Each player has one hint that they are able to use at any point in the game.");
        }
        

    }
    /**
     * Executes the gameplay based on the mode of the game.
     * <p>
     * If the game is set to single-player mode (indicated by {@code ai == true}),
     * the {@code singlePlayer()} method runs. Otherwise, the 
     * {@code multiPlayer()} method is called for multiplayer gameplay.
     * </p>
     *
     * <pre>
     * Preconditions:
     * - The {@code ai} field must be initialized to determine the game mode.
     *
     * Postconditions:
     * - If {@code ai == true}, the single-player game mode is executed.
     * - If {@code ai == false}, the multiplayer game mode is executed.
     * </pre>
     */
    public void play() {
        if (ai == true) {
            singlePlayer();
        } else {
            multiPlayer();
        }
    }

    public void singlePlayer() {

        int currentPlayer = 0; // Ai will start first

        while(Board.getPieces() > 1) {
            int pieces = Board.getPieces();
            Scanner sc = new Scanner(System.in);
            if (currentPlayer % 2 == 0) {
                System.out.println("\nIt's " + player2.getName() + "'s turn");
                int aiMove = computerMove(pieces);
                System.out.println("\nAI took " + aiMove + " pieces");
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
                    if (takePieces <= (int) (pieces/2) && takePieces > 0) {
                        Board.removePieces(takePieces);
                        System.out.println("Num of pieces: " + Board.getPieces());
                        
                        break;
                    } else {
                        System.out.println("Please enter a valid input");
                    }

                }
            }
            currentPlayer++;

        }   
        endGame(currentPlayer-1);
    }
    public void multiPlayer() {

        int currentPlayer = (int) ((Math.random() * 2));

        while(Board.getPieces() > 1) {
            int pieces = Board.getPieces();
            Scanner sc = new Scanner(System.in);
            if (currentPlayer % 2 == 0) {
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
                if (takePieces <= (int) (pieces/2) && takePieces > 0) {
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
    /**
     * Determines and executes the computer's move in a game by applying a strategic approach.
     * <p>
     * The computer calculates the optimal number of pieces to take based on a power-of-2 - 1 strategy. 
     * If the calculated number of pieces to take is less than or equal to half the remaining pieces, 
     * it removes that number. Otherwise, it defaults to removing one piece.
     * </p>
     *
     * <pre>
     * Preconditions:
     * - {@code pieces} must be greater than zero.
     * - The {@code Board.removePieces()} method must be implemented and correctly update the game state.
     *
     * Postconditions:
     * - A valid number of pieces, either the strategic calculation or one piece, is removed from the board.
     * - The method returns the number of pieces removed by the computer.
     * </pre>
     *
     * @param pieces the total number of pieces remaining on the board before the computer's move
     * @return the number of pieces removed by the computer
     */
    public int computerMove(int pieces) {
        int power = 2;
        int take;
        while(power < pieces) {
            power *= 2;
        }
        power /= 2;
        take = pieces - (power - 1);
        // strategy 1
        if(take <= (int) (pieces/2)) {
            Board.removePieces(take);
            return take;
        } 
        // strategy 2
        else {
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
    public void hint(int pieces) {
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
