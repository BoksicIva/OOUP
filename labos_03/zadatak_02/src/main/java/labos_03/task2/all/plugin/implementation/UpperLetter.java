package labos_03.task2.all.plugin.implementation;

import labos_03.task2.all.TextEditorModel;
import labos_03.task2.all.UndoManager;
import labos_03.task2.all.plugin.Plugin;
import labos_03.task2.all.stack.ClipboardStack;

import java.util.List;

public class UpperLetter implements Plugin {
    private final String name="Upper letter";
    private final String description="Goes through file and changes all first letters of words in upper case..";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute(TextEditorModel model, UndoManager undoManager, ClipboardStack clipboardStack) {
        List<String> lines = model.getLines();
        for (int index = 0; index < lines.size(); index++) {
            String row = lines.get(index);
            String[] words = row.split("\\s+");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < words.length; i++) {
                if (i != 0) {
                    sb.append(' ');
                }
                sb.append(Character.toUpperCase(words[i].charAt(0)));
                sb.append(words[i].substring(1).toLowerCase());
            }
            lines.set(index, sb.toString());

        }
        model.setLines(lines);
        model.notifyTextObservers();
    }
}
