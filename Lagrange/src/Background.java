import java.awt.*;

public class Background {
    private int interval;
    private int window_high;
    private int window_width;

    public Background(int interval, int window_high, int window_width){
        this.interval = interval;
        this.window_high = window_high;
        this.window_width = window_width;

    }

    public void setInterval(int interval){
        this.interval = interval;
    }

    void draw(final Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setColor(Color.BLACK);
        for (int i = 0; i < window_width; i += interval) {
            g.drawLine(i, 0, i, window_high);
        }
        for (int i = 0; i < window_high; i += interval) {
            g.drawLine(0, i, window_width, i);
        }

    }
}
