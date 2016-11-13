package BattleShip;

/**
 * Created by sanke on 10/25/2016.
 */
public class Coordinate {
    int row;
    int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean equals(int row, int y) {
        return this.row == row && this.col == y;
    }
}
