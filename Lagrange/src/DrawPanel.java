import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class DrawPanel extends JPanel implements ActionListener {

    private int PANEL_WIDTH;
    private int PANEL_HEIGHT;
    private final int TIMER_DELAY;



    private Timer timer;
    private int ticksFromStart = 0;
    private int clickes = 0;

    Dot d = new Dot(10, 10, 70);
    Background bckgrnd = new Background(15, 1200, 1600);

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
    boolean f;
    LinkedList<Double> xs = new LinkedList<Double>();
    LinkedList<Double> ys = new LinkedList<Double>();
    //LagrangePolynomial Lp = new LagrangePolynomial(xs, ys);
    LagrangePolynomial2D Lp2D = new LagrangePolynomial2D(xs, ys);

    @Override
    public void paint(final Graphics gr) {
        super.paint(gr);
        Point location = MouseInfo.getPointerInfo().getLocation();
        x = location.getX() - 15;
        y = location.getY() - 15;
        d.setX(x);
        d.setY(y);
        d.draw(gr);
        bckgrnd.draw(gr);

        if (clickes > 1) {
            Lp2D.draw2D(gr);
        }
        if (f == true){
            Lp2D.addDot(p.getLast());
            f = false;
        }


        for (int i = 0; i < p.size(); i++){
            d.setX(p.get(i).getX() - 15);
            d.setY(p.get(i).getY() - 15);
            d.setColor(Color.RED);
            d.draw(gr);
        }
        d.setColor(Color.BLACK);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               Dot clickedDot = new Dot(MouseInfo.getPointerInfo().getLocation().x,
                       MouseInfo.getPointerInfo().getLocation().y, 70);
               p.add(clickedDot);
               f = true;
               clickes++;
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