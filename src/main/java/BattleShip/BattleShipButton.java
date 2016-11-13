package BattleShip;

import javax.swing.*;

/**
 * Created by Sanketh on 10/29/16.
 */
public class BattleShipButton extends JButton {
    private boolean yourBoard = true;
    private Coordinate buttonLocation;
    private BattleShipBoard board;

    public void setYourBoard(boolean yourBoard) {
        this.yourBoard = yourBoard;
    }

    public void setButtonLocation(Coordinate buttonLocation) {
        this.buttonLocation = buttonLocation;
    }

    public void setBoard(BattleShipBoard board) {
        this.board = board;
    }

    public Coordinate getButtonLocation() {
        return this.buttonLocation;
    }
}
