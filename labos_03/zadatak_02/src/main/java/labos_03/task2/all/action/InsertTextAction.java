package labos_03.task2.all.action;

import labos_03.task2.all.TextEditor;
import labos_03.task2.all.location.Location;
import labos_03.task2.all.location.LocationRange;

public class InsertTextAction {
    private LocationRange range;
    private Location cursorLocation;
    private TextEditor editor;

    public InsertTextAction(TextEditor editor,String text){
        UndoManagerAction action=new UndoManagerAction(editor.getModel());
        action.setCursorBefore(editor.getModel().getCursorLocation());
        action.setLinesBefore(editor.getModel());

        action.setCursoreAfter(editor.getModel().getCursorLocation());
        action.setLinesAfter(editor.getModel());
        editor.getModel().getUndoManager().push(action);

    }
    public InsertTextAction(TextEditor editor,char character){
        UndoManagerAction action=new UndoManagerAction(editor.getModel());
        action.setCursorBefore(editor.getModel().getCursorLocation());
        action.setLinesBefore(editor.getModel());

        action.setCursoreAfter(editor.getModel().getCursorLocation());
        action.setLinesAfter(editor.getModel());
        editor.getModel().getUndoManager().push(action);
    }


}
