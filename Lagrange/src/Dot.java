import java.awt.*;

public class Dot {
    private double x;
    private double y;
    private int size;
    private Color color;

    public Dot (double x, double y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setSize(int size){
        this.size = size;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }



    void draw(final Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setFont(new Font("TimesRoman", Font.PLAIN, size));
        g.setColor(color);
        g.drawString(".", (int) x, (int) y);
    }
}
