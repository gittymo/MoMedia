/*
 * InvalidCharacterIDException.java
 *
 * Created on 01 April 2002, 23:15
 */

package momedia.character;

/**
 *
 * @author  Morgan Evans
 */
public class InvalidCharacterIDException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>InvalidCharacterIDException</code> without detail message.
     */
    public InvalidCharacterIDException() {
    }
    
    
    /**
     * Constructs an instance of <code>InvalidCharacterIDException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidCharacterIDException(String msg) {
        super(msg);
    }
}
