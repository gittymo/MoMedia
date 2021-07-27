/*
 * NonExistantFilmStripException.java
 *
 * Created on 02 April 2002, 17:05
 */

package momedia.movie;

/**
 *
 * @author  Morgan Evans
 */
public class NonExistantFilmStripException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>NonExistantFilmStripException</code> without detail message.
     */
    public NonExistantFilmStripException() {
    }
    
    
    /**
     * Constructs an instance of <code>NonExistantFilmStripException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public NonExistantFilmStripException(String msg) {
        super(msg);
    }
}
