package labos_03.task2.all.action.stack;

import labos_03.task2.all.TextEditor;
import labos_03.task2.all.TextEditorFrame;
import labos_03.task2.all.observers.ClipboardStackObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PasteAndTakeAction extends AbstractAction implements ClipboardStackObserver {
    private TextEditorFrame frame;

    public PasteAndTakeAction(TextEditorFrame frame){
        super("Paste and take");
        this.frame=frame;
        this.frame.getStack().addClipboardObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(frame.getStack().empty()){
            updateClipboardStack();
            return;
        }
        frame.getEditor().getModel().insert(frame.getStack().pop());
    }

    @Override
    public void updateClipboardStack() {
        boolean empty=frame.getStack().empty();
        frame.getEnablePasteAndTake().setEnable(empty);
    }
}
