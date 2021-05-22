package labos_03.task2.all.action.moving;

import labos_03.task2.all.TextEditor;
import labos_03.task2.all.location.Location;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CursorStartAction extends AbstractAction {
    private TextEditor editor;

    public CursorStartAction(TextEditor editor){
        super("Cursor to document start");
        this.editor=editor;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Location location = editor.getModel().getCursorLocation();
        location.setRow(0);
        location.setColumn(0);
        editor.getModel().notifyCursorObservers();

    }
}
