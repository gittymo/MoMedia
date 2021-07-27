/*
 * DuplicateCharacterIDException.java
 *
 * Created on 09 April 2002, 16:05
 */

package momedia.movie;

/**
 *
 * @author  Morgan Evans
 */
public class DuplicateCharacterIDException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>DuplicateCharacterIDException</code> without detail message.
     */
    public DuplicateCharacterIDException() {
    }
    
    
    /**
     * Constructs an instance of <code>DuplicateCharacterIDException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DuplicateCharacterIDException(String msg) {
        super(msg);
    }
}
