/*
 * EditorDocument.java
 *
 * Created on 03 April 2002, 23:48
 */

package momedia.editor;

/**
 *
 * @author  Morgan Evans
 */
public class EditorDocument extends javax.swing.JInternalFrame {
    private momedia.movie.FilmStrip filmstrip;
    private momedia.movie.Movie movieinfo;
    private momedia.shared.MovieScreen moviescreen;
    private momedia.editor.PreviewThread previewthread;
    private momedia.editor.Timeline timeline;
    private momedia.editor.Editor parentframe;
    private Object selection;
    private boolean altered;
    private int ovalcount, pathcount;
    /** Creates a new instance of EditorDocument */
    public EditorDocument(momedia.movie.Movie _movieinfo, momedia.editor.Editor _parentframe, int _fscount) {
        movieinfo = _movieinfo;
        parentframe = _parentframe;
        altered = false;
        try {
            filmstrip = new momedia.movie.FilmStrip("FilmStrip" + _fscount,"FilmStrip " + _fscount, 300, 30f);
            // Finalise the construction of the window
            finaliseConstructor();
            // Set character count flags to zero
            ovalcount = 0;
            pathcount = 0;
        } catch (momedia.movie.InvalidFilmStripIDException ex) {
            System.out.println("Couldn't create filmstrip ID");
        }
    }
    
    public EditorDocument(momedia.movie.Movie _movieinfo, momedia.editor.Editor _parentframe, int _fscount, momedia.movie.FilmStrip _filmstrip) {
        movieinfo = _movieinfo;
        parentframe = _parentframe;
        altered = false;
        try {
            // Create a new filmstrip object
            filmstrip = new momedia.movie.FilmStrip("FilmStrip" + _fscount,"FilmStrip " + _fscount, 300, 30f);
            // Duplicate the attributes of the given filmstrip object '_filmstrip' in the new one.
            filmstrip.setFrameRate(_filmstrip.getFrameRate());
            filmstrip.setFrameCount(_filmstrip.getFrameCount());
            filmstrip.setBackground(_filmstrip.getBackground());
            // Copy the characters between filmstrips
            try {
                java.util.Vector clist = _filmstrip.getCharacterList();
                for (int i = 0; i < clist.size(); i++) {
                    // Set character count flags to zero
                    ovalcount = 0;
                    pathcount = 0;
                    momedia.character.AbstractTag at = (momedia.character.AbstractTag) clist.elementAt(i);
                    if (at instanceof momedia.character.OvalTag) {
                        momedia.character.OvalTag ot = (momedia.character.OvalTag) at;
                        momedia.character.OvalTag nt = new momedia.character.OvalTag(ot.getID(),ot.getStartLife(),ot.getEndLife());
                        nt.setPosition(ot.getXPos(),ot.getYPos());
                        filmstrip.addCharacter(nt);
                        ovalcount++;
                    }
                }
                System.out.println(filmstrip.getCharacterList().size());
            } catch (momedia.movie.DuplicateCharacterIDException ex) { System.out.println("Duplicate Char"); }
              catch (momedia.movie.EmptyCharacterListException ex) { System.out.println("Empty List"); }
              catch (momedia.character.InvalidCharacterIDException ex) { System.out.println("Invalid Char"); }
            // Finalise the construction of the window
            finaliseConstructor();
        } catch (momedia.movie.InvalidFilmStripIDException ex) {
            System.out.println("Couldn't create filmstrip ID");
        }
    }
    
    public momedia.shared.MovieScreen getMovieScreen() {
        return moviescreen;
    }
    
    public momedia.movie.FilmStrip getFilmStrip() {
        return filmstrip;
    }
    
    public momedia.editor.Timeline getTimeLine() {
        return timeline;
    }
    
    public boolean isAltered() {
        return altered;
    }
    
    public void setAltered(boolean _alteredstate) {
        altered = _alteredstate;
    }
    
    public void setTitleSuffix(boolean _altered, boolean _preview, float _scale) {
        int scale = (int) (100 * _scale);
        String newTitle = filmstrip.getCaption() + " at " + scale + "%";
        if (_preview) {
            newTitle += " (preview mode)";
        }
        if (_altered) {
            newTitle += " *";
        }
        setTitle(newTitle);
    }
    
    public boolean hasSelection() {
        if (selection == null)
            return false;
        else
            return true;
    }
    
