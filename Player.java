import java.util.Scanner;

public class Player {
    private String name;
    private int score;

    public Player() {
        int points = 0;
        System.out.println("Enter your name: ");
        Scanner sc = new Scanner(System.in);
        String newName = sc.nextLine();
        String name = newName;
        System.out.println("Welcome to the game " + name);
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
    public void addToScore(int value) {
        this.score = value;
    }


}
