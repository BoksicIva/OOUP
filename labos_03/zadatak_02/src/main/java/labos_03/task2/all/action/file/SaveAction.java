package labos_03.task2.all.action.file;

import labos_03.task2.all.TextEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SaveAction extends AbstractAction {
    private TextEditor editor;

    public SaveAction(TextEditor editor){
        super("Save");
        this.editor=editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Save document as ");
        if(jfc.showSaveDialog(editor)!=JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(
                    editor,
                    "Nothing saved.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Path openedFilePath = jfc.getSelectedFile().toPath();

        try {
            Files.write(openedFilePath,editor.getModel().getLines());
        } catch (IllegalArgumentException | IOException e1) {
            JOptionPane.showMessageDialog(
                    editor,
                    "Error while saving. Try again",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(
                editor,
                "File is saved.",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);

    }
}
