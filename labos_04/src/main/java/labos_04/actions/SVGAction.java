package labos_04.actions;

import labos_04.DocumentModel;
import labos_04.GUI;
import labos_04.renderer.SVGRendererImpl;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class SVGAction extends AbstractAction {
    private GUI gui;
    private DocumentModel model;

    public SVGAction(String name, GUI gui, DocumentModel model){
        super(name);
        this.gui=gui;
        this.model=model;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setSelectedFile(new File("myfile.svg"));
        jfc.setFileFilter(new FileNameExtensionFilter("svg file",".svg"));
        jfc.setDialogTitle("Save picture as ");
        if(jfc.showSaveDialog(gui)!=JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(
                    gui,
                    "Nothing saved.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Path openedFilePath = jfc.getSelectedFile().toPath();
        String filename=jfc.getSelectedFile().toString();
        if (!filename.endsWith(".txt"))
            filename += ".svg";
        try {
            SVGRendererImpl svgRenderer=new SVGRendererImpl(filename);
            model.list().forEach(o->o.render(svgRenderer));
            svgRenderer.close();
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
