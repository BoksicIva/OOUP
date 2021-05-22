package labos_03.task2.all.action.moving;

import labos_03.task2.all.TextEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LeftAtion extends AbstractAction {
    private TextEditor editor;

    public LeftAtion(TextEditor editor){
        super("LEFT");
        this.editor=editor;
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("LEFT"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        editor.getModel().moveCursorLeft();
        editor.repaint();
    }
}
