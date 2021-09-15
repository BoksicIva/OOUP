package labos_04.renderer;


import labos_04.Point;
import labos_04.renderer.Renderer;

import java.awt.*;

public class G2DRendererImpl implements Renderer {


    private Graphics2D g2d;

    public G2DRendererImpl(Graphics2D g2d) {
       this.g2d=g2d;
    }

    @Override
    public void drawLine(labos_04.Point s, labos_04.Point e) {
        // Postavi boju na plavu
        // Nacrtaj linijski segment od S do E
        // (sve to uporabom g2d dobivenog u konstruktoru)
        g2d.setColor(Color.BLUE);
        g2d.drawLine(s.getX(),s.getY(),e.getX(),e.getY());
    }

    @Override
    public void fillPolygon(Point[] points) {
        // Postavi boju na plavu
        // Popuni poligon definiran danim točkama
        // Postavi boju na crvenu
        // Nacrtaj rub poligona definiranog danim točkama
        // (sve to uporabom g2d dobivenog u konstruktoru)
        g2d.setColor(Color.BLUE);

        int n=points.length;
        int[] xPoints=new int[n];
        int[] yPoints=new int[n];

        for(int i=0;i<n;i++){
            xPoints[i]=points[i].getX();
            yPoints[i]=points[i].getY();
        }

        g2d.fillPolygon(xPoints,yPoints,n);
        g2d.setColor(Color.RED);
        g2d.drawPolygon(xPoints,yPoints,n);
    }

}
