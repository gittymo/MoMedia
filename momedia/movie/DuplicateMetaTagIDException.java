/*
 * DuplicateMetaTagNameException.java
 *
 * Created on 02 April 2002, 17:07
 */

package momedia.movie;

/**
 *
 * @author  Morgan Evans
 */
public class DuplicateMetaTagIDException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>DuplicateMetaTagNameException</code> without detail message.
     */
    public DuplicateMetaTagIDException() {
    }
    
    
    /**
     * Constructs an instance of <code>DuplicateMetaTagNameException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DuplicateMetaTagIDException(String msg) {
        super(msg);
    }
}
