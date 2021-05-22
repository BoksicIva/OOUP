package labos_03.task2.all.action.selection;

import labos_03.task2.all.TextEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ShiftLeftAction extends AbstractAction {

    private TextEditor editor;

    public ShiftLeftAction(TextEditor editor){
        super("ShiftLeft");
        this.editor=editor;
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift LEFT"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        editor.getModel().shiftLeft();
    }
}
