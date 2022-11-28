import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class LagrangePolynomial2D {
    private List<Double> xValues;
    private List<Double> yValues;
    private List<Double> tValues = new ArrayList<>();

    public LagrangePolynomial2D(List<Double> xValues, List<Double> yValues){
        this.xValues = xValues;
        this.yValues = yValues;
    }

    public void getTv(){
        double t1 = 0;
        double t2 = 0;
        double sum = 0;
        this.tValues.clear();
        this.tValues.add(t1);
        for (int i = 1; i < xValues.size(); i++){
            sum += Math.pow( (xValues.get(i-1) - xValues.get(i)) * (xValues.get(i-1) - xValues.get(i)) -
                    (yValues.get(i-1) - yValues.get(i)) * (yValues.get(i-1) - yValues.get(i)), 0.5 );
        }
        for (int i = 1; i < xValues.size(); i++){
            t2 += Math.pow( (xValues.get(i-1) - xValues.get(i)) * (xValues.get(i-1) - xValues.get(i)) -
                    (yValues.get(i-1) - yValues.get(i)) * (yValues.get(i-1) - yValues.get(i)), 0.5 )/sum;
            tValues.add(t2);
        }
    }

    public void draw2D(final Graphics gr){
        Graphics2D g = (Graphics2D) gr;
        BasicStroke pen1 = new BasicStroke(3);
        g.setStroke(pen1);
        g.setColor(Color.GREEN.darker());

        LagrangePolynomial lp2dx = new LagrangePolynomial(this.tValues, this.xValues);
        LagrangePolynomial lp2dy = new LagrangePolynomial(this.tValues, this.yValues);
        int x1, x2;
        int y1, y2;
        x1 = (int) lp2dx.interpolateLagrangePolynomialX(0);
        y1 = (int) lp2dy.interpolateLagrangePolynomialX(0);
        for (double t = 0.0001; t < 1; t+= 0.0001){
            x2 = x1;
            x1 = (int) lp2dx.interpolateLagrangePolynomialX(t);
            y2 = y1;
            y1 = (int) lp2dy.interpolateLagrangePolynomialX(t);
            gr.drawLine(x1-5, y1-18, x2-5, y2-18);
        }
    }

    public void addDot(Dot dot) {
        this.xValues.add(dot.getX());
        this.yValues.add(dot.getY());
        getTv();
    }
}
