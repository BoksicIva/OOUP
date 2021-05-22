package labos_03.task2.all.plugin;

import labos_03.task2.all.TextEditorModel;
import labos_03.task2.all.UndoManager;
import labos_03.task2.all.stack.ClipboardStack;

public interface Plugin {

    String getName(); // ime plugina (za izbornicku stavku)
    String getDescription(); // kratki opis
    void execute(TextEditorModel model, UndoManager undoManager, ClipboardStack clipboardStack);

}
