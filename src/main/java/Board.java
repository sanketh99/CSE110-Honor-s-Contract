//import javax.swing.*;
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * Created by sanke on 10/25/2016.
// */
//public class Board {
//    private int max_size;
//    private int num_pieces;
//    private Computer comp;
//    private Player player;
//
//    public int getMax_size() {
//        return max_size;
//    }
//
//    public void setMax_size(int max_size) {
//        this.max_size = max_size;
//    }
//
//    public int getNum_pieces() {
//        return num_pieces;
//    }
//
//    public void setNum_pieces(int num_pieces) {
//        this.num_pieces = num_pieces;
//    }
//
//    public Computer getComp() {
//        return comp;
//    }
//
//    public Player getPlayer() {
//        return player;
//    }
//
//    public void setUp() {
//        player = new Player();
//        player.setBoard(this);
//        comp = new Computer();
//        System.out.println("----------------For the sake of trouble shooting: -----------------");
//        for (Coordinate marker : comp.getUnsunkShips()) {
//            System.out.println("row: " + marker.row + " col: " + marker.col);
//        }
//    }
//
//}