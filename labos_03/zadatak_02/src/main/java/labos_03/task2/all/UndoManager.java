package labos_03.task2.all;

import labos_03.task2.all.action.EditAction;
import labos_03.task2.all.observers.UndoManagerObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UndoManager {
    private static UndoManager undoManager;

    private Stack<EditAction> undoStack;
    private Stack<EditAction> redoStack;
    private List<UndoManagerObserver> undoObservers;
    private List<UndoManagerObserver> redoObservers;

    private UndoManager(){
        undoStack=new Stack<>();
        redoStack=new Stack<>();
        undoObservers=new ArrayList<>();
        redoObservers=new ArrayList<>();
    }

    public static UndoManager getInstance() {
        if (undoManager == null) {
            undoManager = new UndoManager();
        }
        return undoManager;
    }

    public Stack<EditAction> getUndoStack() {
        return undoStack;
    }

    public Stack<EditAction> getRedoStack() {
        return redoStack;
    }

    public void undo() {
        if (undoStack.isEmpty())
            return;

        EditAction action = undoStack.pop();
        redoStack.push(action);
        action.executeDo();
        notifyRedoObservers();

        if(undoStack.isEmpty())
            notifyUndoObservers();
    }

    public void redo() {
        if (redoStack.isEmpty())
            return;

        EditAction action = redoStack.pop();
        undoStack.push(action);
        action.executeDo();
        notifyUndoObservers();

        if(redoStack.isEmpty())
            notifyRedoObservers();
    }


    public EditAction push(EditAction action) {
        undoStack.push(action);
        redoStack.clear();

        notifyRedoObservers();
        return action;
    }

    public boolean isRedoEmpty() {
        return this.redoStack.isEmpty();
    }

    public boolean isUndoEmpty() {
        return this.undoStack.isEmpty();
    }


    public void addUndoObserver(UndoManagerObserver observer) {
        undoObservers.add(observer);
    }

    public void removeUnodObserver(UndoManagerObserver observer) {
        undoObservers.remove(observer);
    }

    public void notifyUndoObservers() {
        for (UndoManagerObserver observer : undoObservers) {
            observer.update();
        }
    }

    public void addRedoObserver(UndoManagerObserver observer) {
        redoObservers.add(observer);
    }

    public void removeRedoObserver(UndoManagerObserver observer) {
        redoObservers.remove(observer);
    }

    public void notifyRedoObservers() {
        for (UndoManagerObserver observer : redoObservers) {
            observer.update();
        }
    }

}
