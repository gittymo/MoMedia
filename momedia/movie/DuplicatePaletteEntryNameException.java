/*
 * DuplicatePaletteEntryNameException.java
 *
 * Created on 02 April 2002, 17:08
 */

package momedia.movie;

/**
 *
 * @author  Morgan Evans
 */
public class DuplicatePaletteEntryNameException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>DuplicatePaletteEntryNameException</code> without detail message.
     */
    public DuplicatePaletteEntryNameException() {
    }
    
    
    /**
     * Constructs an instance of <code>DuplicatePaletteEntryNameException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DuplicatePaletteEntryNameException(String msg) {
        super(msg);
    }
}
