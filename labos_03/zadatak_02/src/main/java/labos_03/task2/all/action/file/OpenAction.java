package labos_03.task2.all.action.file;

import labos_03.task2.all.TextEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class OpenAction extends AbstractAction {
    private TextEditor editor;

    public OpenAction(TextEditor editor){
        super("Open");
        this.editor=editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        String text="";
        fc.setDialogTitle("Open file");
        if(fc.showOpenDialog(editor)!=JFileChooser.APPROVE_OPTION) {
            return;
        }
        File fileName = fc.getSelectedFile();
        Path filePath = fileName.toPath();
        if(!Files.isReadable(filePath)) {
            JOptionPane.showMessageDialog(
                    editor,
                    "File "+fileName.getAbsolutePath()+" does not exist!",
                    "Warning",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Scanner myReader = new Scanner(fileName);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                text+=data+"\n";
            }
            myReader.close();
        } catch (FileNotFoundException a) {
            System.out.println("An error occurred.");
            a.printStackTrace();
        }

        editor.getModel().clear();
        editor.getModel().insert(text);
    }
}
