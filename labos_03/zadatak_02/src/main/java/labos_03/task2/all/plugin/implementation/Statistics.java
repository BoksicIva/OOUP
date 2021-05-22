package labos_03.task2.all.plugin.implementation;

import labos_03.task2.all.TextEditorModel;
import labos_03.task2.all.UndoManager;
import labos_03.task2.all.plugin.Plugin;
import labos_03.task2.all.stack.ClipboardStack;

import javax.swing.*;
import java.util.List;

public class Statistics implements Plugin {
    private String name="Statistics";
    private String description="Plugin that gats statistics of open document.";



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
        List<String> lines=model.getLines();

        StringBuilder sb=new StringBuilder();

        int charsNum=lines.stream().mapToInt(p -> p.length()).sum();
        int linesNum=lines.size();
        long wordsNum=lines.stream().mapToInt(line -> line.split("\\s++").length).sum();

        sb.append("Statistics : \n");
        sb.append("_________________\n");
        sb.append("Lines : "+linesNum+"\n");
        sb.append("Words : "+wordsNum+"\n");
        sb.append("Characters(with spaces) : "+charsNum+"\n");

        JOptionPane.showMessageDialog(
                null,
                 sb,
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
