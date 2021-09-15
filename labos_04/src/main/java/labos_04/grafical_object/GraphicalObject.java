package labos_04.grafical_object;

import labos_04.Point;
import labos_04.renderer.Renderer;
import labos_04.shape.Rectangle;
import labos_04.listeners.GraphicalObjectListener;

import java.util.List;
import java.util.Stack;

public interface GraphicalObject {

    // Podrška za uređivanje objekta
    boolean isSelected();

    void setSelected(boolean selected);

    int getNumberOfHotPoints();

    Point getHotPoint(int index);

    void setHotPoint(int index, Point point);

    boolean isHotPointSelected(int index);

    void setHotPointSelected(int index, boolean selected);

    double getHotPointDistance(int index, Point mousePoint);

    /**
     *  Geometrijska operacija nad oblikom
     */
    void translate(Point delta);

    Rectangle getBoundingBox();

    double selectionDistance(Point mousePoint);

    /**
     * // Podrška za crtanje (dio mosta)
     * @param r
     */
    void render(Renderer r);

    public void addGraphicalObjectListener(GraphicalObjectListener l);

    public void removeGraphicalObjectListener(GraphicalObjectListener l);

    /**
     * // Podrška za prototip (alatna traka, stvaranje objekata u crtežu, ...)
     * @return
     */
    String getShapeName();

    GraphicalObject duplicate();

    /**
     *  // Podrška za snimanje i učitavanje
     * @return
     */
    public String getShapeID();

    public void load(Stack<GraphicalObject> stack, String data);

      public void save(List<String> rows);
}
