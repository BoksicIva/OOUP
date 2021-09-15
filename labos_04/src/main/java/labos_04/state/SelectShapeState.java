package labos_04.state;

import labos_04.*;
import labos_04.Point;
import labos_04.grafical_object.CompositeShape;
import labos_04.grafical_object.GraphicalObject;
import labos_04.renderer.Renderer;
import labos_04.shape.Rectangle;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.Collectors;

public class SelectShapeState implements State {
    private DocumentModel model;
    private int PIXELS=1;

    public SelectShapeState(DocumentModel model) {
        this.model=model;

    }

    @Override
    public void mouseDown(Point mousePoint, boolean shiftDown, boolean ctrlDown) {
        GraphicalObject selectedObject=model.findSelectedGraphicalObject(mousePoint);
        if(ctrlDown && selectedObject!=null)
            selectedObject.setSelected(true);
        else if(!ctrlDown && selectedObject!=null){
            model.deselectSelected();
            selectedObject.setSelected(true);
        }else{
            model.deselectSelected();
        }
        model.notifyListeners();
    }

    @Override
    public void mouseUp(Point mousePoint, boolean shiftDown, boolean ctrlDown) {

    }

    @Override
    public void mouseDragged(Point mousePoint) {
        GraphicalObject selectedObject=model.findSelectedGraphicalObject(mousePoint);
        if(selectedObject!=null && model.getSelectedObjects().size()==1){
            double min=Double.MAX_VALUE;
            int index=-1;
            for(int i=0;i< selectedObject.getNumberOfHotPoints();i++){
                double distance=selectedObject.getHotPointDistance(i,mousePoint);
                if(distance<min){
                    min=distance;
                    index=i;
                }
            }
            if(index!=-1){
                selectedObject.setHotPoint(index,mousePoint);
            }
        }
        model.notifyListeners();
    }

    @Override
    public void keyPressed(int keyCode) {
        switch(keyCode){
            case KeyEvent.VK_UP:
                model.getSelectedObjects().forEach(o -> o.translate(new Point(0,-PIXELS)));
                break;
            case KeyEvent.VK_DOWN:
                model.getSelectedObjects().forEach(o -> o.translate(new Point(0,PIXELS)));
                break;
            case KeyEvent.VK_LEFT:
                model.getSelectedObjects().forEach(o -> o.translate(new Point(-PIXELS,0)));
                break;
            case KeyEvent.VK_RIGHT:
                model.getSelectedObjects().forEach(o -> o.translate(new Point(PIXELS,0)));
                break;
            case 107:
                model.getSelectedObjects().forEach(o -> model.increaseZ(o));
                break;
            case 109:
                model.getSelectedObjects().forEach(o -> model.decreaseZ(o));
                break;
            case KeyEvent.VK_U:
                ungroupSelection();
                break;
            case KeyEvent.VK_G:
                groupSelection();
                break;
        }

        model.notifyListeners();
    }

    private void groupSelection(){
        List<GraphicalObject> selectedObjects=model.list().stream().filter(GraphicalObject::isSelected).collect(Collectors.toList());
        if(selectedObjects.size()==1) return;

        //model.list().removeIf(selectedObjects::contains);
        selectedObjects.forEach(o->model.removeGraphicalObject(o));
        model.addGraphicalObject(new CompositeShape(selectedObjects,true));
    }

    private void ungroupSelection(){
        if(model.getSelectedObjects().size()!=1 || !(model.getSelectedObjects().get(0) instanceof CompositeShape)) return;
        List<GraphicalObject> selectedObjects= (((CompositeShape) model.getSelectedObjects().get(0)).getGroup());


        selectedObjects.forEach(o->model.addGraphicalObject(o));
        model.removeGraphicalObject(model.getSelectedObjects().get(0));
    }

    @Override
    public void afterDraw(Renderer r, GraphicalObject go) {
        if(go.isSelected()){
            drawRectangle(go.getBoundingBox(), r);
        }
    }

    @Override
    public void afterDraw(Renderer r) {
        if(model.getSelectedObjects().size()==1){
            GraphicalObject selectedObject=model.getSelectedObjects().get(0);

            for(int i=0;i<selectedObject.getNumberOfHotPoints();i++){
                Point hotPoint=selectedObject.getHotPoint(i);
                drawRectangle(new Rectangle(hotPoint.getX()-2,hotPoint.getY()-2,4,4),r);
            }
        }
    }

    @Override
    public void onLeaving() {
        model.deselectSelected();
    }

    private void drawRectangle(Rectangle rectangle,Renderer r){
        r.drawLine(new Point(rectangle.getX(),rectangle.getY()),new Point(rectangle.getX()+rectangle.getWidth(),rectangle.getY()));
        r.drawLine(new Point(rectangle.getX()+rectangle.getWidth(),rectangle.getY()),new Point(rectangle.getX()+rectangle.getWidth(),rectangle.getY()+rectangle.getHeight()));
        r.drawLine(new Point(rectangle.getX(),rectangle.getY()+rectangle.getHeight()),new Point(rectangle.getX()+rectangle.getWidth(),rectangle.getY()+rectangle.getHeight()));
        r.drawLine(new Point(rectangle.getX(),rectangle.getY()),new Point(rectangle.getX(),rectangle.getY()+rectangle.getHeight()));
    }
}
