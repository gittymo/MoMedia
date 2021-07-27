/*
 * FilmStrip.java
 *
 * Created on 01 April 2002, 23:54
 */

package momedia.movie;

import java.util.Vector;

/**
 *
 * @author  Morgan Evans
 */
public class FilmStrip implements java.awt.datatransfer.Transferable {
    
    private String id;
    private String caption;
    private float framerate;
    private Vector characters;
    private int frames;
    private java.awt.Paint background;
    private momedia.scripting.Script script;
    private Vector animpaths;
    private java.awt.datatransfer.DataFlavor[] clipboard_formats;
    public static momedia.movie.FilmstripFlavor filmstrip_flavor = new momedia.movie.FilmstripFlavor();
    
    /** Creates a new instance of FilmStrip */
    public FilmStrip(String _id, String _caption, int _frames, float _framerate) throws InvalidFilmStripIDException {
        frames = 100;
        framerate = 25;
        clipboard_formats = new java.awt.datatransfer.DataFlavor[] {filmstrip_flavor};
        try {
            setID(_id);
            setFrameRate(_framerate);
            setFrameCount(_frames);
            characters = new Vector();
            animpaths = new Vector();
            caption = _caption;
            script = null;
            background = null;
        } catch (InvalidFilmStripIDException ex) {
            throw new InvalidFilmStripIDException();
        }
    }
    
    public void setID(String _id) throws InvalidFilmStripIDException {
        if (!momedia.utils.StringFunctions.isValidID(_id)) throw new InvalidFilmStripIDException();
        id = _id;
    }
    
    public void setFrameRate(float _framerate) {
        if (_framerate < 0) _framerate = -_framerate;
        framerate = _framerate;
    }
    
    public float getFrameRate() {
        return framerate;
    }
    
    public void setFrameCount(int _frames) {
        if (_frames < 0) _frames = -_frames;
        frames = _frames;
    }
    
    public int getFrameCount() {
        return frames;
    }
    
    public String getID() {
        return id;
    }
    
    public void addCharacter(momedia.character.AbstractTag _character) throws momedia.movie.DuplicateCharacterIDException {
        String nid = _character.getID();
        for (int i = 0; i < characters.size(); i++) {
            String oid = ((momedia.character.AbstractTag) characters.elementAt(i)).getID();
            if (nid.compareToIgnoreCase(oid) == 0) throw new momedia.movie.DuplicateCharacterIDException();
        }
        characters.add(_character);
    }
    
    public void giveCharacterID(momedia.character.AbstractTag _character, String _id) throws momedia.character.NonExistantCharacterException, momedia.movie.DuplicateCharacterIDException, momedia.character.InvalidCharacterIDException {
        for (int i = 0; i < characters.size(); i++) {
            String oid = ((momedia.character.AbstractTag) characters.elementAt(i)).getID();
            if (_id.compareToIgnoreCase(oid) == 0) {
                if (_character != characters.elementAt(i)) throw new momedia.movie.DuplicateCharacterIDException();
            }
        }
        boolean found = false;
        for (int i = 0; i < characters.size() && !found; i++) {
            momedia.character.AbstractTag tc = (momedia.character.AbstractTag) characters.elementAt(i);
            try {
                if (tc == _character) {
                    tc.setID(_id);
                    found = true;
                }
            } catch (momedia.character.InvalidCharacterIDException ex) {
                throw new momedia.character.InvalidCharacterIDException();
            }
        }
        if (!found) throw new momedia.character.NonExistantCharacterException();
    }   
    
    public void removeCharacter(momedia.character.AbstractTag _character) throws momedia.character.NonExistantCharacterException {
        boolean found = false;
        for (int i = 0; i < characters.size() && !found; i++) {
            if (_character == characters.elementAt(i)) found = true;
        }
        if (!found) throw new momedia.character.NonExistantCharacterException();
        characters.remove(_character);
    }
    
    public void setBackground(Object _background) {
    }
    
    public java.awt.Paint getBackground() {
        return background;
    }
    
    public void addAnimationPath() {
    }
    
    public void removeAnimationPath() throws momedia.animation.NonExistantPathException {
    }
    
    public Vector getCharacterList() throws EmptyCharacterListException {
        return characters;
    }
    
    public Vector getPathsList() throws EmptyPathsListException {
        return animpaths;
    }
    
    public momedia.scripting.Script getScript() throws momedia.scripting.NonExistantScriptException {
        return script;
    }
    
    public java.lang.String getCaption() {
        return caption;
    }
    
    public void setCaption(java.lang.String _caption) {
        caption = _caption;
    }
    
    public Object getTransferData(java.awt.datatransfer.DataFlavor flavor) throws java.awt.datatransfer.UnsupportedFlavorException {
        if (flavor != filmstrip_flavor) throw new java.awt.datatransfer.UnsupportedFlavorException(flavor);
        return this;
    }
    
    public java.awt.datatransfer.DataFlavor[] getTransferDataFlavors() {
        return clipboard_formats;
    }
    
    public boolean isDataFlavorSupported(java.awt.datatransfer.DataFlavor flavor) {
        if (flavor != filmstrip_flavor) return false;
        return true;
    }
}