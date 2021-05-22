package labos_03.task2.all.action.selection;

import labos_03.task2.all.TextEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ShiftUpAction extends AbstractAction {
    private TextEditor editor;

    public ShiftUpAction(TextEditor editor){
        super("ShiftUp");
        this.editor=editor;
        //putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift UP"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        editor.getModel().shiftUp();
    }
}
