package labos_03.task2.all.action;

import labos_03.task2.all.TextEditor;
import labos_03.task2.all.UndoManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class
DeleteAction extends AbstractAction {
    private TextEditor editor;

    public DeleteAction(TextEditor editor){
        super("Delete");
        this.editor=editor;
        //putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("DELETE"));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        UndoManagerAction action=new UndoManagerAction(editor.getModel());
        action.setCursorBefore(editor.getModel().getCursorLocation());
        action.setLinesBefore(editor.getModel());
        if(editor.getModel().getSelectionRange().getEnd().equals(editor.getModel().getSelectionRange().getStart()))
            editor.getModel().deleteAfter();
        else
            editor.getModel().deleteRange(editor.getModel().getSelectionRange());
        editor.repaint();
        action.setCursoreAfter(editor.getModel().getCursorLocation());
        action.setLinesAfter(editor.getModel());
        editor.getModel().getUndoManager().push(action);
    }



}
