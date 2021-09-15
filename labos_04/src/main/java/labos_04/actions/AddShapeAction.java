package labos_04.actions;

import labos_04.DocumentModel;
import labos_04.GUI;
import labos_04.grafical_object.GraphicalObject;
import labos_04.state.AddShapeState;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddShapeAction extends AbstractAction {
    private GUI gui;
    private DocumentModel model;
    private GraphicalObject prototype;

    public AddShapeAction(String name, GUI gui, DocumentModel model, GraphicalObject prototype){
        super(name);
        this.gui=gui;
        this.model=model;
        this.prototype=prototype;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.setCurrentStateState(new AddShapeState(model,prototype));
    }
}
