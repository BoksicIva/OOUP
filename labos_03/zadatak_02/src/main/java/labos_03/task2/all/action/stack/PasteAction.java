package labos_03.task2.all.action.stack;

import labos_03.task2.all.TextEditor;
import labos_03.task2.all.TextEditorFrame;
import labos_03.task2.all.action.InsertTextAction;
import labos_03.task2.all.observers.ClipboardStackObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PasteAction extends AbstractAction implements ClipboardStackObserver {
    private TextEditorFrame frame;
    private InsertTextAction insertTextAction;

    public PasteAction(TextEditorFrame frame){
        super("Paste");
        this.frame=frame;
        this.frame.getStack().addClipboardObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(frame.getStack().empty())
            return;
        frame.getEditor().getModel().insert( frame.getStack().peek());
        insertTextAction=new InsertTextAction(frame.getEditor(),frame.getStack().peek());
    }

    @Override
    public void updateClipboardStack() {
        boolean empty=frame.getStack().empty();
        frame.getEnablePaste().setEnable(empty);
    }
}
