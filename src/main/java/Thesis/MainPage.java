package Thesis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by Sanketh on 11/12/16.
 */
public class MainPage {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private MyDrawPanel drawPanel;
    private Rectangle tempRect = null;
    private ArrayList<Shape> shapes = new ArrayList<>();

    public static void main(String[] args) {
        new MainPage().go();
    }

    private void go() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new MyDrawPanel();
        MouseEventCatcher mouseHandler = new MouseEventCatcher();

        drawPanel.addMouseListener(mouseHandler);
        drawPanel.addMouseMotionListener(mouseHandler);

        frame.getContentPane().add(drawPanel);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    class MyDrawPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            if (tempRect != null) {
                g2d.setColor(Color.blue);
                g2d.fill(tempRect);
            }

            g2d.setColor(Color.blue);
            for (Shape shape : shapes) {
                g2d.fill(shape);
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
            shapes.add(createRect(e));
            drawPanel.repaint();
        }

        private Rectangle createRect(MouseEvent e) {
            Point p2 = e.getPoint();
            int x = Math.min(p1.x, p2.x);
            int y = Math.min(p1.y, p2.y);
            int w = Math.abs(p1.x - p2.x);
            int h = Math.abs(p1.y - p2.y);
            Rectangle rect = new Rectangle(x, y, w, h);
            return rect;
        }
    }


}