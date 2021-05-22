package labos_03.task2.all.action;

import labos_03.task2.all.TextEditor;
import labos_03.task2.all.TextEditorModel;
import labos_03.task2.all.UndoManager;
import labos_03.task2.all.location.Location;

import java.util.ArrayList;
import java.util.List;

public class UndoManagerAction implements EditAction{
    private TextEditorModel model;
    private List<String> linesBefore;
    private List<String> linesAfter;
    private Location cursorBefore;
    private Location cursoreAfter;

    public UndoManagerAction(TextEditorModel model){
        this.model=model;
    }

    public void setLinesBefore(TextEditorModel modelBefore) {
        this.linesBefore = new ArrayList<>(modelBefore.getLines());
    }

    public void setLinesAfter(TextEditorModel modelAfter) {
        this.linesAfter =new ArrayList<>(modelAfter.getLines());
    }

    public void setCursorBefore(Location cursorBefore) {
        this.cursorBefore = cursorBefore;
    }

    public void setCursoreAfter(Location cursoreAfter) {
        this.cursoreAfter = cursoreAfter;
    }

    @Override
    public void executeDo() {
        model.clear();
        model.setLines(linesAfter);
        model.setCursorLocation(cursoreAfter);
    }

    @Override
    public void executeUndo() {
        model.clear();
        model.setLines(linesBefore);
        model.setCursorLocation(cursorBefore);
    }
}
