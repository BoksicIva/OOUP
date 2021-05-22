package labos_03.task2.all.action.undomanager;

import labos_03.task2.all.TextEditor;
import labos_03.task2.all.TextEditorFrame;
import labos_03.task2.all.observers.UndoManagerObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RedoAction extends AbstractAction implements UndoManagerObserver {
    private TextEditorFrame frame;

    public RedoAction(TextEditorFrame frame){
        super("Redo");
        this.frame=frame;
        this.frame.getEditor().getModel().getUndoManager().addRedoObserver(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!frame.getEditor().getModel().getUndoManager().getRedoStack().isEmpty())
            frame.getEditor().getModel().getUndoManager().redo();
    }

    @Override
    public void update() {
        boolean empty=!frame.getEditor().getModel().getUndoManager().isUndoEmpty();
        frame.getEnableRedo().setEnable(empty);
    }
}
