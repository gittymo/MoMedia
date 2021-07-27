/*
 * NonExistantPaletteEntryException.java
 *
 * Created on 02 April 2002, 17:08
 */

package momedia.movie;

/**
 *
 * @author  Morgan Evans
 */
public class NonExistantPaletteEntryException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>NonExistantPaletteEntryException</code> without detail message.
     */
    public NonExistantPaletteEntryException() {
    }
    
    
    /**
     * Constructs an instance of <code>NonExistantPaletteEntryException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public NonExistantPaletteEntryException(String msg) {
        super(msg);
    }
}
