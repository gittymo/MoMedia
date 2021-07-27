/*
 * NonExistantMetaTagException.java
 *
 * Created on 02 April 2002, 17:06
 */

package momedia.movie;

/**
 *
 * @author  Morgan Evans
 */
public class NonExistantMetaTagException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>NonExistantMetaTagException</code> without detail message.
     */
    public NonExistantMetaTagException() {
    }
    
    
    /**
     * Constructs an instance of <code>NonExistantMetaTagException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public NonExistantMetaTagException(String msg) {
        super(msg);
    }
}
