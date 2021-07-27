package momedia.editor;

public class SmallToEndButton extends momedia.editor.SmallButton {
    momedia.editor.Timeline timeline;
    
    public SmallToEndButton(momedia.editor.Timeline _timeline) {
        super(new javax.swing.ImageIcon(".\\icons\\toend_sm.gif"));
        timeline = _timeline;
        
        addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeline.setCurrentFrame(timeline.getMaximumFrame());
            }
        });
    }
}
