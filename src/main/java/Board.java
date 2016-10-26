import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by sanke on 10/25/2016.
 */
public class Board {
    private int max_size;
    private int num_pieces;
    private Computer comp;
    private Player player;

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

    public Player getPlayer() {
        return player;
    }

    public void setUp() {
        player = new Player();
        player.setBoard(this);
        comp = new Computer();
        System.out.println("----------------For the sake of trouble shooting: -----------------");
        for (Coordinate marker : comp.getUnsunkShips()) {
            System.out.println("x: " + marker.x + " y: " + marker.y);
        }
    }

    public void displayBoard() {
        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(createNewPanel());
        frame.add(createNewPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private TestPane createNewPanel() {
        TestPane test = new TestPane();
        test.setSize(getMax_size());
        return test;
    }
}

class TestPane extends JPanel {
    private int columnCount;
    private int rowCount;
    private List<Rectangle> cells;

    public void setSize(int n) {
        this.columnCount = n;
        this.rowCount = n;
    }

    public TestPane() {
        cells = new ArrayList<Rectangle>(columnCount * rowCount);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
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
