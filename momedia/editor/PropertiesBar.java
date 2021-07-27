/*
 * PropertiesBar.java
 *
 * Created on 09 April 2002, 21:34
 */

package momedia.editor;

/**
 *
 * @author  Morgan Evans
 */
public class PropertiesBar extends javax.swing.JToolBar {
    final private javax.swing.JComboBox cmb_categories;
    final private java.awt.Container cnt_fieldsarea;
    
    /** Creates a new instance of PropertiesBar */
    public PropertiesBar() {
        super("Properties Bar",javax.swing.JToolBar.HORIZONTAL);
        setFloatable(true);
        cmb_categories = new javax.swing.JComboBox();
        cmb_categories.addItem("Position / Dimensions");
        cmb_categories.addItem("Colours / Line Style");
        add(cmb_categories);
        
        cnt_fieldsarea = new java.awt.Container();
        cnt_fieldsarea.add(new javax.swing.JLabel("Hello"));
        add(cnt_fieldsarea);
    }    
}