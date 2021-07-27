package momedia.editor;

public class SmallFrameTextField extends javax.swing.JTextField {
    momedia.editor.Timeline timeline;
    
    public SmallFrameTextField(momedia.editor.Timeline _timeline) throws momedia.editor.InvalidFrameNumberException {
        timeline = _timeline;
        
        setPreferredSize(new java.awt.Dimension(48,20));
        setMinimumSize(new java.awt.Dimension(48,20));
        setMaximumSize(new java.awt.Dimension(48,20));
        setText(Integer.toString(timeline.getCurrentFrame()));
        
        addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                momedia.editor.SmallFrameTextField source = (momedia.editor.SmallFrameTextField) evt.getSource();
                try {
                    int givenvalue = Integer.parseInt(source.getText());
                    if (givenvalue >= timeline.getMinimumFrame() && givenvalue <= timeline.getMaximumFrame()) {
                        timeline.setCurrentFrame(givenvalue);
                    } else {
                        source.setText(Integer.toString(timeline.getCurrentFrame()));
                        source.selectAll();
                    }
                } catch (java.lang.NumberFormatException nfe) {
                    source.setText(Integer.toString(timeline.getCurrentFrame()));
                    source.selectAll();
                }
            }
        });
        
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                momedia.editor.SmallFrameTextField source = (momedia.editor.SmallFrameTextField) evt.getSource();
                try {
                    int givenvalue = Integer.parseInt(source.getText());
                    if (givenvalue >= timeline.getMinimumFrame() && givenvalue <= timeline.getMaximumFrame()) {
                        timeline.setCurrentFrame(givenvalue);
                    } else {
                        source.setText(Integer.toString(timeline.getCurrentFrame()));
                        source.selectAll();
                    }
                } catch (java.lang.NumberFormatException nfe) {
                    source.setText(Integer.toString(timeline.getCurrentFrame()));
                    source.selectAll();
                }
            }
        });
    }
    
    public void setCurrentFrame(int _framenumber) {
        setText(Integer.toString(_framenumber));
    }
}
