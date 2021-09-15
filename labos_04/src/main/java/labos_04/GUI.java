package labos_04;

import labos_04.actions.*;
import labos_04.grafical_object.GraphicalObject;
import labos_04.state.IdleState;
import labos_04.state.State;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static java.awt.BorderLayout.PAGE_START;

public class GUI extends JFrame {
    private List<GraphicalObject> objects;
    private DocumentModel documentModel;
    private Canvas canvas;
    private State currentState=new IdleState();

    public GUI(List<GraphicalObject> objects) {
        this.objects=objects;
        this.documentModel=new DocumentModel();
        this.canvas=new Canvas(documentModel,this);
        this.documentModel.addDocumentModelListener(canvas);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(0, 0);
        setMinimumSize(new Dimension(600,600));
        initGUI();

    }

    public List<GraphicalObject> getObjects() {
        return objects;
    }

    private void initGUI(){
        Container cp=getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(canvas,BorderLayout.CENTER);
        createToolbar();
    }


    private void createToolbar(){
        JToolBar toolBar = new JToolBar("Tools");
        toolBar.setFloatable(true);

        JButton loadButton=new JButton(new OpenAction("Učitaj",this,documentModel));
        toolBar.add(loadButton);


        JButton saveButton=new JButton(new SaveAction("Pohrani",this,documentModel));
        toolBar.add(saveButton);
        saveButton.setFocusable(false);

        JButton svgButton=new JButton(new SVGAction("SVG export",this,documentModel));
        toolBar.add(svgButton);


        for(GraphicalObject obj : objects){
            JButton button=new JButton(new AddShapeAction(obj.getShapeName(),this,documentModel,obj));
            toolBar.add(button);
        }

        JButton selectButton=new JButton(new SelectShapeAction("Selectiraj",this,documentModel));
        toolBar.add(selectButton);
        selectButton.setFocusable(false);

        JButton eraseButton=new JButton(new EraserAction("Brisalo",this,documentModel));
        toolBar.add(eraseButton);

        add(toolBar,PAGE_START);

    }

    public State getCurrentState(){
        return currentState;
    }

    public void setCurrentStateState(State s) {
        currentState.onLeaving();
        currentState = s;
    }



}
