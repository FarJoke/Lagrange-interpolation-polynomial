import java.awt.*;
import java.util.List;

public class LagrangePolynomial {
    private List<Double> xValues;
    private List<Double> yValues;

    public LagrangePolynomial(List<Double> xValues, List<Double> yValues){
        this.xValues = xValues;
        this.yValues = yValues;
    }

    public void addDot(Dot dot){
        this.xValues.add(dot.getX());
        this.yValues.add(dot.getY());
    }

    private double InterpolateLagrangePolynomialX(double x)
    {
        double lagrangePol = 0;
        for (int i = 0; i < xValues.size(); i++)
        {
            double basicsPol = 1;
            for (int j = 0; j < xValues.size(); j++)
            {
                if (j != i)
                {
                    basicsPol *= (x - this.xValues.get(j))/(this.xValues.get(i) - this.xValues.get(j));
                }
            }
            lagrangePol += basicsPol * this.yValues.get(i);
        }

        return lagrangePol;
    }

    public void draw(final Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        int b = 0;
        int b1;
        for (int i = 1; i < 2000; i++){
            g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            g.setColor(Color.BLACK);
            b1 = b;
            b = (int) InterpolateLagrangePolynomialX(i);
            //g.drawString(".",i - 15 ,(int) InterpolateLagrangePolynomialX(i) - 15);
            g.drawLine(i-5, b1 - 18, i-5 , b - 18);
            //g.drawString(".",  b, (int) InterpolateLagrangePolynomialY(b) - 15);
        }
    }

}
