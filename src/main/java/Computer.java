import java.util.ArrayList;
import java.util.Random;

/**
 * Created by sanke on 10/25/2016.
 */
public class Computer extends Player {
    private Random rn = new Random();
    Coordinate currentGuess;
    private int x;
    private int y;

    public Computer() {
        while (this.getUnsunkShips().size() < this.getBoard().getNum_pieces()) {
            x = rn.nextInt(this.getBoard().getMax_size()+1);
            y = rn.nextInt(this.getBoard().getMax_size()+1);
            this.addShip(x, y);
        }
    }

    public boolean makeGuess(Player opponent) {
        x = rn.nextInt(this.getBoard().getMax_size())+1;
        y = rn.nextInt(this.getBoard().getMax_size())+1;
        ArrayList<Coordinate> allShots = this.getAllShots();

        while (getMatch(x, y, allShots)) {
            x = rn.nextInt(this.getBoard().getMax_size())+1;
            y = rn.nextInt(this.getBoard().getMax_size())+1;
        }

        System.out.println("Let's try " + x + ", " + y);
        currentGuess = new Coordinate(x, y);
        return opponent.checkGuess(x, y);
    }
}
