package labos_03.task2.all.action;

import labos_03.task2.all.TextEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BackspaceAction extends AbstractAction {
    private TextEditor editor;

    public BackspaceAction(TextEditor editor){
        super("BackspaceAction");
        this.editor=editor;
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("BACK_SPACE"));


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        UndoManagerAction action=new UndoManagerAction(editor.getModel());
        action.setCursorBefore(editor.getModel().getCursorLocation());
        action.setLinesBefore(editor.getModel());
        if(editor.getModel().getSelectionRange().getEnd().equals(editor.getModel().getSelectionRange().getStart()))
            editor.getModel().deleteBefore();
        else
            editor.getModel().deleteRange(editor.getModel().getSelectionRange());
        editor.repaint();
        action.setCursoreAfter(editor.getModel().getCursorLocation());
        action.setLinesAfter(editor.getModel());
        editor.getModel().getUndoManager().push(action);
    }


}
