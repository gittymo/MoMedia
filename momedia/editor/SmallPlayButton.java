package momedia.editor;

public class SmallPlayButton extends momedia.editor.SmallButton {
    momedia.editor.Timeline timeline;
    
    public SmallPlayButton(momedia.editor.Timeline _timeline) {
        super(new javax.swing.ImageIcon(".\\icons\\play_sm.gif"));
        timeline = _timeline;
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                timeline.startPreview();
            }
        });
    }
}
