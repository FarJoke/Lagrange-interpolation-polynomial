import java.awt.*;

public class Dot {
    private double x;
    private double y;

    public Dot (double x, double y){
        this.x = x;
        this.y = y;
    }
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    void draw(final Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
        g.setColor(Color.BLACK);
        g.drawString(".", (int) x, (int) y);
    }
}
