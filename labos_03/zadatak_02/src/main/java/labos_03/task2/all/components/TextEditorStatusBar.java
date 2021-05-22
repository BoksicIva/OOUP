package labos_03.task2.all.components;

import labos_03.task2.all.TextEditor;
import labos_03.task2.all.location.Location;
import labos_03.task2.all.observers.CursorObserver;
import labos_03.task2.all.observers.TextObserver;

import javax.swing.*;
import java.awt.*;

public class TextEditorStatusBar extends JToolBar implements CursorObserver {
    private TextEditor editor;
    private String title;

    private JLabel lines= new JLabel("Ln : 1");
    private JLabel columns= new JLabel("Col : 1");

    public TextEditorStatusBar(TextEditor editor){
        this.editor=editor;
        this.title="Info";

        setFloatable(false);
        setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));

        JPanel panel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY));

        panel.add(lines);
        panel.add(columns);

        this.add(panel);
    }




    @Override
    public void updateCursorLocation(Location loc) {
        lines.setText("Ln : "+(loc.getRow()+1));
        columns.setText("Col : "+(loc.getColumn()+1));
    }

}
