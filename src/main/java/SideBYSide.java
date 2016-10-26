package SSCCE;

import java.awt.*;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SideBYSide extends JFrame{

    public static void main(String[] args) {
        new SideBYSide();
    }

    public SideBYSide(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        JPanel container = new JPanel();
//        JPanel panelOne = new JPanel();
//        JPanel panelTwo = new JPanel();
//
//        panelOne.add(new JLabel("1"));
//        panelTwo.add(new JLabel("2"));

        TestPane panelOne = new TestPane();
        JPanel player = new JPanel();
        TestPane panelTwo = new TestPane();
        JPanel opponent = new JPanel();

        player.add(new JLabel("Player"));
        opponent.add(new JLabel("Computer"));

        panelOne.setSize(5);
        panelTwo.setSize(5);

        container.setLayout(new GridLayout(1,2,1,2));
        container.add(panelOne);
        container.add(panelTwo);
        container.add(player);
        container.add(opponent);


        this.add(container);
    }

}
class TestPane extends JPanel {
    private int columnCount;
    private int rowCount;
    private java.util.List<Rectangle> cells;

    public void setSize(int n) {
        this.columnCount = n;
        this.rowCount = n;
    }

    public TestPane() {
        cells = new ArrayList<Rectangle>(columnCount * rowCount);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        int cellWidth = width / columnCount;
        int cellHeight = height / rowCount;

        int xOffset = (width - (columnCount * cellWidth)) / 2;
        int yOffset = (height - (rowCount * cellHeight)) / 2;

        if (cells.isEmpty()) {
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < columnCount; col++) {
                    Rectangle cell = new Rectangle(
                            xOffset + (col * cellWidth),
                            yOffset + (row * cellHeight),
                            cellWidth,
                            cellHeight);
                    cells.add(cell);
                }
            }
        }

        g2d.setColor(Color.GRAY);
        for (Rectangle cell : cells) {
            g2d.draw(cell);
        }

        g2d.dispose();
    }
}