package labos_03.task2.all.action.selection;

import labos_03.task2.all.TextEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ShiftDownAction extends AbstractAction {
    private TextEditor editor;

    public ShiftDownAction(TextEditor editor){
        super("ShiftDown");
        this.editor=editor;
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift DOWN"));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        editor.getModel().shiftDown();
    }
}
