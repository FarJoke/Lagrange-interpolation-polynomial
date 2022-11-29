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
            sum += Math.sqrt((xValues.get(i-1) - xValues.get(i)) * (xValues.get(i-1) - xValues.get(i)) -
                    (yValues.get(i-1) - yValues.get(i)) * (yValues.get(i-1) - yValues.get(i)));
        }
        for (int i = 1; i < xValues.size(); i++){
            t2 += Math.sqrt((xValues.get(i-1) - xValues.get(i)) * (xValues.get(i-1) - xValues.get(i)) -
                    (yValues.get(i-1) - yValues.get(i)) * (yValues.get(i-1) - yValues.get(i)))/sum;
            tValues.add(t2);
        }
    }

    public List<Dot> getGraph2dPoints(){
        List<Dot> graphPoints = new ArrayList<>();
        LagrangePolynomial lp2dx = new LagrangePolynomial(this.tValues, this.xValues);
        LagrangePolynomial lp2dy = new LagrangePolynomial(this.tValues, this.yValues);
        Dot grPoint;
        int x, y;
        for (double t = 1; t >= 0; t-= 0.0001){;
            x = (int) lp2dx.interpolateLagrangePolynomialX(t);
            y = (int) lp2dy.interpolateLagrangePolynomialX(t);
            grPoint = new Dot(x, y);
            graphPoints.add(grPoint);
        }
        return graphPoints;
    }

    public void draw2D(final Graphics gr){
        Graphics2D g = (Graphics2D) gr;
        BasicStroke pen1 = new BasicStroke(3);
        g.setStroke(pen1);
        g.setColor(Color.GREEN.darker());

        List<Dot> graphPoints = getGraph2dPoints();

        int x1;
        int y1;
        int x2;
        int y2;
        for (int i = 1; i < graphPoints.size(); i++){
            x1 = (int) graphPoints.get(i-1).getX();
            x2 = (int) graphPoints.get(i).getX();
            y1 = (int) graphPoints.get(i-1).getY();
            y2 = (int) graphPoints.get(i).getY();
            gr.drawLine(x1-5, y1-18, x2-5, y2-18);
        }
    }

    public void addDot(Dot dot) {
        this.xValues.add(dot.getX());
        this.yValues.add(dot.getY());
        getTv();
    }
}
