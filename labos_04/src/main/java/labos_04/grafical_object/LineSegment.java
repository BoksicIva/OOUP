package labos_04.grafical_object;

import labos_04.GeometryUtil;
import labos_04.Point;
import labos_04.renderer.Renderer;
import labos_04.shape.Rectangle;

import java.util.List;
import java.util.Stack;

public class LineSegment extends AbstractGraphicalObject {


    public LineSegment() {
        super(new Point[]{new Point(0,0),new Point(10,0)});
    }

    public LineSegment(Point startPoint,Point endPoint) {
        super(new Point[]{startPoint,endPoint});
    }

    @Override
    public Rectangle getBoundingBox() {
        int leftX=Math.min(getHotPoint(0).getX(),getHotPoint(1).getX());
        int rightX=Math.max(getHotPoint(0).getX(),getHotPoint(1).getX());
        int upY=Math.min(getHotPoint(0).getY(),getHotPoint(1).getY());
        int downY=Math.max(getHotPoint(0).getY(),getHotPoint(1).getY());
        return new Rectangle(leftX,upY,rightX-leftX,downY-upY);
    }

    @Override
    public double selectionDistance(Point mousePoint) {
        return GeometryUtil.distanceFromLineSegment(getHotPoint(0),getHotPoint(1),mousePoint);
    }

    @Override
    public void render(Renderer r) {
        r.drawLine(getHotPoint(0),getHotPoint(1));
    }


    @Override
    public String getShapeName() {
        return "Linija";
    }

    @Override
    public GraphicalObject duplicate() {
        LineSegment newLineSegmet=new LineSegment(getHotPoint(0),getHotPoint(1));
        return newLineSegmet;
    }

    @Override
    public String getShapeID() {
        return "@LINE";
    }

    @Override
    public void load(Stack<GraphicalObject> stack, String data) {
        String[] dataSplit=data.split(" ");
        LineSegment duplicate= (LineSegment) duplicate();

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
