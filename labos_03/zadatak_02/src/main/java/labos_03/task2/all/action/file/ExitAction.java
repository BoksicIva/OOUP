package labos_03.task2.all.action.file;

import labos_03.task2.all.TextEditor;
import labos_03.task2.all.TextEditorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ExitAction extends AbstractAction {
    private TextEditorFrame frame;

    public ExitAction(TextEditorFrame frame){
        super("Exit");
        this.frame=frame;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
    }
}
