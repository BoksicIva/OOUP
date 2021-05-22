package labos_03.task2.all.action.file;

import labos_03.task2.all.TextEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ClearAction extends AbstractAction {
    private TextEditor editor;

    public ClearAction(TextEditor editor){
        super("Clear");
        this.editor=editor;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        editor.getModel().clear();
    }
}
