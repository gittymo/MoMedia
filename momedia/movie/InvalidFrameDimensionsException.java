/*
 * InvalidFrameDimensionsException.java
 *
 * Created on 03 April 2002, 09:21
 */

package momedia.movie;

/**
 *
 * @author  Morgan Evans
 */
public class InvalidFrameDimensionsException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>InvalidFrameDimensionsException</code> without detail message.
     */
    public InvalidFrameDimensionsException() {
    }
    
    
    /**
     * Constructs an instance of <code>InvalidFrameDimensionsException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidFrameDimensionsException(String msg) {
        super(msg);
    }
}
