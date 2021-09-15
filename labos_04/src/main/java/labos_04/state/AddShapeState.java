package labos_04.state;

import labos_04.DocumentModel;
import labos_04.Point;
import labos_04.renderer.Renderer;
import labos_04.grafical_object.GraphicalObject;

public class AddShapeState implements State{
    private GraphicalObject prototype;
    private DocumentModel model;

    public AddShapeState(DocumentModel model, GraphicalObject prototype) {
       this.model=model;
       this.prototype=prototype;
    }


    @Override
    public void mouseDown(Point mousePoint, boolean shiftDown, boolean ctrlDown) {
        // dupliciraj zapamćeni prototip, pomakni ga na poziciju miša i dodaj u model
        GraphicalObject object=prototype.duplicate();
        object.translate(mousePoint);
        model.addGraphicalObject(object);
    }

    @Override
    public void mouseUp(Point mousePoint, boolean shiftDown, boolean ctrlDown) {

    }

    @Override
    public void mouseDragged(Point mousePoint) {

    }

    @Override
    public void keyPressed(int keyCode) {

    }

    @Override
    public void afterDraw(Renderer r, GraphicalObject go) {

    }

    @Override
    public void afterDraw(Renderer r) {

    }

    @Override
    public void onLeaving() {
        model.deselectSelected();
    }
}
