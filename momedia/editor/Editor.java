/*
 * Editor.java
 *
 * Created on 05 April 2002, 16:35
 */

package momedia.editor;

/**
 *
 * @author  Morgan Evans
 */
public class Editor extends javax.swing.JFrame implements java.awt.datatransfer.ClipboardOwner {
    // Main window components
    javax.swing.JMenuBar menubar;
    javax.swing.JDesktopPane desktop;
    momedia.editor.Editor editorwindow;
    momedia.editor.ObjectsPalette objectspalette;
    momedia.editor.PropertiesBar propertiesbar;
    
    // File menu components
    javax.swing.JMenu mnu_file;
    javax.swing.JMenuItem mnu_file_newmovie;
    javax.swing.JMenuItem mnu_file_loadmovie;
    javax.swing.JMenuItem mnu_file_savemovie;
    javax.swing.JMenuItem mnu_file_newfilmstrip;
    javax.swing.JMenuItem mnu_file_removefilmstrip;
    javax.swing.JMenuItem mnu_file_exit;
    
    // View menu components
    javax.swing.JMenu mnu_view;
    javax.swing.JMenuItem mnu_view_scale100;
    javax.swing.JMenuItem mnu_view_scale50;
    javax.swing.JMenuItem mnu_view_scale200;
    javax.swing.JMenuItem mnu_view_scalearb;
    
    // FilmStrip menu components
    javax.swing.JMenu mnu_filmstrip;
    javax.swing.JMenuItem mnu_filmstrip_new;
    javax.swing.JMenuItem mnu_filmstrip_remove;
    javax.swing.JMenuItem mnu_filmstrip_manager;
    
    // Edito menu components
    javax.swing.JMenu mnu_edit;
    javax.swing.JMenuItem mnu_edit_cut;
    javax.swing.JMenuItem mnu_edit_copy;
    javax.swing.JMenuItem mnu_edit_paste;
    javax.swing.JMenuItem mnu_edit_delete;
    
    // Editor flags and globals
    private boolean moviealtered;
    private int nextfilmstrip;
    private momedia.movie.Movie movie;
    private java.awt.datatransfer.Clipboard clipboard;
    private java.util.Vector documents;
    
