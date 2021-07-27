/*
 * NonExistantCharacterException.java
 *
 * Created on 02 April 2002, 00:06
 */

package momedia.character;

/**
 *
 * @author  Morgan Evans
 */
public class NonExistantCharacterException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>NonExistantCharacterException</code> without detail message.
     */
    public NonExistantCharacterException() {
    }
    
    
    /**
     * Constructs an instance of <code>NonExistantCharacterException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public NonExistantCharacterException(String msg) {
        super(msg);
    }
}
