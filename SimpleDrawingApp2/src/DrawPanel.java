import jdk.jfr.EventType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import static java.awt.event.MouseEvent.BUTTON1;

public class DrawPanel extends JPanel implements ActionListener {

    private final int PANEL_WIDTH;
    private final int PANEL_HEIGHT;
    private final int TIMER_DELAY;



    private Timer timer;
    private int ticksFromStart = 0;

    Dot d = new Dot(10, 10);


    public DrawPanel(final int width, final int height, final int timerDelay) {
        this.PANEL_WIDTH = width;
        this.PANEL_HEIGHT = height;
        this.TIMER_DELAY = timerDelay;
        timer = new Timer(timerDelay, this);
        timer.start();
    }

    LinkedList<Dot> p = new LinkedList<>();
    double x;
    double y;

    @Override
    public void paint(final Graphics gr) {
        super.paint(gr);
        Point location = MouseInfo.getPointerInfo().getLocation();
        x = location.getX() - 15;
        y = location.getY() - 15;
        d.setX(x);
        d.setY(y);
        d.draw(gr);

        for (int i = 0; i < p.size(); i++){
            d.setX(p.get(i).getX() - 15);
            d.setY(p.get(i).getY() - 15);
            d.draw(gr);
        }
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               Dot clickedDot = new Dot(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y);
               p.add(clickedDot);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


   }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
            ++ticksFromStart;
        }
    }
}