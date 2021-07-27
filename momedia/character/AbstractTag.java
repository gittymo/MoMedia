// AbstractTag.java
// Abstract Character Tag
// By Morgan Evans

/* 01/04/2002 - Created this class
 */

package momedia.character;

import java.util.Hashtable;

public class AbstractTag {
    private String id;                          // Unique ID for the character object
    private int startLife;                      // First frame character appears in
    private int endLife;                        // Last frame character appears in
    private Hashtable attributes;               // Hash table containing attributes for objects at particular
                                                // frames in the movie.
    private java.awt.Rectangle boundingbox;     // Bounding box for the character
    
    public AbstractTag(String _id, int _startLife, int _endLife) throws InvalidCharacterIDException {
        try {
            setID(_id);
            startLife = _startLife;
            endLife = _endLife;
            attributes = new Hashtable();
        } catch (InvalidCharacterIDException ex) {
            throw new InvalidCharacterIDException();
        }
    }
    
    public void setID(String _id) throws InvalidCharacterIDException {
        if (!momedia.utils.StringFunctions.isValidID(_id)) throw new InvalidCharacterIDException();
        id = _id;
    }
    
    public void setStartLife(int _startLife) {
        if (_startLife < 0) _startLife = -_startLife;
        if (_startLife > endLife) {
            startLife = endLife;
            endLife = _startLife;
        }
    }
    
    public void setEndLife(int _endLife) {
        if (_endLife < 0) _endLife = _endLife;
        if (_endLife < startLife) {
            endLife = startLife;
            startLife = _endLife;
        }
    }
    
    public String getID() {
        return id;
    }
    
    public int getStartLife() {
        return startLife;
    }
    
    public int getEndLife() {
        return endLife;
    }
}