    /** Creates a new instance of Editor */
    public Editor() {
        super();
        
        // Make a note of the main editor window's object reference
        editorwindow = this;
        // Set the MoMedia Editor window to fill the screen when opened.
        java.awt.Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        screensize.setSize(screensize.getWidth(),screensize.getHeight() - 28);
        setSize(screensize);
        // Ensure the window does not exit the application except under proper control
        setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitApplication();
            }
        });
        // Create the global clipboard for the application
        clipboard = new java.awt.datatransfer.Clipboard("MoMedia Editor Clipboard");
        // Set the movie altered flag to false (indicating no new changes to the movie so far).
        moviealtered = false;
        // Set the suffix for the next filmstrip
        nextfilmstrip = 1;
        // Create a vector to reference all of the documents as a collection in the editor
        documents = new java.util.Vector();
        // Create a default movie for the user (complete with new document for something to work on)
        try {
            movie = new momedia.movie.Movie("New Movie",320,240);
            updateCaption();
        } catch (momedia.movie.InvalidFrameDimensionsException ex) {
            System.out.println("Fatal error - could not create default movie.  Exiting");
            System.exit(1);
        }
        
        // Create the desktop pane (which contains the palettes and filmstrip windows).
        desktop = new javax.swing.JDesktopPane();
        
        // Set the layout for the editor window.  This wants to consist of three main sections:
        java.awt.BorderLayout lyt_editor = new java.awt.BorderLayout();
        // 1) Menubar and toolbar
        // First the menubar
        menubar = new javax.swing.JMenuBar();
        // Create the file menu ...
        mnu_file = new javax.swing.JMenu("File");
        menubar.add(mnu_file);
        // ... and its respective entries
        mnu_file_newmovie = new javax.swing.JMenuItem("New Movie");
        mnu_file_newmovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMovie();
            }
        });
        mnu_file.add(mnu_file_newmovie);
        mnu_file_loadmovie = new javax.swing.JMenuItem("Load Movie");
        mnu_file.add(mnu_file_loadmovie);
        mnu_file_savemovie = new javax.swing.JMenuItem("Save Movie");
        mnu_file.add(mnu_file_savemovie);
        mnu_file.add(new javax.swing.JSeparator());
        mnu_file_exit = new javax.swing.JMenuItem("Exit");
        mnu_file_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitApplication();
            }
        });
        mnu_file.add(mnu_file_exit);
        
        // Create the edit menu ...
        mnu_edit = new javax.swing.JMenu("Edit");
        menubar.add(mnu_edit);
        // .. and its entries
        mnu_edit_cut = new javax.swing.JMenuItem("Cut");
        mnu_edit_cut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                javax.swing.JInternalFrame selected = desktop.getSelectedFrame();
                if (selected instanceof momedia.editor.EditorDocument) {
                    momedia.editor.EditorDocument ed = (momedia.editor.EditorDocument) selected;
                    if (!ed.hasSelection()) {
                        int choice = javax.swing.JOptionPane.showConfirmDialog(editorwindow,"Perform cut operation on the entire filmstrip '" + ed.getFilmStrip().getID() + "'?","Message from MoMedia Editor",javax.swing.JOptionPane.YES_NO_OPTION,javax.swing.JOptionPane.QUESTION_MESSAGE);
                        if (choice == javax.swing.JOptionPane.YES_OPTION) {
                            momedia.movie.FilmStrip fs = ed.getFilmStrip();
                            clipboard.setContents(fs,editorwindow);
                            documents.remove(ed);
                            ed.closeDocumentWindow();
                            moviealtered = true;
                            updateCaption();
                        }
                    }
                }
            }
        });
        mnu_edit.add(mnu_edit_cut);
        mnu_edit_copy = new javax.swing.JMenuItem("Copy");
        mnu_edit_copy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                javax.swing.JInternalFrame selected = desktop.getSelectedFrame();
                if (selected instanceof momedia.editor.EditorDocument) {
                    momedia.editor.EditorDocument ed = (momedia.editor.EditorDocument) selected;
                    if (!ed.hasSelection()) {
                        int choice = javax.swing.JOptionPane.showConfirmDialog(editorwindow,"Perform copy operation on the entire filmstrip '" + ed.getFilmStrip().getID() + "'?","Message from MoMedia Editor",javax.swing.JOptionPane.YES_NO_OPTION,javax.swing.JOptionPane.QUESTION_MESSAGE);
                        if (choice == javax.swing.JOptionPane.YES_OPTION) {
                            momedia.movie.FilmStrip fs = ed.getFilmStrip();
                            clipboard.setContents(fs,editorwindow);
                        }
                    }
                }
            }
        });
        mnu_edit.add(mnu_edit_copy);
        mnu_edit_paste = new javax.swing.JMenuItem("Paste");
        mnu_edit_paste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                java.awt.datatransfer.Transferable tf = clipboard.getContents(null);
                try {
                    momedia.movie.FilmStrip fs = (momedia.movie.FilmStrip) tf.getTransferData(momedia.movie.FilmStrip.filmstrip_flavor);      
                    momedia.editor.EditorDocument newdoc = new momedia.editor.EditorDocument(movie,editorwindow,nextfilmstrip++,fs);
                    documents.add(newdoc);
                    newdoc.setVisible(true);
                    newdoc.toFront();
                    desktop.add(newdoc);
                    moviealtered = true;
                    updateCaption();
                } catch (java.awt.datatransfer.UnsupportedFlavorException ex) {
                    javax.swing.JOptionPane.showMessageDialog(editorwindow,"No data to paste");
                } catch (java.io.IOException ex) {}
                  catch (java.lang.NullPointerException ex) {}
            }
        });
        
        mnu_edit.add(mnu_edit_paste);
        mnu_edit.add(new javax.swing.JSeparator());
        mnu_edit_delete = new javax.swing.JMenuItem("Delete");
        mnu_edit.add(mnu_edit_delete);
        
        // Create the view menu ...
        mnu_view = new javax.swing.JMenu("View");
        menubar.add(mnu_view);
        // ... and its entries
        mnu_view_scale50 = new javax.swing.JMenuItem("Scale 1:2");
        mnu_view_scale50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                javax.swing.JInternalFrame aif = desktop.getSelectedFrame();
                if (aif instanceof momedia.editor.EditorDocument) {
                    momedia.editor.EditorDocument aed = (momedia.editor.EditorDocument) aif;
                    aed.getMovieScreen().setScale(0.5f);
                    aed.setTitleSuffix(aed.isAltered(),aed.isInPreviewMode(),aed.getMovieScreen().getScale());
                    aed.getMovieScreen().repaint();
                }
            }
        });
        mnu_view.add(mnu_view_scale50);
        mnu_view_scale100 = new javax.swing.JMenuItem("Scale 1:1");
        mnu_view_scale100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                javax.swing.JInternalFrame aif = desktop.getSelectedFrame();
                if (aif instanceof momedia.editor.EditorDocument) {
                    momedia.editor.EditorDocument aed = (momedia.editor.EditorDocument) aif;
                    aed.getMovieScreen().setScale(1);
                    aed.setTitleSuffix(aed.isAltered(),aed.isInPreviewMode(),aed.getMovieScreen().getScale());
                    aed.getMovieScreen().repaint();
                }
            }
        });
        mnu_view.add(mnu_view_scale100);
        mnu_view_scale200 = new javax.swing.JMenuItem("Scale 2:1");
        mnu_view_scale200.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                javax.swing.JInternalFrame aif = desktop.getSelectedFrame();
                if (aif instanceof momedia.editor.EditorDocument) {
                    momedia.editor.EditorDocument aed = (momedia.editor.EditorDocument) aif;
                    aed.getMovieScreen().setScale(2);
                    aed.setTitleSuffix(aed.isAltered(),aed.isInPreviewMode(),aed.getMovieScreen().getScale());
                    aed.getMovieScreen().repaint();
                }
            }
        });
        mnu_view.add(mnu_view_scale200);
        mnu_view_scalearb = new javax.swing.JMenuItem("User Defined Scale ...");
        mnu_view_scalearb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                momedia.editor.MovieScaleDialog msd = new momedia.editor.MovieScaleDialog(editorwindow);
                momedia.utils.WindowFunctions.centreDialog(editorwindow,msd);
                msd.setVisible(true);
                if (msd.getUserChoice() == momedia.editor.MovieScaleDialog.OK_OPTION) {
                    float scale = (float) msd.getScale() / 100f;
                    javax.swing.JInternalFrame aif = desktop.getSelectedFrame();
                    if (aif instanceof momedia.editor.EditorDocument) {
                        momedia.editor.EditorDocument aed = (momedia.editor.EditorDocument) aif;
                        aed.getMovieScreen().setScale(scale);
                        aed.setTitleSuffix(aed.isAltered(),aed.isInPreviewMode(),aed.getMovieScreen().getScale());
                        aed.getMovieScreen().repaint();
                    }
                }
            }
        });
        mnu_view.add(mnu_view_scalearb);
        
        // Create the filmstrip menu ...
        mnu_filmstrip = new javax.swing.JMenu("Filmstrip");
        menubar.add(mnu_filmstrip);
        // .. and its entries
        mnu_filmstrip_new = new javax.swing.JMenuItem("New Filmstrip");
        mnu_filmstrip_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                momedia.editor.EditorDocument newdoc = new momedia.editor.EditorDocument(movie,editorwindow,nextfilmstrip++);
                documents.add(newdoc);
                newdoc.setVisible(true);
                newdoc.toFront();
                desktop.add(newdoc);
                moviealtered = true;
                updateCaption();
            }
        });
        mnu_filmstrip.add(mnu_filmstrip_new);
        mnu_filmstrip_remove = new javax.swing.JMenuItem("Remove Filmstrip");
        mnu_filmstrip_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                javax.swing.JInternalFrame selected = desktop.getSelectedFrame();
                if (selected instanceof momedia.editor.EditorDocument) {
                    momedia.editor.EditorDocument ed = (momedia.editor.EditorDocument) selected;
                    int choice = javax.swing.JOptionPane.showConfirmDialog(editorwindow,"Permanently remove filmstrip '" + ed.getFilmStrip().getID() + "'?","Message from MoMedia Editor",javax.swing.JOptionPane.YES_NO_OPTION,javax.swing.JOptionPane.QUESTION_MESSAGE);
                    if (choice == javax.swing.JOptionPane.YES_OPTION) {
                        documents.remove(ed);
                        ed.closeDocumentWindow();
                        moviealtered = true;
                        updateCaption();
                    }
                }
            }
        });
        mnu_filmstrip.add(mnu_filmstrip_remove);
        mnu_filmstrip.add(new javax.swing.JSeparator());
        mnu_filmstrip_manager = new javax.swing.JMenuItem("Filmstrip Manager...");
        mnu_filmstrip.add(mnu_filmstrip_manager);
        
        // Add the menu bar to the application window
        setJMenuBar(menubar);
        
        // Create the properties bar and add it to the north border of the application window
        // Add the desktop area to the center of the main window.
        getContentPane().add(desktop,java.awt.BorderLayout.CENTER);
        // 3) The status bar (to show handy-dandy tips for the user)
        // Create the objects palette and add it to the west border of the application window.
        objectspalette = new momedia.editor.ObjectsPalette();
        objectspalette.setVisible(true);
        desktop.add(objectspalette,javax.swing.JLayeredPane.PALETTE_LAYER);
        
        // Create a new filmstrip for the movie and add it to the desktop pane
        momedia.editor.EditorDocument doc = new momedia.editor.EditorDocument(movie,editorwindow,nextfilmstrip++);
        doc.setVisible(true);
        doc.toFront();
        desktop.add(doc);
    }
    
    public static void main(String args[]) {
        momedia.editor.Editor editor = new momedia.editor.Editor();
        editor.setVisible(true);
    }
    
    public void exitApplication() {
        if (moviealtered) {
            int choice = preSaveCheck("This movie has been altered since you last saved it.  Save changes before exiting?");
            if (choice == javax.swing.JOptionPane.YES_OPTION) {
                // Save code here
            }
            if (choice != javax.swing.JOptionPane.CANCEL_OPTION) {
                shutdown();
            }
        } else {
            shutdown();
        }
    }
    
    public void newMovie() {
        if (moviealtered) {
            int choice = preSaveCheck("This movie has been altered since the last time your saved it.  Save changes before creating a new movie?");
            if (choice == javax.swing.JOptionPane.YES_OPTION) {
                // Save code here
            }
            if (choice != javax.swing.JOptionPane.CANCEL_OPTION) {
                createMovie();
            }
        } else createMovie();
    }
    
    public int preSaveCheck(String _message) {
        int choice = javax.swing.JOptionPane.showConfirmDialog(editorwindow,_message,"Warning from MoMedia Editor",javax.swing.JOptionPane.YES_NO_CANCEL_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);
        return choice;
    }
    
    private void shutdown() {
        setVisible(false);
        dispose();
        System.exit(0);
    }
    
    public void createMovie() {
        // Close all existing Editor Document windows
        javax.swing.JInternalFrame[] fsw = desktop.getAllFrames();
        for (int i = 0; i < fsw.length; i++) {
            if (fsw[i] instanceof momedia.editor.EditorDocument) {
                ((momedia.editor.EditorDocument) fsw[i]).setVisible(false);
                ((momedia.editor.EditorDocument) fsw[i]).dispose();
            }
        }
        
        // Reset movie flags
        nextfilmstrip = 1;
        moviealtered = false;
        
        // Create a new movie
        try {
            movie = new momedia.movie.Movie("New Movie",320,240);
            updateCaption();
        } catch (momedia.movie.InvalidFrameDimensionsException ex) {
            System.out.println("Fatal error - could not create new movie.  Exiting");
            System.exit(1);
        }
        
        // Create a new filmstrip for the movie and add it to the desktop pane
        momedia.editor.EditorDocument doc = new momedia.editor.EditorDocument(movie,editorwindow,nextfilmstrip++);
        doc.setVisible(true);
        desktop.add(doc);
    }
    
    public void updateCaption() {
        String newTitle = "MoMedia Editor v1.00 - " + movie.getCaption();
        if (moviealtered) newTitle += " *";
        setTitle(newTitle);
    }
    
    public void setAltered(boolean _moviealtered) {
        moviealtered = _moviealtered;
    }
    
    public void lostOwnership(java.awt.datatransfer.Clipboard clipboard, java.awt.datatransfer.Transferable transferable) {
    }
    
    public void setPreviewMode(boolean _state,momedia.editor.EditorDocument _ed) {
        if (desktop.getSelectedFrame() == _ed) mnu_edit.setEnabled(!_state);
    }
    
    public momedia.editor.ObjectsPalette getObjectsPalette() {
        return objectspalette;
    }
}