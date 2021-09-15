package labos_04.actions;

import labos_04.DocumentModel;
import labos_04.GUI;
import labos_04.grafical_object.CompositeShape;
import labos_04.grafical_object.GraphicalObject;
import labos_04.grafical_object.LineSegment;
import labos_04.grafical_object.Oval;
import labos_04.state.AddShapeState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import static java.util.Map.entry;

public class OpenAction extends AbstractAction {
    private GUI gui;
    private DocumentModel model;
    private Map<String,GraphicalObject> objects= Map.ofEntries(entry("@OVAL",new Oval()), entry("@LINE",new LineSegment()), entry("@COMP",new CompositeShape(new ArrayList<>(),false)));

    public OpenAction(String name, GUI gui, DocumentModel model){
        super(name);
        this.gui=gui;
        this.model=model;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Stack<GraphicalObject> stack=new Stack<>();
        JFileChooser fc = new JFileChooser();
        String text="";
        fc.setDialogTitle("Open file");
        if(fc.showOpenDialog(gui)!=JFileChooser.APPROVE_OPTION) {
            return;
        }
        File fileName = fc.getSelectedFile();
        Path filePath = fileName.toPath();
        if(!Files.isReadable(filePath)) {
            JOptionPane.showMessageDialog(
                    gui,
                    "File "+fileName.getAbsolutePath()+" does not exist!",
                    "Warning",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            Scanner myReader = new Scanner(fileName);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataStrip=data.split(" ");
                objects.get(dataStrip[0]).load(stack,data);
            }
            myReader.close();
        } catch (FileNotFoundException a) {
            System.out.println("An error occurred.");
            a.printStackTrace();
        }


        model.clear();
        for (GraphicalObject o : stack) {
            model.addGraphicalObject(o);
        }

    }

}
