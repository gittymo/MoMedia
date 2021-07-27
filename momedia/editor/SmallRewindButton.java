package momedia.editor;

public class SmallRewindButton extends momedia.editor.SmallButton {
    momedia.editor.Timeline timeline;
    
    public SmallRewindButton(momedia.editor.Timeline _timeline) {
        super(new javax.swing.ImageIcon(".\\icons\\rewind_sm.gif"));
        timeline = _timeline;
        
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (timeline.getCurrentFrame() > timeline.getMinimumFrame()) {
                    timeline.setCurrentFrame(timeline.getCurrentFrame() - 1);
                }
            }
        });
    }
}
