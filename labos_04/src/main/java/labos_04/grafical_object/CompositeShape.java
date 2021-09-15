package labos_04.grafical_object;

import labos_04.Point;
import labos_04.renderer.Renderer;
import labos_04.listeners.GraphicalObjectListener;
import labos_04.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CompositeShape extends AbstractGraphicalObject {
    private  List<GraphicalObject> group;
    private List<GraphicalObjectListener> listeners;
    private boolean selected;

    public CompositeShape(List<GraphicalObject> group,boolean selected){
        super(null);
        this.group=group;
        setSelected(selected);
    }

    public List<GraphicalObject> getGroup() {
        return group;
    }

    public void setGroup(List<GraphicalObject> group) {
        this.group = group;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected=selected;
        notifySelectionListeners();
    }

    @Override
    public int getNumberOfHotPoints() {
        return 0;
    }

    @Override
    public Point getHotPoint(int index) {
        return null;
    }

    @Override
    public void setHotPoint(int index, Point point) {

    }

    @Override
    public boolean isHotPointSelected(int index) {
        return false;
    }

    @Override
    public void setHotPointSelected(int index, boolean selected) {

    }

    @Override
    public double getHotPointDistance(int index, Point mousePoint) {
        return 0;
    }


    @Override
    public void translate(Point delta) {
        group.forEach(o->o.translate(delta));

    }

    @Override
    public Rectangle getBoundingBox() {
        Rectangle box=null;

        for(GraphicalObject obj:group){
            Rectangle shapeBB=obj.getBoundingBox();
            if(box==null)
                box=shapeBB;
            else
                box=box.union(shapeBB);
        }
        return box;

    }

    @Override
    public double selectionDistance(Point mousePoint) {
        return group.stream().map(o -> o.selectionDistance(mousePoint)).min(Double::compare).get();
    }

    @Override
    public void render(Renderer r) {
        group.forEach(o ->o.render(r));
    }


    @Override
    public String getShapeName() {
        return "Group";
    }

    @Override
    public GraphicalObject duplicate() {
        List<GraphicalObject> newGroup=new ArrayList<>(group);
        CompositeShape newCompositeShape=new CompositeShape(newGroup,isSelected());
        return newCompositeShape;
    }

    @Override
    public String getShapeID() {
        return "@COMP";
    }

    @Override
    public void load(Stack<GraphicalObject> stack, String data) {
        String[] dataSplit=data.split(" ");
        int n=Integer.parseInt(dataSplit[1]);
        CompositeShape duplicate=(CompositeShape) duplicate();

        for(int i=0;i<n;i++){
           GraphicalObject obj=stack.pop();
            duplicate.group.add(obj);
        }
        stack.push(duplicate);
    }

    @Override
    public void save(List<String> rows) {
        group.forEach(o->o.save(rows));
        rows.add(this.toString());
    }

    @Override
    public String toString() {
        return getShapeID()+ " "+group.size() +" prethodna";
    }
}
