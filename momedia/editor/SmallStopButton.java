package momedia.editor;

public class SmallStopButton extends momedia.editor.SmallButton {
    momedia.editor.Timeline timeline;
    
    public SmallStopButton(momedia.editor.Timeline _timeline) {
        super(new javax.swing.ImageIcon(".\\icons\\stop_sm.gif"));
        timeline = _timeline;
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                timeline.stopPreview();
            }
        });
    }
}
