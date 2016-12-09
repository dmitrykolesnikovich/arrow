package arrow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

    public static void main(String[] args) {
        Vector p1 = new Vector().setValue(50, 50);
        Vector p2 = new Vector();

        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contantPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);

                Angle rotation = new Angle().setValue(Math.toDegrees(Math.atan2(0, 10) - Math.atan2(p2.y - p1.y, p2.x - p1.x)));

                Vector p22 = rotate(p2, p1, rotation);
                Vector arrowPoint = new Vector().setValue(p22.x - 20, p22.y);

                Vector arrow1 = rotate(arrowPoint, p22, new Angle().setValue(30));
                Vector arrow2 = rotate(arrowPoint, p22, new Angle().setValue(-30));

                Angle rotationRevert = rotation.cloneRevert();
                Vector arrow12 = rotate(arrow1, p1, rotationRevert);
                Vector arrow22 = rotate(arrow2, p1, rotationRevert);

                // draw arrows
                g.drawLine((int) arrow12.x, (int) arrow12.y, (int) p2.x, (int) p2.y);
                g.drawLine((int) arrow22.x, (int) arrow22.y, (int) p2.x, (int) p2.y);
            }
        };
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                setTarget(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                setTarget(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                setTarget(e);
            }

            private void setTarget(MouseEvent e) {
                p2.x = e.getX();
                p2.y = e.getY();
                contantPanel.repaint();
            }
        };
        contantPanel.addMouseListener(adapter);
        contantPanel.addMouseMotionListener(adapter);
        frame.getContentPane().add(contantPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static Vector rotate(Vector point, Vector o, Angle angle) {
        double x = point.x;
        double y = point.y;
        double ox = o.x;
        double oy = o.y;
        double resultX = ((x - ox) * angle.cos() - (y - oy) * angle.sin()) + ox;
        double resultY = ((x - ox) * angle.sin() + (y - oy) * angle.cos()) + oy;
        return new Vector().setValue(resultX, resultY);
    }


}
