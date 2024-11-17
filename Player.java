import java.util.Scanner;

public class Player {
    private String name;
    private int score;
    private int hints;

    public Player() {
        System.out.println("Enter your name: ");
        Scanner sc = new Scanner(System.in);
        String newName = sc.nextLine();
        this.name = newName;
        this.score = 0;
        this.hints = 1;
        System.out.println("Welcome to the game " + name);
    }
    public Player(String aIPlayer) {
        this.name = aIPlayer;
        this.score = 0;
    }

    public String getName() {
        return this.name;
    }
    public int getScore() {
        return this.score;
    }
    public void setName(String inputName) {
        this.name = inputName;
    }
    public void addToScore() {
        this.score += 1;
    }
    public int getHint() {
        return this.hints;
    }
    public void subtractHint() {
        this.hints -= 1;
    }
    
}
