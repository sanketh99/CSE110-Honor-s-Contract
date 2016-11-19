package Thesis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sanketh on 11/12/16.
 */
public class MainPage {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    private MyDrawPanel drawPanel;
    private JPanel pallet;

    private Color shapeColor = Color.blue;

    private Rectangle tempRect = null;
    private HashMap<Shape, Color> shapes = new HashMap<>();

    public static void main(String[] args) {
        new MainPage().go();
    }

    private void go() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new MyDrawPanel();
        pallet = createPalletPanel();
        MouseEventCatcher mouseHandler = new MouseEventCatcher();

        drawPanel.addMouseListener(mouseHandler);
        drawPanel.addMouseMotionListener(mouseHandler);

        frame.setLayout(new GridLayout(1, 2, 20, 20));
        frame.add(pallet);
        frame.add(drawPanel);

        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    private JPanel createPalletPanel() {
        JButton blue = new JButton("Blue");
        JButton red = new JButton("Red");
        JButton yellow = new JButton("Yellow");
        ArrayList<JButton> allButtons = new ArrayList<>();

        allButtons.add(blue);
        allButtons.add(red);
        allButtons.add(yellow);

        ColorChangingListener colorChangingListener = new ColorChangingListener();

        JPanel pallet = new JPanel();
        pallet.setLayout(new GridLayout(allButtons.size(), 1));

        for (JButton button : allButtons) {
            button.addActionListener(colorChangingListener);
            pallet.add(button);
        }

        return pallet;
    }

    class ColorChangingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Blue") {
                shapeColor = Color.blue;
            } else if (e.getActionCommand() == "Red") {
                shapeColor = Color.red;
            } else if (e.getActionCommand() == "Yellow") {
                shapeColor = Color.yellow;
            }
        }
    }

    class MyDrawPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            if (tempRect != null) {
                g2d.setColor(shapeColor);
                g2d.fill(tempRect);
            }

            for (Map.Entry<Shape, Color> entry : shapes.entrySet()) {
                g2d.setColor(entry.getValue());
                g2d.fill(entry.getKey());
            }
        }
    }

    class MouseEventCatcher extends MouseAdapter {
        private Point p1;

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("Pressed.");
            p1 = e.getPoint();
            drawPanel.repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            System.out.println("Dragging....");
            tempRect = createRect(e);
            drawPanel.repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("Released.");
            tempRect = null;
            shapes.put(createRect(e), shapeColor);
            drawPanel.repaint();
        }

        private Rectangle createRect(MouseEvent e) {
            Point p2 = e.getPoint();
            int x = Math.min(p1.x, p2.x);
            int y = Math.min(p1.y, p2.y);
            int w = Math.abs(p1.x - p2.x);
            int h = Math.abs(p1.y - p2.y);
            return new Rectangle(x, y, w, h);
        }
    }

}