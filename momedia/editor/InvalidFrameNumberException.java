/*
 * InvalidFrameNumberException.java
 *
 * Created on 04 April 2002, 09:33
 */

package momedia.editor;

/**
 *
 * @author  Morgan Evans
 */
public class InvalidFrameNumberException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>InvalidFrameNumberException</code> without detail message.
     */
    public InvalidFrameNumberException() {
    }
    
    
    /**
     * Constructs an instance of <code>InvalidFrameNumberException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidFrameNumberException(String msg) {
        super(msg);
    }
}
