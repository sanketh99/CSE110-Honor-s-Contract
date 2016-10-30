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

    public ArrayList<Coordinate> getUnsunkShips() {
        return unsunkShips;
    }

    public void addShip(int x, int y) {
        if (!(getMatch(x, y, unsunkShips))) {
            this.unsunkShips.add(new Coordinate(x, y));
        } else {
            System.out.println("Ship has already been added!");
        }
    }

    public void setBoard(BattleShipBoard board) {
        this.board = board;
    }

    public BattleShipBoard getBoard() {
        return board;
    }

    public ArrayList<Coordinate> getSunkShips() {
        return sunkShips;
    }


    public ArrayList<Coordinate> getAllShots() {
        ArrayList<Coordinate> shots = new ArrayList<Coordinate>();
        shots.addAll(hits);
        shots.addAll(misses);
        return shots;
    }

    public boolean checkGuess(int x, int y) {
        for (Coordinate marker : unsunkShips) {
            if (marker.equals(x, y)) {
                sunkShips.add(marker);
                unsunkShips.remove(marker);
                return true;
            }
        }
        return false;
    }

    public boolean makeGuess(Player opponent, int x, int y) {
        boolean result = opponent.checkGuess(x, y);
        if (result) {
            hits.add(new Coordinate(x, y));
        } else {
            misses.add(new Coordinate(x, y));
        }
        return result;
    }

    public boolean validateGuess(int x, int y) {
        boolean bounds = false;
        if (0 < x && x <= this.getBoard().getMax_size()) {
            if (0 < y && y <= this.getBoard().getMax_size()) {
                bounds = true;
            }
        }
        return (!getMatch(x, y, getAllShots()) && bounds);
    }

    public boolean getMatch(int x, int y, ArrayList<Coordinate> searchArray) {
        for (Coordinate marker : searchArray) {
            if (marker.equals(x, y)) {
                return true;
            }
        }
        return false;
    }
}
