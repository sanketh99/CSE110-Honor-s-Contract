package BattleShip;

import java.util.ArrayList;

/**
 * Created by sanke on 10/25/2016.
 */
public class Player {
    private ArrayList<Coordinate> unsunkShips = new ArrayList<Coordinate>();
    private ArrayList<Coordinate> sunkShips = new ArrayList<Coordinate>();
    private ArrayList<Coordinate> hits = new ArrayList<Coordinate>();
    private ArrayList<Coordinate> misses = new ArrayList<Coordinate>();
    private static BattleShipBoard board;
    boolean setShips = false;

    public void setBoard(BattleShipBoard board) {
        this.board = board;
    }
    public ArrayList<Coordinate> getUnsunkShips() {
        return unsunkShips;
    }
    public BattleShipBoard getBoard() {
        return board;
    }
    public ArrayList<Coordinate> getSunkShips() {
        return sunkShips;
    }

    // Adds Player's ships to the board. Ensures that player doesn't add a ship on a spot where there is already a ship.
    public void addShip(int row, int col) {
        if (!(getMatch(row, col, unsunkShips))) {
            this.unsunkShips.add(new Coordinate(row, col));
        } else {
            System.out.println("Ship has already been added!");
        }
    }

    // This function is for the opposing player (or computer) to use. It checks to see whether the opponent's guess was a hit or miss.
    public boolean checkGuess(int row, int col) {
        for (Coordinate marker : unsunkShips) {
            if (marker.equals(row, col)) {
                sunkShips.add(marker);
                unsunkShips.remove(marker);
                return true;
            }
        }
        return false;
    }

    // This function determines whether the Player's guess (strike on the computer) is a hit or a miss.
    public boolean makeGuess(Player opponent, int row, int col) {
        boolean result = opponent.checkGuess(row, col);
        if (result) {
            hits.add(new Coordinate(row, col));
        } else {
            misses.add(new Coordinate(row, col));
        }
        return result;
    }


    // Searches to see if a set of coordinates exist within an array.
    public boolean getMatch(int x, int y, ArrayList<Coordinate> searchArray) {
        for (Coordinate marker : searchArray) {
            if (marker.equals(x, y)) {
                return true;
            }
        }
        return false;
    }

    public boolean isGuessUnique(int x, int y) {
        ArrayList<Coordinate> allHitsandMisses = new ArrayList<>();
        allHitsandMisses.addAll(hits);
        allHitsandMisses.addAll(misses);

        return !getMatch(x, y, allHitsandMisses);
    }
}
