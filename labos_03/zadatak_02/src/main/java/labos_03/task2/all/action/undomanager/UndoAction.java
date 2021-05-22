package labos_03.task2.all.action.undomanager;


import labos_03.task2.all.TextEditorFrame;
import labos_03.task2.all.observers.UndoManagerObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UndoAction extends AbstractAction implements UndoManagerObserver {
    private TextEditorFrame frame;

    public UndoAction(TextEditorFrame frame){
        super("Undo");
        this.frame=frame;
        this.frame.getEditor().getModel().getUndoManager().addUndoObserver(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!frame.getEditor().getModel().getUndoManager().isUndoEmpty())
            frame.getEditor().getModel().getUndoManager().undo();

    }

    @Override
    public void update() {
        boolean empty=!frame.getEditor().getModel().getUndoManager().isUndoEmpty();
        frame.getEnableUndo().setEnable(empty);
    }
}
