package labos_04.grafical_object;

import labos_04.GeometryUtil;
import labos_04.Point;
import labos_04.renderer.Renderer;
import labos_04.shape.Rectangle;

import java.util.List;
import java.util.Stack;

public class Oval extends AbstractGraphicalObject {
    private int NUM_POINTS=180;

    public Oval() {
        super(new Point[]{new Point(0,10),new Point(10,0)});
    }

    public Oval(Point startPoint,Point endPoint) {
        super(new Point[]{startPoint,endPoint});
    }


    @Override
    public Rectangle getBoundingBox() {
        int rightX=getHotPoint(1).getX();
        int rightY=getHotPoint(1).getY();

        int downX=getHotPoint(0).getX();
        int downY=getHotPoint(0).getY();

        int width=2*(rightX-downX);
        int height=2*(downY-rightY);

        int y=downY-height;
        int x=rightX-width;

        return new Rectangle(x,y,width,height);
    }

    @Override
    public double selectionDistance(Point mousePoint) {
//        Rectangle box=getBoundingBox();
//        double d1=GeometryUtil.distanceFromLineSegment(new Point(box.getX(),box.getY()),new Point(box.getX()+box.getWidth(),box.getY()),mousePoint);
//        double d2=GeometryUtil.distanceFromLineSegment(new Point(box.getX()+box.getWidth(),box.getY()),new Point(box.getX()+box.getWidth(),box.getY()+box.getHeight()),mousePoint);
//        double d3=GeometryUtil.distanceFromLineSegment(new Point(box.getX(),box.getY()+box.getHeight()),new Point(box.getX()+box.getWidth(),box.getY()+box.getHeight()),mousePoint);
//        double d4=GeometryUtil.distanceFromLineSegment(new Point(box.getX(),box.getY()),new Point(box.getX(),box.getY()+box.getHeight()),mousePoint);
//
//        return Math.min(Math.min(Math.min(d1,d2),d3),d4);
        int centarX=getHotPoint(1).getX()-getHotPoint(0).getX();
        int centarY=getHotPoint(0).getY()-getHotPoint(1).getY();

        int dx=mousePoint.getX()-centarX;
        int dy=mousePoint.getY()-centarY;

        return Math.abs(Math.sqrt(dx*dx+dy*dy)-GeometryUtil.distanceFromPoint(new Point(centarX,centarY),getHotPoint(1)));
    }

    @Override
    public void render(Renderer r) {
        Point[] points=new Point[NUM_POINTS];
        Rectangle box=getBoundingBox();

        int centarX=box.getX()+box.getWidth()/2;
        int centarY=box.getY()+box.getHeight()/2;


        for(int i=0;i<NUM_POINTS;i++){
            final double theta = Math.toRadians(((double) i / NUM_POINTS) * 360);
            int x= (int) (centarX+box.getWidth()*Math.cos(theta)/2);
            int y= (int) (centarY+box.getHeight()*Math.sin(theta)/2);
            Point point=new Point(x,y);
            points[i]=point;
        }

        r.fillPolygon(points);
    }

    @Override
    public String getShapeName() {
        return "Oval";
    }

    @Override
    public GraphicalObject duplicate() {
        Oval newOval=new Oval(getHotPoint(0),getHotPoint(1));
        return newOval;
    }

    @Override
    public String getShapeID() {
        return "@OVAL";
    }

    @Override
    public void load(Stack<GraphicalObject> stack, String data) {
        String[] dataSplit=data.split(" ");
        Oval duplicate= (Oval) duplicate();

        int counter=1;
        for(int i=1;i<getNumberOfHotPoints()+1;i++){
            int x=Integer.parseInt(dataSplit[counter++]);
            int y=Integer.parseInt(dataSplit[counter++]);
            Point p=new Point(x,y);
            duplicate.setHotPoint(i-1,p);
        }
        stack.push(duplicate);
    }

    @Override
    public void save(List<String> rows) {
        rows.add(this.toString());
    }

    @Override
    public String toString() {
        return getShapeID()+" "+getHotPoint(0).toString()+" "+getHotPoint(1).toString();
    }
}
