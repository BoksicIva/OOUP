package labos_03.task2.all;

import labos_03.task2.all.action.*;
import labos_03.task2.all.action.file.ClearAction;
import labos_03.task2.all.action.file.ExitAction;
import labos_03.task2.all.action.file.OpenAction;
import labos_03.task2.all.action.file.SaveAction;
import labos_03.task2.all.action.moving.*;
import labos_03.task2.all.action.selection.ShiftDownAction;
import labos_03.task2.all.action.selection.ShiftLeftAction;
import labos_03.task2.all.action.selection.ShiftRightAction;
import labos_03.task2.all.action.selection.ShiftUpAction;
import labos_03.task2.all.action.stack.CopyAction;
import labos_03.task2.all.action.stack.CutAction;
import labos_03.task2.all.action.stack.PasteAction;
import labos_03.task2.all.action.stack.PasteAndTakeAction;
import labos_03.task2.all.action.undomanager.RedoAction;
import labos_03.task2.all.action.undomanager.UndoAction;
import labos_03.task2.all.components.TextEditorStatusBar;
import labos_03.task2.all.observers.UndoManagerObserver;
import labos_03.task2.all.stack.ClipboardStack;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;


public class TextEditorFrame extends JFrame implements UndoManagerObserver {

    private CopyAction copyAction;
    private CutAction cutAction;
    private ClearAction clearAction;
    private DeleteAction deleteAction;
    private ExitAction exitAction;
    private OpenAction openAction;
    private PasteAction pasteAction;
    private PasteAndTakeAction pasteAndTakeAction;
    private RedoAction redoAction;
    private SaveAction saveAction;
    private UndoAction undoAction;
    private CursorEndAction cursorEnd;
    private CursorStartAction cursorStart;

    private final TextEditorModel model;
    private final TextEditor editor ;

    private ClipboardStack stack = new ClipboardStack();

    private JButton redo,undo,cut,copy,paste,pasteAndTake;

    private EnableAction enableRedo,enableUndo,enableCut,enableCopy,enablePaste,enablePasteAndTake;

    public TextEditorFrame(String text){
        this.model=new TextEditorModel(text);
        this.editor=new TextEditor(model);
        this.model.getUndoManager().addRedoObserver(this);
        this.model.getUndoManager().addUndoObserver(this);
        setTitle("Text editor");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(0, 0);
        initGUI();

        setMinimumSize(new Dimension(600,600));
    }

    private void initGUI() {
        this.getContentPane().setLayout(new BorderLayout());

        editor.setFocusable(true);
        editor.setRequestFocusEnabled(true);
        editor.requestFocusInWindow();


        JScrollPane editorPane = new JScrollPane(editor,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        add(editorPane, BorderLayout.CENTER);


        createActions();
        createMenus();
        createToolbars();
        createStatusBar();
        createEnableActions();


    }

    public EnableAction getEnableRedo() {
        return enableRedo;
    }

    public EnableAction getEnableUndo() {
        return enableUndo;
    }

    public EnableAction getEnableCut() {
        return enableCut;
    }

    public EnableAction getEnableCopy() {
        return enableCopy;
    }

    public EnableAction getEnablePaste() {
        return enablePaste;
    }

    public EnableAction getEnablePasteAndTake() {
        return enablePasteAndTake;
    }

    public void setStack(ClipboardStack stack) {
        this.stack = stack;
    }


    public ClipboardStack getStack() {
        return stack;
    }

    public TextEditor getEditor() {
        return editor;
    }

    private void createActions(){
        this.copyAction=new CopyAction(this);
        this.cutAction=new CutAction(this);
        this.clearAction=new ClearAction(editor);
        this.deleteAction=new DeleteAction(editor);
        this.exitAction=new ExitAction(this);
        this.openAction=new OpenAction(editor);
        this.pasteAction=new PasteAction(this);
        this.pasteAndTakeAction=new PasteAndTakeAction(this);
        this.redoAction=new RedoAction(this);
        this.saveAction=new SaveAction(editor);
        this.undoAction=new UndoAction(this);
        this.cursorStart=new CursorStartAction(editor);
        this.cursorEnd=new CursorEndAction(editor);

        editor.getInputMap().put(KeyStroke.getKeyStroke("control C"), "copyAction");
        editor.getActionMap().put("copyAction",copyAction);

        editor.getInputMap().put(KeyStroke.getKeyStroke("control X"), "cutAction");
        editor.getActionMap().put("cutAction",cutAction);

        editor.getInputMap().put(KeyStroke.getKeyStroke("control V"), "pasteAction");
        editor.getActionMap().put("pasteAction",pasteAction);

        editor.getInputMap().put(KeyStroke.getKeyStroke("control shift V"), "pasteAndTakeAction");
        editor.getActionMap().put("pasteAndTakeAction",pasteAndTakeAction);

        editor.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"), "backspace");
        editor.getActionMap().put("backspace", new BackspaceAction(editor));

        editor.getInputMap().put(KeyStroke.getKeyStroke("DELETE"), "delete");
        editor.getActionMap().put("delete", new DeleteAction(editor));

        editor.getInputMap().put(KeyStroke.getKeyStroke("UP"), "up");
        editor.getActionMap().put("up", new UpAction(editor));

        editor.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "right");
        editor.getActionMap().put("right", new RightAction(editor));

        editor.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "left");
        editor.getActionMap().put("left", new LeftAtion(editor));

