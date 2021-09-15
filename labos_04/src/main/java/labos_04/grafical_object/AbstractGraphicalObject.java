package labos_04.grafical_object;

import labos_04.GeometryUtil;
import labos_04.Point;
import labos_04.listeners.GraphicalObjectListener;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGraphicalObject implements GraphicalObject {
    private Point[] hotPoints;
    private boolean[] hotPointsSelected;
    private boolean selected;
    private List<GraphicalObjectListener> listeners=new ArrayList<>();

    public AbstractGraphicalObject(Point[] hotpoints){
        this.hotPoints=hotpoints;
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
        return hotPoints.length;
    }

    @Override
    public Point getHotPoint(int index) {
        return hotPoints[index];
    }

    @Override
    public void setHotPoint(int index, Point point) {
        hotPoints[index]=point;
    }

    @Override
    public boolean isHotPointSelected(int index) {
        return hotPointsSelected[index];
    }

    @Override
    public void setHotPointSelected(int index, boolean selected) {
        hotPointsSelected[index]=selected;
        notifySelectionListeners();
    }

    @Override
    public double getHotPointDistance(int index, Point mousePoint) {
        return GeometryUtil.distanceFromPoint(hotPoints[index],mousePoint);
    }

    @Override
    public void translate(Point delta) {
        int n=getNumberOfHotPoints();
        for(int i =0;i<n;i++){
            hotPoints[i]=hotPoints[i].translate(delta);
        }
        notifyListeners();
    }


    @Override
    public void addGraphicalObjectListener(GraphicalObjectListener l) {
        listeners.add(l);
    }

    @Override
    public void removeGraphicalObjectListener(GraphicalObjectListener l) {
        listeners.remove(l);
    }

    public void notifyListeners(){
        listeners.forEach(l -> l.graphicalObjectChanged(this));
    }

    public void notifySelectionListeners(){
        listeners.forEach(l -> l.graphicalObjectSelectionChanged(this));
    }


}
