/*
 * Movie.java
 *
 * Created on 02 April 2002, 17:03
 */

package momedia.movie;

/**
 *
 * @author  Morgan Evans
 */
public class Movie {
    
    private int framewidth;   
    private int frameheight;
    private java.util.Vector palette;
    private java.util.Hashtable metatags;
    private momedia.scripting.Script script;
    private boolean copyprotected;
    private java.util.Vector filmstrips;
    private String caption;
    
    /** Creates a new instance of Movie */
    public Movie(String _caption, int _framewidth, int _frameheight) throws momedia.movie.InvalidFrameDimensionsException {
        framewidth = 0;
        frameheight = 0;
        caption = _caption;
        try {
            setFrameDimensions(_framewidth, _frameheight);
            palette = new java.util.Vector();
            metatags = new java.util.Hashtable();
            filmstrips = new java.util.Vector();
            copyprotected = false;
            script = new momedia.scripting.Script();
        }
         catch (InvalidFrameDimensionsException ex) {
             throw new InvalidFrameDimensionsException();
         }
    }
    
    public void setFrameDimensions(int _width, int _height) throws InvalidFrameDimensionsException {
        if (_width < 160 || _height < 120) throw new InvalidFrameDimensionsException();
        framewidth = _width;
        frameheight = _height;
    }
    
    public int getFrameWidth() {
        return framewidth;
    }
    
    public int getFrameHeight() {
        return frameheight;
    }
    
    public void addFilmStrip(momedia.movie.FilmStrip _filmstrip) throws DuplicateFilmStripIDException {
        for (int i = 0; i < filmstrips.size(); i++) {
            momedia.movie.FilmStrip efs = (momedia.movie.FilmStrip) filmstrips.elementAt(i);
            String efsid = efs.getID();
            String fsid = _filmstrip.getID();
            if (efsid.compareToIgnoreCase(fsid) == 0) throw new DuplicateFilmStripIDException();
        }
        
        filmstrips.add(_filmstrip);
    }
    
    public void removeFilmStrip(momedia.movie.FilmStrip _filmstrip) throws NonExistantFilmStripException {
        boolean found = false;
        for (int i = 0; i < filmstrips.size() && !found; i++) {
            momedia.movie.FilmStrip efs = (momedia.movie.FilmStrip) filmstrips.elementAt(i);
            if (efs == _filmstrip) found = true;
        }
        if (!found) throw new NonExistantFilmStripException();
        else {
            filmstrips.remove(_filmstrip);
        }
    }
    
    public int getFilmStripCount() {
        return filmstrips.size();
    }
    
    public void addPaletteEntry(java.lang.String _name, java.awt.Paint _paint) throws DuplicatePaletteEntryNameException {
    }
    
    public void removePaletteEntry(java.lang.String _name) throws NonExistantPaletteEntryException {
    }
    
    public void addMetaTag(java.lang.String _id, java.lang.String _value) throws DuplicateMetaTagIDException {
        String id = _id.toUpperCase();
        if (!metatags.containsKey(id)) {
            metatags.put(id, _value);
        } else throw new DuplicateMetaTagIDException();
    }
    
    public void removeMetaTag(java.lang.String _id) throws NonExistantMetaTagException {
        String id = _id.toUpperCase();
        if (metatags.containsKey(id)) {
            metatags.remove(id);
        } else throw new NonExistantMetaTagException();
    }
    
    public void updateMetaTag(java.lang.String _id, java.lang.String _value) throws NonExistantMetaTagException {
        String id = _id.toUpperCase();
        if (metatags.containsKey(id)) {
            metatags.put(id,_value);
        } else throw new NonExistantMetaTagException();
    }
    
    public java.lang.String getMetaTagValue(java.lang.String _id) throws NonExistantMetaTagException {
        String id = _id.toUpperCase();
        if (!metatags.containsKey(id)) throw new NonExistantMetaTagException();
        return (String) metatags.get(id);
    }
        
    public momedia.scripting.Script getScript() throws momedia.scripting.NonExistantScriptException {
        return script;
    }
    
    public String getCaption() {
        return caption;
    }
    
    public void setCaption(String _caption) {
        caption = _caption;
    }
}
