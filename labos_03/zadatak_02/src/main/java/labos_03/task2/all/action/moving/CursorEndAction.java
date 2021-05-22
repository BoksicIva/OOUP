package labos_03.task2.all.action.moving;

import labos_03.task2.all.TextEditor;
import labos_03.task2.all.location.Location;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CursorEndAction extends AbstractAction {
    private TextEditor editor;

    public CursorEndAction(TextEditor editor){
        super("Cursor to document end");
        this.editor=editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Location location = editor.getModel().getCursorLocation();
        List<String> lines=editor.getModel().getLines();
        location.setRow(lines.size()-1);
        location.setColumn(lines.get(lines.size()-1).length());
        editor.getModel().notifyCursorObservers();

    }
}
