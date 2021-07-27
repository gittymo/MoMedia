package momedia.editor;

public class SmallForwardButton extends momedia.editor.SmallButton {
    momedia.editor.Timeline timeline;
    
    public SmallForwardButton(momedia.editor.Timeline _timeline) {
        super(new javax.swing.ImageIcon(".\\icons\\fforward_sm.gif"));
        timeline = _timeline;
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (timeline.getCurrentFrame() < timeline.getMaximumFrame()) {
                    timeline.setCurrentFrame(timeline.getCurrentFrame() + 1);
                }
            }
        });
    }
}
