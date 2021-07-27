/*
 * Timeline.java
 *
 * Created on 04 April 2002, 09:15
 */

package momedia.editor;

/**
 *
 * @author  Morgan Evans
 */
public class Timeline extends java.awt.Container {
    
    private momedia.shared.MovieScreen moviescreen;
    private momedia.movie.FilmStrip filmstrip;
    private int minframe, maxframe, currentframe;
    private momedia.editor.SmallFrameTextField txt_currentframe;
    private momedia.editor.SmallFrameSlider sld_currentframe;
    private momedia.editor.SmallButton btn_tostart, btn_rewind, btn_forwards, btn_toend, btn_stop, btn_play;
    private javax.swing.JLabel lbl_maxframe;
    private momedia.editor.EditorDocument editordocument;
    
    /** Creates a new instance of Timeline */
    public Timeline(momedia.editor.EditorDocument _editordocument) throws momedia.editor.InvalidFrameNumberException {
        editordocument = _editordocument;
        moviescreen = editordocument.getMovieScreen();
        filmstrip = editordocument.getFilmStrip();
        minframe = 1;
        maxframe = filmstrip.getFrameCount();
        currentframe = 1;
        
        btn_tostart = new momedia.editor.SmallToStartButton(this);
        btn_toend = new momedia.editor.SmallToEndButton(this);
        btn_stop = new momedia.editor.SmallStopButton(this);
        btn_play = new momedia.editor.SmallPlayButton(this);
        btn_forwards = new momedia.editor.SmallForwardButton(this);
        btn_rewind = new momedia.editor.SmallRewindButton(this);
        sld_currentframe = new momedia.editor.SmallFrameSlider(this);
        lbl_maxframe = new javax.swing.JLabel(Integer.toString(maxframe));
        lbl_maxframe.setPreferredSize(new java.awt.Dimension(64,20));
        lbl_maxframe.setMinimumSize(new java.awt.Dimension(64,20));
        txt_currentframe = new momedia.editor.SmallFrameTextField(this);
        
        setLayout(new javax.swing.BoxLayout(this,javax.swing.BoxLayout.X_AXIS));
        add(btn_tostart);
        add(btn_rewind);
        add(btn_stop);
        add(btn_play);
        add(btn_forwards);
        add(btn_toend);
        add(sld_currentframe);
        add(txt_currentframe);
        add(lbl_maxframe);
    }
    
    public int getMinimumFrame() {
        return minframe;
    }
    
    public int getMaximumFrame() {
        return maxframe;
    }
    
    public int getCurrentFrame() {
        return currentframe;
    }
    
    public void setCurrentFrame(int _framenumber) {
        currentframe = _framenumber;
        txt_currentframe.setCurrentFrame(currentframe);
        sld_currentframe.setCurrentFrame(currentframe);
        moviescreen.setCurrentFrame(currentframe);
    }
    
    public void startPreview() {
        if (editordocument.isInPreviewMode()) {
            if (editordocument.previewIsPaused()) {
                btn_play.setIcon(new javax.swing.ImageIcon(".\\icons\\pause_sm.gif"));
                setPreviewControlState(false);
                editordocument.startPreview();
            } else {
                btn_play.setIcon(new javax.swing.ImageIcon(".\\icons\\play_sm.gif"));
                setPreviewControlState(true);
                editordocument.pausePreview();
            }
        } else {
            editordocument.startPreview();
            btn_play.setIcon(new javax.swing.ImageIcon(".\\icons\\pause_sm.gif"));
            setPreviewControlState(false);
        }
    }
    
    public void setPreviewControlState(boolean _state) {
        btn_tostart.setEnabled(_state);
        btn_toend.setEnabled(_state);
        btn_rewind.setEnabled(_state);
        btn_forwards.setEnabled(_state);
        sld_currentframe.setEnabled(_state);
        txt_currentframe.setEnabled(_state);
    }
    
    public void stopPreview() {
        editordocument.stopPreview();
        setPreviewControlState(true);
        btn_play.setIcon(new javax.swing.ImageIcon(".\\icons\\play_sm.gif"));
    }
}