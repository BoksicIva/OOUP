package labos_03.task2.all.action;

import labos_03.task2.all.TextEditor;
import labos_03.task2.all.TextEditorFrame;
import labos_03.task2.all.plugin.Plugin;
import labos_03.task2.all.plugin.PluginFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;

public class PluginAction extends AbstractAction {

    private TextEditorFrame frame;
    private Plugin plugin;

    public PluginAction(TextEditorFrame frame, String name) throws ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException, IllegalAccessException {
        super(name);
        this.frame=frame;
        this.plugin= PluginFactory.getIstance(name);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        plugin.execute(frame.getEditor().getModel(),frame.getEditor().getModel().getUndoManager(),frame.getStack());
    }
}
