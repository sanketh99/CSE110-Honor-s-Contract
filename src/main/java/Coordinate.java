/**
 * Created by sanke on 10/25/2016.
 */
public class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(int x, int y) {
        return this.x == x && this.y == y;
    }
}
