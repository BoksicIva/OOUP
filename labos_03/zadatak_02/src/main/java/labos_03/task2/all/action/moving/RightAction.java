package labos_03.task2.all.action.moving;

import labos_03.task2.all.TextEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RightAction extends AbstractAction {
    private TextEditor editor;

    public RightAction(TextEditor editor){
        super("UP");
        this.editor=editor;
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("RIGHT"));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        editor.getModel().moveCursorRight();
        editor.repaint();
    }
}
