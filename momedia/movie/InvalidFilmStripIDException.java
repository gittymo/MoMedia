/*
 * InvalidFilmStripException.java
 *
 * Created on 02 April 2002, 00:04
 */

package momedia.movie;

/**
 *
 * @author  Morgan Evans
 */
public class InvalidFilmStripIDException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>InvalidFilmStripException</code> without detail message.
     */
    public InvalidFilmStripIDException() {
    }
    
    
    /**
     * Constructs an instance of <code>InvalidFilmStripException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidFilmStripIDException(String msg) {
        super(msg);
    }
}