        editor.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "down");
        editor.getActionMap().put("down", new DownAction(editor));

        editor.getInputMap().put(KeyStroke.getKeyStroke("shift DOWN"), "shiftdown");
        editor.getActionMap().put("shiftdown", new ShiftDownAction(editor));

        editor.getInputMap().put(KeyStroke.getKeyStroke("shift UP"), "shiftup");
        editor.getActionMap().put("shiftup", new ShiftUpAction(editor));

        editor.getInputMap().put(KeyStroke.getKeyStroke("shift LEFT"), "shiftleft");
        editor.getActionMap().put("shiftleft", new ShiftLeftAction(editor));

        editor.getInputMap().put(KeyStroke.getKeyStroke("shift RIGHT"), "shiftright");
        editor.getActionMap().put("shiftright", new ShiftRightAction(editor));

        editor.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "undoAction");
        editor.getActionMap().put("undoAction", undoAction);

        editor.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "redoActiont");
        editor.getActionMap().put("redoAction", redoAction);

        copyAction.setEnabled(false);
        cutAction.setEnabled(false);
        pasteAction.setEnabled(false);
        redoAction.setEnabled(false);
        undoAction.setEnabled(false);
        pasteAndTakeAction.setEnabled(false);

    }


    private void createMenus() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        fileMenu.add(new JMenuItem(openAction));
        fileMenu.add(new JMenuItem(saveAction));
        fileMenu.add(new JMenuItem(exitAction));



        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        editMenu.add(new JMenuItem(undoAction));
        editMenu.add(new JMenuItem(redoAction));
        editMenu.add(new JMenuItem(cutAction));
        editMenu.add(new JMenuItem(copyAction));
        editMenu.add(new JMenuItem(pasteAction));
        editMenu.add(new JMenuItem(pasteAndTakeAction));
        editMenu.add(new JMenuItem(deleteAction));
        editMenu.add(new JMenuItem(clearAction));



        JMenu moveMenu = new JMenu("Move");
        menuBar.add(moveMenu);

        moveMenu.add(new JMenuItem(cursorStart));
        moveMenu.add(new JMenuItem(cursorEnd));



        JMenu pluginMenu = new JMenu("Plugins");
        menuBar.add(pluginMenu);

        createAndAddPlugins(pluginMenu);

        this.setJMenuBar(menuBar);
    }

    private void createAndAddPlugins(JMenu pluginMenu){
        String path = "C:\\FER\\OOUP - java intelli\\src\\main\\java\\labos_03\\task2\\all\\plugin\\implementation";

        File pluginsImplementationsFile=new File(path);

        for(File file : pluginsImplementationsFile.listFiles()){
            PluginAction pluginAction = null;
            String pluginName=file.getName().substring(0,file.getName().length()-5);
            try {
                pluginAction=new PluginAction(this,pluginName);
            } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                System.out.println("Plugin not found");
            }
            pluginMenu.add(new JMenuItem(pluginAction));


        }
    }

    private void createToolbars() {
        JToolBar toolBar = new JToolBar("Tools");
        toolBar.setFloatable(true);

        undo=new JButton(undoAction);
        undo.setFocusable(false);
        toolBar.add(undo);

        redo=new JButton(redoAction);
        redo.setFocusable(false);
        toolBar.add(redo);

        cut=new JButton(cutAction);
        cut.setFocusable(false);
        toolBar.add(cut);


        copy=new JButton(copyAction);
        copy.setFocusable(false);
        toolBar.add(copy);

        paste=new JButton(pasteAction);
        paste.setFocusable(false);
        toolBar.add(paste);

        pasteAndTake=new JButton(pasteAndTakeAction);
        pasteAndTake.setFocusable(false);
        toolBar.add(pasteAndTake);

        copy.setEnabled(false);
        cut.setEnabled(false);
        paste.setEnabled(false);
        pasteAndTake.setEnabled(false);
        redo.setEnabled(false);
        undo.setEnabled(false);


        this.getContentPane().add(toolBar, BorderLayout.PAGE_START);
    }

    private void createStatusBar(){
        TextEditorStatusBar statusBar=new TextEditorStatusBar(editor);
        add(statusBar,BorderLayout.SOUTH);
        model.addCursorObserver(statusBar);

    }

    private void createEnableActions() {
        enableCopy=new EnableAction(copy,copyAction);
        enableCut=new EnableAction(cut,cutAction);
        enablePaste=new EnableAction(paste,pasteAction);
        enableRedo=new EnableAction(redo,redoAction);
        enableUndo=new EnableAction(undo,undoAction);
        enablePasteAndTake=new EnableAction(pasteAndTake,pasteAndTakeAction);
    }



    public static void main(String... args) {
        String text="Neki random text.\nMora biti kroz više redaka\nProvjera radi li";
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new TextEditorFrame(text).setVisible(true);
            }
        });
    }

    @Override
    public void update() {
        repaint();
    }
}
