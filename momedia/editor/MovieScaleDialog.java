/*
 * MovieScaleDialog.java
 *
 * Created on 17 April 2002, 09:05
 */

package momedia.editor;

/**
 *
 * @author  Morgan Evans
 */
public class MovieScaleDialog extends javax.swing.JDialog {
    public static int OK_OPTION = 1;
    public static int CANCEL_OPTION = 2;
    
    private javax.swing.JTextField value;
    private int lastGoodValue = 100;
    private javax.swing.JDialog thisWindow;
    private int userChoice = this.CANCEL_OPTION;
    private javax.swing.JButton btnOK, btnCancel;
    
    /** Creates a new instance of MovieScaleDialog */
    public MovieScaleDialog(momedia.editor.Editor appwindow) {
        super(appwindow,"Scale View",true);
        java.awt.Container container = this.getContentPane();
        container.setLayout(new java.awt.BorderLayout(8,8));
        
        thisWindow = this;
        
        container.add(new javax.swing.JLabel("Scale editor document to:",javax.swing.JLabel.CENTER),java.awt.BorderLayout.NORTH);
        
        value = new javax.swing.JTextField(Integer.toString(lastGoodValue));
        value.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                try {
                    int givenvalue = Integer.parseInt(value.getText());
                    if (givenvalue < 10) {
                        javax.swing.JOptionPane.showMessageDialog(thisWindow,"Scale must be 10% or more");
                        value.setText(Integer.toString(lastGoodValue));
                        value.selectAll();
                    } else {
                        lastGoodValue = givenvalue;
                    }
                } catch (java.lang.NumberFormatException nfe) {
                    javax.swing.JOptionPane.showMessageDialog(thisWindow,"Scale must be a positive whole number and at least 10%");
                    value.setText(Integer.toString(lastGoodValue));
                    value.selectAll();
                }
            }
            
            public void focusGained(java.awt.event.FocusEvent evt) {
                value.selectAll();
            }
        });
        container.add(value,java.awt.BorderLayout.CENTER);
        value.selectAll();
        
        btnOK = new javax.swing.JButton("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boolean valueok = true;
                int givenvalue = -1;
                try {
                    givenvalue = Integer.parseInt(value.getText());
                } catch (NumberFormatException nfe) {
                    valueok = false;
                    javax.swing.JOptionPane.showMessageDialog(thisWindow,"Scale must be a positive whole number and at least 10%");
                    value.setText(Integer.toString(lastGoodValue));
                    value.selectAll();
                }
                if (valueok) {
                    if (givenvalue < 10) {
                        valueok = false;
                        javax.swing.JOptionPane.showMessageDialog(thisWindow,"Scale must be 10% or more");
                        value.setText(Integer.toString(lastGoodValue));
                        value.selectAll();
                    } else {
                        lastGoodValue = givenvalue;
                    }
                }
                if (valueok) {
                    userChoice = OK_OPTION;
                    setVisible(false);
                    dispose();
                }
            }
        });
        btnOK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char key = evt.getKeyChar();
                if (key == evt.VK_ENTER) {
                    userChoice = OK_OPTION;
                    setVisible(false);
                    dispose();
                }
            }
        });
        
        btnCancel = new javax.swing.JButton("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userChoice = CANCEL_OPTION;
                setVisible(false);
                dispose();
            }
        });
        btnCancel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char key = evt.getKeyChar();
                if (key == evt.VK_ENTER) {
                    userChoice = CANCEL_OPTION;
                    setVisible(false);
                    dispose();
                }
            }
        });
        
        java.awt.Container actbut = new java.awt.Container();
        actbut.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER,4,4));
        actbut.add(btnOK);
        actbut.add(btnCancel);
        container.add(actbut,java.awt.BorderLayout.SOUTH);
       
        pack();
    }
    
    public int getUserChoice() {
        return userChoice;
    }
    
    public int getScale() {
        return lastGoodValue;
    }
}
