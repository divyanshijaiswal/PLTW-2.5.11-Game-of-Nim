public class Board {
    private static int pieces;

    public static void populate() {
        pieces = (int) ((Math.random() * (51 - 10)) + 10);
        System.out.println("Number of pieces: " + pieces);
    }
    public static int getPieces() {
        return pieces;
    }
    public static void removePieces(int input) {
        pieces -= input;
    }
}
