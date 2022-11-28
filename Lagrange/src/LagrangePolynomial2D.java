import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class LagrangePolynomial2D {
    private List<Double> xValues;
    private List<Double> yValues;
    private List<Double> tValues = new ArrayList<>();
    private  LagrangePolynomial lp2dx;
    private  LagrangePolynomial lp2dy;

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
            sum += Math.pow( Math.pow((xValues.get(i-1) - xValues.get(i)), 2) -
                    Math.pow((yValues.get(i-1) - yValues.get(i)), 2), 0.5 );
        }
        for (int i = 1; i < xValues.size(); i++){
            t2 += Math.pow( Math.pow((xValues.get(i-1) - xValues.get(i)), 2) -
                    Math.pow((yValues.get(i-1) - yValues.get(i)), 2), 0.5 )/sum;
            tValues.add(t2);
        }
    }

    public void draw2D(final Graphics gr){
        List<Double> xDValues = new ArrayList<>();
        List<Double> yDValues = new ArrayList<>();
        lp2dx = new LagrangePolynomial(tValues, xValues);
        lp2dy = new LagrangePolynomial(tValues, yValues);

        for (double t = 0; t < 1; t+= 0.0001){
            xDValues.add(lp2dx.InterpolateLagrangePolynomialX(t));
            yDValues.add(lp2dy.InterpolateLagrangePolynomialX(t));
            gr.drawString(".", (int) lp2dx.InterpolateLagrangePolynomialX(t), (int) lp2dy.InterpolateLagrangePolynomialX(t));
        }
    }

    public void addDot(Dot dot) {
        this.xValues.add(dot.getX());
        this.yValues.add(dot.getY());
        getTv();
    }
}
