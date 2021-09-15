package labos_04.state;

import labos_04.Point;
import labos_04.renderer.Renderer;
import labos_04.grafical_object.GraphicalObject;

public interface State {


    /**
     * poziva se kad progam registrira da je pritisnuta lijeva tipka miša
     * @param mousePoint
     * @param shiftDown
     * @param ctrlDown
     */
    void mouseDown(Point mousePoint, boolean shiftDown, boolean ctrlDown);


    /**
     * poziva se kad progam registrira da je otpuštena lijeva tipka miša
     * @param mousePoint
     * @param shiftDown
     * @param ctrlDown
     */
    void mouseUp(Point mousePoint, boolean shiftDown, boolean ctrlDown);



    /**
     * poziva se kad progam registrira da korisnik pomiče miš dok je tipka pritisnuta
     * @param mousePoint
     */
    void mouseDragged(Point mousePoint);



    /**
     * poziva se kad progam registrira da je korisnik pritisnuo tipku na tipkovnici
     * @param keyCode
     */
    void keyPressed(int keyCode);


    /**
     * Poziva se nakon što je platno nacrtalo grafički objekt predan kao argument
     * @param r
     * @param go
     */
    void afterDraw(Renderer r, GraphicalObject go);


    /**
     * Poziva se nakon što je platno nacrtalo čitav crtež
     * @param r
     */
    void afterDraw(Renderer r);


    /**
     * Poziva se kada program napušta ovo stanje kako bi prešlo u neko drugo
     */
    void onLeaving();

}
