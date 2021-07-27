/*
 * DuplicateFilmStripNameException.java
 *
 * Created on 02 April 2002, 17:04
 */

package momedia.movie;

/**
 *
 * @author  Morgan Evans
 */
public class DuplicateFilmStripIDException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>DuplicateFilmStripNameException</code> without detail message.
     */
    public DuplicateFilmStripIDException() {
    }
    
    
    /**
     * Constructs an instance of <code>DuplicateFilmStripNameException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DuplicateFilmStripIDException(String msg) {
        super(msg);
    }
}
