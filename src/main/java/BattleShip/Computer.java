package BattleShip;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by sanke on 10/25/2016.
 */
public class Computer extends Player {
    private Random rn = new Random();
    Coordinate currentGuess;
    private ArrayList<Coordinate> allGuesses = new ArrayList<>();
    private int x;
    private int y;

    public Computer() {
        while (this.getUnsunkShips().size() < this.getBoard().getNum_pieces()) {
            x = rn.nextInt(this.getBoard().getMax_size());
            y = rn.nextInt(this.getBoard().getMax_size());
            this.addShip(x, y);
        }
    }

    public boolean makeGuess(Player opponent) {
        x = rn.nextInt(this.getBoard().getMax_size());
        y = rn.nextInt(this.getBoard().getMax_size());


        while (getMatch(x, y, allGuesses)) {
            x = rn.nextInt(this.getBoard().getMax_size());
            y = rn.nextInt(this.getBoard().getMax_size());
        }

        System.out.println("Let's try " + x + ", " + y);
        currentGuess = new Coordinate(x, y);
        allGuesses.add(currentGuess);
        return opponent.checkGuess(x, y);
    }
}
