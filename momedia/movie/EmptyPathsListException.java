/*
 * EmptyPathsListException.java
 *
 * Created on 01 April 2002, 23:57
 */

package momedia.movie;

/**
 *
 * @author  Morgan Evans
 */
public class EmptyPathsListException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>EmptyPathsListException</code> without detail message.
     */
    public EmptyPathsListException() {
    }
    
    
    /**
     * Constructs an instance of <code>EmptyPathsListException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public EmptyPathsListException(String msg) {
        super(msg);
    }
}
