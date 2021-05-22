package labos_03.task2.all.action.stack;

import labos_03.task2.all.TextEditor;
import labos_03.task2.all.TextEditorFrame;
import labos_03.task2.all.observers.SelectionObserverForButtens;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CutAction extends AbstractAction  implements SelectionObserverForButtens {
    private TextEditorFrame frame;

    public CutAction(TextEditorFrame frame){
        super("Cut");
        this.frame=frame;
        this.frame.getEditor().getModel().addSelectionObserverForButtens(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!frame.getEditor().getModel().getSelectionRange().getStart().equals(frame.getEditor().getModel().getSelectionRange().getEnd())) {
            frame.getStack().push(frame.getEditor().getModel().selectedText());
            frame.getEditor().getModel().deleteRange(frame.getEditor().getModel().getSelectionRange());
        }
    }

    @Override
    public void updateButtons() {
        boolean empty=frame.getEditor().getModel().getSelectionRange().getStart().equals(frame.getEditor().getModel().getSelectionRange().getEnd());
        frame.getEnableCut().setEnable(empty);
    }
}
