/*
 * NonExistantPathException.java
 *
 * Created on 01 April 2002, 23:56
 */

package momedia.animation;

/**
 *
 * @author  Morgan Evans
 */
public class NonExistantPathException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>NonExistantPathException</code> without detail message.
     */
    public NonExistantPathException() {
    }
    
    
    /**
     * Constructs an instance of <code>NonExistantPathException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public NonExistantPathException(String msg) {
        super(msg);
    }
}
