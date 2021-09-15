package labos_04.renderer;

import labos_04.Point;

public interface Renderer {

    void drawLine(Point s, Point e);

    void fillPolygon(Point[] points);
}
