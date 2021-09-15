package labos_04.actions;

import labos_04.DocumentModel;
import labos_04.GUI;
import labos_04.grafical_object.GraphicalObject;
import labos_04.state.AddShapeState;
import labos_04.state.EraserState;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EraserAction extends AbstractAction {
    private GUI gui;
    private DocumentModel model;

    public EraserAction(String name, GUI gui, DocumentModel model){
        super(name);
        this.gui=gui;
        this.model=model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.setCurrentStateState(new EraserState(model));
    }



}
