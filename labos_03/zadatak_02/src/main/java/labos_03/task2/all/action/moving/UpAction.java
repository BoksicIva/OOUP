package labos_03.task2.all.action.moving;

import labos_03.task2.all.TextEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UpAction extends AbstractAction {
    private TextEditor editor;

    public UpAction(TextEditor editor){
        super("UP");
        this.editor=editor;
        //putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        editor.getModel().moveCursorUp();
        editor.repaint();
    }
}
