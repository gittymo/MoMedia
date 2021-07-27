/*
 * ObjectsPalette.java
 *
 * Created on 09 April 2002, 20:53
 */

package momedia.editor;

/**
 *
 * @author  Morgan Evans
 */
public class ObjectsPalette extends javax.swing.JInternalFrame {
    
    private int selectedicon;
    public static int NONE = 0;
    public static int OVAL = 1;
    public static int PATH = 2;
    
    private momedia.editor.ObjectButton btn_oval;
    private momedia.editor.ObjectButton btn_path;
    private javax.swing.ButtonGroup buttongroup;
    private javax.swing.JPanel sbc, lbc;    // Containers for large and small buttons respectively
    
    /** Creates a new instance of ObjectsPalette */
    public ObjectsPalette() {
        super("Objects",false,true,false,false);
        java.awt.Container container = this.getContentPane();
        container.setLayout(new java.awt.BorderLayout(4,4));
        buttongroup = new javax.swing.ButtonGroup();
        
        // Create the character object buttons
        sbc = new javax.swing.JPanel(new java.awt.GridLayout(1,2,2,2));
        btn_oval = new momedia.editor.ObjectButton(new javax.swing.ImageIcon(".\\icons\\oval.gif"),"Oval shape");
        sbc.add(btn_oval); buttongroup.add(btn_oval); btn_oval.setSelected(true);
        btn_path = new momedia.editor.ObjectButton(new javax.swing.ImageIcon(".\\icons\\animpath.gif"),"Animation path");
        sbc.add(btn_path); buttongroup.add(btn_path);
        container.add(sbc,java.awt.BorderLayout.NORTH);
        
        pack();
    }
    
    public int getSelectedIcon() {
        if (btn_oval.isSelected()) return OVAL;
        if (btn_path.isSelected()) return PATH;
        return NONE;
    }
    
    public void deselectAll() {
        for (int i = 0; i < sbc.getComponentCount(); i++) {
            momedia.editor.ObjectButton ob = (momedia.editor.ObjectButton) sbc.getComponent(i);
            ob.setSelected(false);
        }
    }
}

class ObjectButton extends javax.swing.JToggleButton {
    public ObjectButton(javax.swing.ImageIcon _icon,String _description) {
        super(_icon);
        setToolTipText(_description);
        setBorder(javax.swing.BorderFactory.createEmptyBorder(2,2,2,2));
    }
}