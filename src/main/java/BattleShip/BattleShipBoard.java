package BattleShip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sanketh on 10/29/16.
 */
public class BattleShipBoard extends JFrame {

    // Variables used by both BattleShipBoard and by Inner Classes
    private int max_size;
    private int num_pieces;
    private Computer comp;
    private Player player;
    private ArrayList<BattleShipButton> yourButtons = new ArrayList<>(max_size * max_size);
    private ArrayList<BattleShipButton> computerButtons = new ArrayList<>(max_size * max_size);

    // Methods used by both inner classes, BattleShipBoard, and by the Player Class.
    public int getMax_size() { return max_size; }
    private void setMax_size(int max_size) { this.max_size = max_size; }
    public int getNum_pieces() { return num_pieces; }
    private void setNum_pieces(int num_pieces) {
        this.num_pieces = num_pieces;
    }
    private BattleShipButton getPlayerButton(int row, int col) {
        return yourButtons.get(row * max_size + col);
    }

    // Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("How big should the board be? ");
        int max_size = sc.nextInt();

        System.out.println("How many pieces should each player have?");
        int num_pieces = sc.nextInt();

        // This line of code will initialize the board.
        new BattleShipBoard(max_size, num_pieces);
    }

    // Constructor
    private BattleShipBoard(int max_size, int num_pieces) {
        // The super method labels the JFrame to "BattleShip"
        super("BattleShip");

        // Initializing some variables.
        setMax_size(max_size);
        setNum_pieces(num_pieces);

        // Both the player and the comp share the same board.
        player = new Player();
        comp = new Computer();

        // Board must be set before initialization
        player.setBoard(this);
        comp.initialize();

        // GUI Creation
        setSize(400, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel myPanel = createOverallPanel();
        add(myPanel);
        setVisible(true);
    }

    // Overall JPanel Creation
    private JPanel createOverallPanel() {
        JPanel yourPanel = new JPanel();
        JPanel computerPanel = new JPanel();

        yourPanel = createGridOnPanel(yourPanel, yourButtons, "P");
        computerPanel = createGridOnPanel(computerPanel, computerButtons, "C");

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(1,2,20,20));
        myPanel.add(yourPanel);
        myPanel.add(computerPanel);
        return myPanel;
    }

    // Player and Computer Panel Creation
    private JPanel createGridOnPanel(JPanel aPanel, ArrayList<BattleShipButton> buttonArray,String user) {
        // Creating two Action Listener Objects
        PlayerButtonListener playerButtonListener = new PlayerButtonListener();
        ComputerButtonListener computerButtonListener = new ComputerButtonListener();

        aPanel.setLayout(new GridLayout(max_size, max_size));

        for (int i = 0; i < (max_size * max_size); i++) {
            BattleShipButton newButton = new BattleShipButton();

            newButton.setButtonLocation(new Coordinate(i/max_size, i % max_size));

            if (user.equals("C")) {
                newButton.addActionListener(computerButtonListener);
            } else {
                newButton.addActionListener(playerButtonListener);
            }

            buttonArray.add(newButton);
            aPanel.add(newButton);
        }
        return aPanel;
    }


    // PlayerButtonListener Class - Handle's piece setting
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

    // Handles Both Player and Computer Attacks
    class ComputerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // First if statement makes sure that Player has laid down pieces before making a strike.
            if (player.setShips) {

                // Following if statement ensures that no one has lost yet.
                if (!(player.getSunkShips().size() == getNum_pieces() || comp.getSunkShips().size() == getNum_pieces())) {

                    //Following two lines obtains Coordinate of button pressed.
                    BattleShipButton clickedButton = (BattleShipButton) e.getSource();
                    Coordinate currentLocation = clickedButton.getButtonLocation();

                    int row = currentLocation.row;
                    int col = currentLocation.col;

                    if (player.isGuessUnique(row, col)) {
                        // Player makes a guess at these coordinates.
                        boolean result = player.makeGuess(comp, row, col);

                        // Changes color of the button depending on whether or not the Player's attack was a hit or miss.
                        if (result) {
                            clickedButton.setBackground(Color.red);
                        } else {
                            clickedButton.setBackground(Color.black);
                        }
                        clickedButton.setOpaque(true);
                        clickedButton.setBorderPainted(false);

                        // Computer makes a guess.
                        result = comp.makeGuess(player);
                        Coordinate sunkShip = comp.getCurrentGuess();
                        BattleShipButton compGuess = getPlayerButton(sunkShip.row, sunkShip.col);

                        if (result) {
                            compGuess.setBackground(Color.red);
                        } else {
                            compGuess.setBackground(Color.black);
                        }
                        compGuess.setOpaque(true);
                        compGuess.setBorderPainted(false);
                    }

                } else {
                    // Prints who won the game!
                    if (player.getSunkShips().size() == getNum_pieces()) {
                        System.out.println("Computer won!");
                    } else if (comp.getSunkShips().size() == getNum_pieces()) {
                        System.out.println("You won!");
                    }
                }
            }
        }
    }

    class BattleShipButton extends JButton {
        private Coordinate buttonLocation;

        public void setButtonLocation(Coordinate buttonLocation) {
            this.buttonLocation = buttonLocation;
        }

        public Coordinate getButtonLocation() {
            return this.buttonLocation;
        }
    }


}
