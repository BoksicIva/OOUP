package labos_04.actions;

import labos_04.DocumentModel;
import labos_04.GUI;
import labos_04.grafical_object.GraphicalObject;
import labos_04.renderer.SVGRendererImpl;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SaveAction extends AbstractAction {
    private GUI gui;
    private DocumentModel model;

    public SaveAction(String name, GUI gui, DocumentModel model){
        super(name);
        this.gui=gui;
        this.model=model;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setSelectedFile(new File("myfile.txt"));
        jfc.setFileFilter(new FileNameExtensionFilter("txt file",".txt"));
        jfc.setDialogTitle("Save picture as ");
        if(jfc.showSaveDialog(gui)!=JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(
                    gui,
                    "Nothing saved.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String filename=jfc.getSelectedFile().toString();
        if (!filename.endsWith(".txt"))
            filename += ".txt";
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            List<String> rows=new ArrayList<>();
            for(GraphicalObject object:model.list()){
                object.save(rows);
            }
            rows.forEach(l->{
                try {
                    bw.write(l);
                    bw.newLine();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        } catch (IllegalArgumentException | IOException e1) {
            JOptionPane.showMessageDialog(
                    gui,
                    "Error while saving. Try again",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(
                gui,
                "File is saved.",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
