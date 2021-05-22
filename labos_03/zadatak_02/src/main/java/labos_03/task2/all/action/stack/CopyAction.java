package labos_03.task2.all.action.stack;

import labos_03.task2.all.TextEditorFrame;
import labos_03.task2.all.observers.SelectionObserverForButtens;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CopyAction extends AbstractAction implements SelectionObserverForButtens {
    private TextEditorFrame frame;

    public CopyAction(TextEditorFrame frame){
        super("Copy");
        this.frame=frame;
        this.frame.getEditor().getModel().addSelectionObserverForButtens(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!frame.getEditor().getModel().getSelectionRange().getStart().equals(frame.getEditor().getModel().getSelectionRange().getEnd())) {
            frame.getStack().push(frame.getEditor().getModel().selectedText());
        }
    }

    @Override
    public void updateButtons() {
        boolean empty=frame.getEditor().getModel().getSelectionRange().getStart().equals(frame.getEditor().getModel().getSelectionRange().getEnd());
        frame.getEnableCopy().setEnable(empty);
    }
}
