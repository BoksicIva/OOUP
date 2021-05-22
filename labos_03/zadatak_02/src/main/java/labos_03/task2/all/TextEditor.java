package labos_03.task2.all;


import labos_03.task2.all.action.InsertTextAction;
import labos_03.task2.all.observers.CursorObserver;
import labos_03.task2.all.location.Location;
import labos_03.task2.all.location.LocationRange;
import labos_03.task2.all.observers.SelectionObserver;
import labos_03.task2.all.observers.TextObserver;
import labos_03.task2.all.stack.ClipboardStack;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TextEditor extends JComponent implements CursorObserver, KeyListener, TextObserver, SelectionObserver {
    private TextEditorModel model;
    private boolean shift = false;
    private boolean first = true;

    private InsertTextAction insertTextAction;

    private final int gap = 5;
    private final Font font = new Font(Font.MONOSPACED, Font.PLAIN, 15);
    private final Set<Integer> pressed = new HashSet<Integer>();



    public TextEditor(TextEditorModel model) {
        this.model = model;
        model.addCursorObserver(this);
        model.addTextObserver(this);
        setMinimumSize(new Dimension(600, 600));
        setEnabled(true);
        addKeyListener(this);
        model.addCursorObserver(this);
    }



    public TextEditorModel getModel() {
        return model;
    }

    public void setModel(TextEditorModel model) {
        this.model = model;
    }





    @Override
    public void updateCursorLocation(Location loc) {
        repaint();
    }

    @Override
    public void updateText() {
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char c = 0;
        int code = e.getKeyCode();
        pressed.add(e.getKeyCode());
        if (code != KeyEvent.VK_LEFT && code != KeyEvent.VK_RIGHT && code != KeyEvent.VK_UP && code != KeyEvent.VK_DOWN && code != KeyEvent.VK_SHIFT && code != KeyEvent.VK_BACK_SPACE && code != KeyEvent.VK_DELETE && code != KeyEvent.VK_CONTROL && !e.isControlDown() && !(pressed.contains(KeyEvent.VK_CONTROL) && (pressed.contains(KeyEvent.VK_C)|| pressed.contains(KeyEvent.VK_V) || pressed.contains(KeyEvent.VK_X) || pressed.contains(KeyEvent.VK_Z)|| pressed.contains(KeyEvent.VK_Y)|| pressed.contains(KeyEvent.VK_SHIFT)))) {
            c = e.getKeyChar();
            model.insert(c);
            insertTextAction=new InsertTextAction(this,c);
        }
        if (code == KeyEvent.VK_SHIFT) {
            model.setSelectionRange(new LocationRange(new Location(model.getCursorLocation().getRow(), model.getCursorLocation().getColumn()), new Location(model.getCursorLocation().getRow(), model.getCursorLocation().getColumn())));
            shift = true;
        }

        if (!e.isShiftDown() && code != KeyEvent.VK_BACK_SPACE && code != KeyEvent.VK_DELETE && code != KeyEvent.VK_CONTROL && !(pressed.contains(KeyEvent.VK_C)|| pressed.contains(KeyEvent.VK_V) || pressed.contains(KeyEvent.VK_X) || pressed.contains(KeyEvent.VK_SHIFT))) {
            model.setSelectionRange(new LocationRange(new Location(model.getCursorLocation().getRow(), model.getCursorLocation().getColumn()), new Location(model.getCursorLocation().getRow(), model.getCursorLocation().getColumn())));
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());
        if (pressed.contains(KeyEvent.VK_SHIFT) && (pressed.contains(KeyEvent.VK_LEFT) || pressed.contains(KeyEvent.VK_RIGHT) || pressed.contains(KeyEvent.VK_UP) || pressed.contains(KeyEvent.VK_DOWN)) ) {
            shift = false;
            first = true;
        }
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        drawSelected(g);
        drawText(g);
        drawCarret(g);
    }

    private void drawSelected(Graphics g) {
        LocationRange selectedRange = model.getSelectionRange();
        if (shift) {
            if (!selectedRange.getStart().equals(selectedRange.getEnd())) {
                selectedRange.replace(selectedRange);
                Location startRange = selectedRange.getStart();
                Location endRange = selectedRange.getEnd();
                for (int i = startRange.getRow(); i <= endRange.getRow(); i++) {
                    int x1, x2, y2, y1;
                    if (i == startRange.getRow() && i == endRange.getRow()) {
                        x1 = g.getFontMetrics().stringWidth(model.getLines().get(i).substring(0, startRange.getColumn()));
                        x2 = g.getFontMetrics().stringWidth(model.getLines().get(i).substring(startRange.getColumn(), endRange.getColumn()));
                    } else if (i == startRange.getRow()) {
                        x1 = g.getFontMetrics().stringWidth(model.getLines().get(i).substring(0, startRange.getColumn()));
                        x2 = g.getFontMetrics().stringWidth(model.getLines().get(i).substring(startRange.getColumn()));
                    } else if (i == endRange.getRow()) {
                        x1 = g.getFontMetrics().stringWidth(model.getLines().get(i).substring(0, 0));
                        x2 = g.getFontMetrics().stringWidth(model.getLines().get(i).substring(0, endRange.getColumn()));
                    } else {
                        x1 = g.getFontMetrics().stringWidth(model.getLines().get(i).substring(0, 0));
                        x2 = g.getFontMetrics().stringWidth(model.getLines().get(i));
                    }
                    y1 = i * (g.getFont().getSize() + gap) + gap;
                    y2 = (i + 1) * (g.getFont().getSize() + gap) + gap;

                    g.setColor(new Color(97, 200, 255));
                    g.fillRect(x1, y1, x2, y2 - y1);
                }
                g.setColor(Color.BLACK);
            }
        }
    }


    public void drawText(Graphics g) {
        Iterator<String> iterator = model.allLines();

        int y = 20;
        while (iterator.hasNext()) {
            String row = iterator.next();
            g.drawString(row, 0, y);
            y += g.getFont().getSize() + gap;
        }
    }

    public void drawCarret(Graphics g) {
        Location location = model.getCursorLocation();
        String row = model.getLines().get(location.getRow()).substring(0, location.getColumn());


        g.drawLine(g.getFontMetrics().stringWidth(row),
                (location.getRow() + 1) * (g.getFont().getSize() + gap) + gap,
                g.getFontMetrics().stringWidth(row),
                location.getRow() * (g.getFont().getSize() + gap) + gap
        );
    }

    @Override
    public void updateSelection() {
        repaint();
    }
}
