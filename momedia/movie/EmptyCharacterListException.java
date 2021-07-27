/*
 * EmptyCharacterListException.java
 *
 * Created on 01 April 2002, 23:56
 */

package momedia.movie;

/**
 *
 * @author  Morgan Evans
 */
public class EmptyCharacterListException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>EmptyCharacterListException</code> without detail message.
     */
    public EmptyCharacterListException() {
    }
    
    
    /**
     * Constructs an instance of <code>EmptyCharacterListException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public EmptyCharacterListException(String msg) {
        super(msg);
    }
}
