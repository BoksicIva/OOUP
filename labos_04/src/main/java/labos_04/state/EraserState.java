package labos_04.state;

import labos_04.DocumentModel;
import labos_04.Point;
import labos_04.renderer.Renderer;
import labos_04.grafical_object.GraphicalObject;

import java.util.ArrayList;
import java.util.List;

public class EraserState implements State{

    private DocumentModel model;
    private int PIXELS=1;
    private List<Point> pointsOnWay=new ArrayList<>();

    public EraserState(DocumentModel model) {
        this.model=model;
    }

    @Override
    public void mouseDown(Point mousePoint, boolean shiftDown, boolean ctrlDown) {
        pointsOnWay.add(mousePoint);
        model.notifyListeners();
    }

    @Override
    public void mouseUp(Point mousePoint, boolean shiftDown, boolean ctrlDown) {
        pointsOnWay.add(mousePoint);
        List<GraphicalObject> copy=new ArrayList<>(model.list());
        for(GraphicalObject obj:copy){

            for(int i=0;i<pointsOnWay.size();i++){
                if(obj.selectionDistance(pointsOnWay.get(i))<=3){
                    model.removeGraphicalObject(obj);
                }
            }
        }
        pointsOnWay.clear();
    }

    @Override
    public void mouseDragged(Point mousePoint) {
        pointsOnWay.add(mousePoint);
        model.notifyListeners();
    }

    @Override
    public void keyPressed(int keyCode) {

    }

    @Override
    public void afterDraw(Renderer r, GraphicalObject go) {

    }

    @Override
    public void afterDraw(Renderer r) {
        for(int i=0;i<pointsOnWay.size()-1;i++)
            r.drawLine(pointsOnWay.get(i),pointsOnWay.get(i+1));
    }

    @Override
    public void onLeaving() {
        model.deselectSelected();
    }
}
