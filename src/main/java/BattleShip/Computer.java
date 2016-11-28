package BattleShip;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by sanke on 10/25/2016.
 */
public class Computer extends Player {
    private Random rn = new Random();
    private Coordinate currentGuess;
    private ArrayList<Coordinate> allGuesses = new ArrayList<>();
    private int x;
    private int y;

    public Coordinate getCurrentGuess() {
        return currentGuess;
    }

    // Initializes the locations of the ships the computer sets.
    public void initialize() {
        while (this.getUnsunkShips().size() < this.getBoard().getNum_pieces()) {
            x = rn.nextInt(this.getBoard().getMax_size());
            y = rn.nextInt(this.getBoard().getMax_size());
            this.addShip(x, y);
        }
    }

    // Slightly different than the method within the Player class. This code makes sure that the computer
    // isn't using the same guesses.
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
