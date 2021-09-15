package labos_04;

import labos_04.grafical_object.GraphicalObject;
import labos_04.listeners.DocumentModelListener;
import labos_04.renderer.G2DRendererImpl;
import labos_04.renderer.Renderer;
import labos_04.state.IdleState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Canvas extends JComponent implements DocumentModelListener {
    private DocumentModel model;
    private GUI gui;
    private int PIXELS=1;

    public Canvas(DocumentModel model,GUI gui){
        setFocusable(true);
        this.model=model;
        this.gui=gui;
        model.addDocumentModelListener(this);
        registerKeyListeners();
        registerMouseListeners();
    }


    private void registerKeyListeners(){
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (KeyEvent.VK_ESCAPE==e.getKeyCode())
                    gui.setCurrentStateState(new IdleState());

                gui.getCurrentState().keyPressed(e.getKeyCode());
            }
        });
    }

    private void registerMouseListeners(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                gui.getCurrentState().mouseDown(new Point(e.getX(),e.getY()),e.isShiftDown(),e.isControlDown());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                gui.getCurrentState().mouseUp(new Point(e.getX(),e.getY()),e.isShiftDown(),e.isControlDown());
            }

        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                gui.getCurrentState().mouseDragged(new Point(e.getX(),e.getY()));
            }
        });
    }


    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        Renderer r = new G2DRendererImpl(g2d);

        g.setColor(Color.WHITE);
        g.fillRect(0,0,getWidth(),getHeight());

//        za svaki objekt o modela:
//        o.render(r);
        for(GraphicalObject obj: model.list()){
            obj.render(r);
            gui.getCurrentState().afterDraw(r,obj);
        }
        gui.getCurrentState().afterDraw(r);
        requestFocusInWindow();
    }


    @Override
    public void documentChange() {
        repaint();
    }
}
