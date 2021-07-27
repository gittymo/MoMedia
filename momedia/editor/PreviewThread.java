/*
 * PreviewScheduler.java
 *
 * Created on 09 April 2002, 09:09
 */

package momedia.editor;

/**
 *
 * @author  Morgan Evans
 */
public class PreviewThread {
    private float currentframe;
    private momedia.movie.FilmStrip filmstrip;
    private momedia.shared.MovieScreen moviescreen;
    private long playstarted;
    private javax.swing.Timer timer;
    private javax.swing.Action action;
    private boolean paused;
    private float offsetframe;
    private momedia.editor.EditorDocument ed;
    
    /** Creates a new instance of PreviewScheduler */
    public PreviewThread(momedia.editor.EditorDocument _ed) {
        timer = null;
        paused = false;
        ed = _ed;
        filmstrip = ed.getFilmStrip();
        moviescreen = ed.getMovieScreen();
        offsetframe = 1;
        
        action = new javax.swing.AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (!paused) {
                    ed.getTimeLine().setCurrentFrame((int) currentframe);
                    long timeexpired = System.currentTimeMillis() - playstarted;
                    currentframe = offsetframe + (timeexpired / ((int) (1000 / filmstrip.getFrameRate())));
                    if (currentframe >= filmstrip.getFrameCount()) {
                        ed.getTimeLine().setCurrentFrame(filmstrip.getFrameCount());
                        ed.getTimeLine().stopPreview();
                    }
                } 
            }
        };
    }
    
    public void setFilmstrip(momedia.movie.FilmStrip _filmstrip) {
        timer.setDelay(1000 / _filmstrip.getFrameCount());
        filmstrip = _filmstrip;
        currentframe = 1;
    }  
    
    public void startPreview() {
        if (timer == null) {
            int delay = (int) (1000 / filmstrip.getFrameRate());
            timer = new javax.swing.Timer(delay,action);
            offsetframe = ed.getTimeLine().getCurrentFrame();
        }
        paused = false;
        currentframe = ed.getTimeLine().getCurrentFrame();
        playstarted = System.currentTimeMillis();
        ed.getMovieScreen().setClipping(true);
        ed.setTitleSuffix(ed.isAltered(),true,ed.getMovieScreen().getScale());
        timer.start();
    }
    
    public void stopPreview() {
        if (timer != null) {
            timer.stop();
            timer = null;
            ed.getMovieScreen().setClipping(false);
            ed.setTitleSuffix(ed.isAltered(),false,ed.getMovieScreen().getScale());
        }
    }
    
    public void pausePreview() {
        paused = true;
    }
    
    public void resumePreview() {
        paused = false;
        currentframe = ed.getTimeLine().getCurrentFrame();
        playstarted = System.currentTimeMillis();
        offsetframe = currentframe;
    }
    
    public boolean isPlaying() {
        if (timer == null) return false;
        return true;
    }
    
    public boolean isPaused() {
        return paused;
    }
}
