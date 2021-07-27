package momedia.editor;

public class SmallToStartButton extends momedia.editor.SmallButton {
    momedia.editor.Timeline timeline;
    
    public SmallToStartButton(momedia.editor.Timeline _timeline) {
        super(new javax.swing.ImageIcon(".\\icons\\tostart_sm.gif"));
        timeline = _timeline;
        
        addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeline.setCurrentFrame(timeline.getMinimumFrame());
            }
        });
    }
}
