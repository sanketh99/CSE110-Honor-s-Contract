package BattleShip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Sanketh on 10/29/16.
 */
public class BattleShipBoard extends JFrame {
    private int max_size;
    private int num_pieces;
    private Computer comp;
    private Player player;
    private ArrayList<BattleShipButton> yourButtons = new ArrayList<>(max_size * max_size);
    private ArrayList<BattleShipButton> computerButtons = new ArrayList<>(max_size * max_size);

    public int getMax_size() {
        return max_size;
    }
    public void setMax_size(int max_size) {
        this.max_size = max_size;
    }
    public int getNum_pieces() {
        return num_pieces;
    }
    public void setNum_pieces(int num_pieces) {
        this.num_pieces = num_pieces;
    }
    public Computer getComp() {
        return comp;
    }
    public Player getPlayer() { return player; }

    public static void main(String[] args) {

        new BattleShipBoard(4);
    }

    public BattleShipButton getPlayerButton(int row, int col) {
        return yourButtons.get(row * max_size + col);
    }

    public BattleShipBoard(int max_size) {
        super("BattleShip");


        // haven't set up player or anything else yet :(
        setMax_size(max_size);
        setNum_pieces(5);
        setUp();
        // move all this later :(

        setSize(400, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel myPanel = createOverallPanel();
        add(myPanel);
        setVisible(true);
    }

    public JPanel createOverallPanel() {
        JPanel yourPanel = new JPanel();
        JPanel computerPanel = new JPanel();

        yourPanel = createGridOnPanel(yourPanel, yourButtons, "P");
        computerPanel = createGridOnPanel(computerPanel, computerButtons, "C");

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(1, 2,20,20));
        myPanel.add(yourPanel);
        myPanel.add(computerPanel);
        return myPanel;
    }

    private JPanel createGridOnPanel(JPanel aPanel, ArrayList<BattleShipButton> buttonArray,String user) {
        aPanel.setLayout(new GridLayout(max_size, max_size));
        PlayerButtonListener playerButtonListener = new PlayerButtonListener();
        ComputerButtonListener computerButtonListener = new ComputerButtonListener();
        for (int i = 0; i < (max_size * max_size); i++) {
            BattleShipButton newButton = new BattleShipButton();

            newButton.setButtonLocation(new Coordinate(i/max_size, i % max_size));
            newButton.setBoard(this);

            if (user.equals("C")) {
                newButton.setYourBoard(false);
                newButton.addActionListener(computerButtonListener);
            } else {
                newButton.setYourBoard(true);
                newButton.addActionListener(playerButtonListener);
            }

            buttonArray.add(newButton);
            aPanel.add(buttonArray.get(i));
        }
        return aPanel;
    }

    public void setUp() {
        player = new Player();
        player.setBoard(this);
        comp = new Computer();
        System.out.println("----------------For the sake of trouble shooting: -----------------");
        for (Coordinate marker : comp.getUnsunkShips()) {
            System.out.println("row: " + marker.row + " col: " + marker.col);
        }
    }

    class PlayerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!player.setShips) {
                BattleShipButton clickedButton = (BattleShipButton) e.getSource();

                Coordinate currentLocation = clickedButton.getButtonLocation();
                player.addShip(currentLocation.row, currentLocation.col);

                clickedButton.setBackground(Color.blue);
                clickedButton.setOpaque(true);
                clickedButton.setBorderPainted(false);

                if (player.getUnsunkShips().size() == getNum_pieces()) {
                    player.setShips = true;
                    System.out.println("Success");
                }
            }
        }
    }

    class ComputerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (player.setShips) {
                BattleShipButton clickedButton = (BattleShipButton) e.getSource();
                Coordinate currentLocation = clickedButton.getButtonLocation();

                int row = currentLocation.row;
                int col = currentLocation.col;

                boolean result = player.makeGuess(comp, row, col);

                if (result) {
                    clickedButton.setBackground(Color.red);
                    clickedButton.setOpaque(true);
                    clickedButton.setBorderPainted(false);
                } else {
                    clickedButton.setBackground(Color.black);
                    clickedButton.setOpaque(true);
                    clickedButton.setBorderPainted(false);
                }

                result = comp.makeGuess(player);
                Coordinate sunkShip = comp.currentGuess;
                BattleShipButton compGuess = getPlayerButton(sunkShip.row, sunkShip.col);

                if (result) {
                    compGuess.setBackground(Color.red);
                } else {
                    compGuess.setBackground(Color.black);
                }
                compGuess.setOpaque(true);
                compGuess.setBorderPainted(false);
            }
        }
    }


}