    private void finaliseConstructor() {
        try {
            // Add the filmstrip to the movie
            movieinfo.addFilmStrip(filmstrip);
            // Create a new moviescreen object for the filmstrip
            moviescreen = new momedia.shared.MovieScreen(filmstrip, movieinfo, false, java.awt.Color.gray);
            // Create the event listeners for the moviescreen
            createMovieScreenListeners(moviescreen);
            // Create a new timeline object for the filmstrip
            timeline = new momedia.editor.Timeline(this);
             // Create the preview thread for the movie screen
            previewthread = new momedia.editor.PreviewThread(this);
            // Set the title of the window
            setTitleSuffix(altered,false,moviescreen.getScale());
            // Set the visibility of the close, minimise and maximise window icons
            setClosable(true);
            setResizable(true);
            setMaximizable(true);
            setIconifiable(true);
            // Set the layout for the window
            getContentPane().setLayout(new java.awt.BorderLayout());
            // Add the timeline and moviescreen objects to the window
            getContentPane().add(timeline,java.awt.BorderLayout.SOUTH);
            getContentPane().add(moviescreen,java.awt.BorderLayout.CENTER);
            // Make sure the window's close icon does nothing unless told to.
            setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
            this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
                public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                    int choice = javax.swing.JOptionPane.showConfirmDialog(parentframe,"Permanently remove filmstrip '" + filmstrip.getID() + "'?","Message from MoMedia Editor",javax.swing.JOptionPane.YES_NO_OPTION,javax.swing.JOptionPane.QUESTION_MESSAGE);
                    if (choice == javax.swing.JOptionPane.YES_OPTION) {
                        closeDocumentWindow();
                    }
                }
                public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                    momedia.editor.EditorDocument ed = (momedia.editor.EditorDocument) evt.getSource();
                    if (ed.isInPreviewMode()) {
                        // Disable the edit menu
                        parentframe.setPreviewMode(true,ed);
                    } else {
                        // Re-enable the menu if the document window is in edit mode.
                        parentframe.setPreviewMode(false,ed);
                    }
                }
            });
        } catch (momedia.editor.InvalidFrameNumberException ex){
            System.out.println("Error creating timeline for filmstrip");
        } catch (momedia.movie.DuplicateFilmStripIDException ex) {
            System.out.println("Error adding filmstrip to movie");
        }
        this.pack();
    }
    
    public void closeDocumentWindow() {
        try {
            setVisible(false);
            movieinfo.removeFilmStrip(filmstrip);
            timeline.stopPreview();
            dispose();
            parentframe.setAltered(true);
            parentframe.updateCaption();
        } catch (momedia.movie.NonExistantFilmStripException ex) {
            System.out.println("Error removing window");
        }
    }
    
    public boolean isInPreviewMode() {
        return previewthread.isPlaying();
    }
    
    public boolean previewIsPaused() {
        return previewthread.isPaused();
    }
    
    public momedia.editor.Editor getApplicationWindow() {
        return parentframe;
    }
    
    public void startPreview() {
        getApplicationWindow().setPreviewMode(true,this);
        if (previewthread.isPlaying()) {
            previewthread.resumePreview();
        } else {
            previewthread.startPreview();
        }
    }
    
    public void stopPreview() {
        getApplicationWindow().setPreviewMode(false,this);
        previewthread.stopPreview();
    }
    
    public void pausePreview() {
        previewthread.pausePreview();
    }
    
    public void createMovieScreenListeners(momedia.shared.MovieScreen ms) {
         ms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (!isInPreviewMode()) {
                    momedia.editor.ObjectsPalette op = parentframe.getObjectsPalette();
                    if (op.getSelectedIcon() == momedia.editor.ObjectsPalette.OVAL) {
                        try {
                            parentframe.getObjectsPalette().deselectAll();
                            momedia.character.OvalTag ot = new momedia.character.OvalTag("Oval"+(ovalcount++),timeline.getCurrentFrame(),timeline.getCurrentFrame());
                            ot.setPosition(evt.getX() + 32,evt.getY() + 32);
                            filmstrip.addCharacter(ot);
                            moviescreen.repaint();
                        } catch (momedia.character.InvalidCharacterIDException ex) {
                            System.out.println("Error creating oval " + ovalcount);
                        } catch (momedia.movie.DuplicateCharacterIDException ex) { 
                            System.out.println("Error, created duplicate ID 'Oval" + ovalcount + "'"); 
                        }
                    }
                }
            }
        });
    }
}