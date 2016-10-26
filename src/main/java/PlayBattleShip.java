import java.util.Scanner;

/**
 * Created by sanke on 10/25/2016.
 */
public class PlayBattleShip {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        Computer comp;
        Player user;
        boolean yourTurn = true;
        int x;
        int y;

        board.setMax_size(3);
        board.setNum_pieces(3);
        board.setUp();
        user = board.getPlayer();
        comp = board.getComp();
        board.displayBoard();

        while (user.getUnsunkShips().size() < board.getNum_pieces()) {
            System.out.println("Enter coordinates of ship: ");
            System.out.println("x: ");
            x = sc.nextInt();
            System.out.println("y: ");
            y = sc.nextInt();
            user.addShip(x, y);
        }

        while (user.getSunkShips().size() < board.getNum_pieces() && comp.getSunkShips().size() < board.getNum_pieces()){
            if (yourTurn) {
                System.out.println("It's your turn!");
                System.out.println("Please enter a guess: ");
                System.out.println("x: ");
                x = sc.nextInt();
                System.out.println("y: ");
                y = sc.nextInt();
                if (!user.validateGuess(x, y)) {
                    System.out.println("");
                    System.out.println("You've already entered that coordinate or your coordinate is out of bounds!");
                    System.out.println("Please try again.");
                    System.out.println("x: ");
                    x = sc.nextInt();
                    System.out.println("y: ");
                    y = sc.nextInt();
                }
                boolean result = user.makeGuess(comp, x, y);
                System.out.println("");
                if (result) {
                    System.out.println("Congrats! You've hit a ship!");
                } else {
                    System.out.println("You've missed! :(");
                }
                yourTurn = false;
                System.out.println("----------------------------------------");
            } else {
                System.out.println("It's the computer's turn!");
                boolean result = comp.makeGuess(user);
                System.out.println("");
                if (result) {
                    System.out.println("Your ship has sunk :(");
                } else {
                    System.out.println("It's a miss!");
                }
                yourTurn = true;
                System.out.println("----------------------------------------");
            }
        }
        System.out.println("");
        System.out.println("Game is over!");
    }
}
