package momedia.editor;

public class SmallButton extends javax.swing.JButton {
    public SmallButton(javax.swing.ImageIcon _icon) {
        setBorder(new javax.swing.border.EmptyBorder(2,1,2,1));
        setIcon(_icon);
        setPreferredSize(new java.awt.Dimension(20,20));
        setMaximumSize(new java.awt.Dimension(20,20));
        setMinimumSize(new java.awt.Dimension(20,20));
    }
}