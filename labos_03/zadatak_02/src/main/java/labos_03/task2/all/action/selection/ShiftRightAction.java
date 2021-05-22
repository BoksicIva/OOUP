package labos_03.task2.all.action.selection;

import labos_03.task2.all.TextEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ShiftRightAction extends AbstractAction {
    private TextEditor editor;

    public ShiftRightAction(TextEditor editor){
        super("ShiftRight");
        this.editor=editor;
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift RIGHT"));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        editor.getModel().shiftRight();
    }
}
