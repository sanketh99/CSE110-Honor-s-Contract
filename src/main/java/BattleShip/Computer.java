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
    private int row;
    private int col;

    public Coordinate getCurrentGuess() {
        return currentGuess;
    }

    // Initializes the locations of the ships the computer sets.
    public void initialize() {
        while (this.getUnsunkShips().size() < this.getBoard().getNum_pieces()) {
            row = rn.nextInt(this.getBoard().getMax_size());
            col = rn.nextInt(this.getBoard().getMax_size());
            this.addShip(row, col);
        }
    }

    // Slightly different than the method within the Player class. This code makes sure that the computer
    // isn't using the same guesses.
    public boolean makeGuess(Player opponent) {
        row = rn.nextInt(this.getBoard().getMax_size());
        col = rn.nextInt(this.getBoard().getMax_size());


        while (getMatch(row, col, allGuesses)) {
            row = rn.nextInt(this.getBoard().getMax_size());
            col = rn.nextInt(this.getBoard().getMax_size());
        }

        System.out.println("Let's try " + row + ", " + col);
        currentGuess = new Coordinate(row, col);
        allGuesses.add(currentGuess);
        return opponent.checkGuess(row, col);
    }
}
