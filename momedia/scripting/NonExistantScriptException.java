/*
 * NonExistantScriptException.java
 *
 * Created on 02 April 2002, 00:00
 */

package momedia.scripting;

/**
 *
 * @author  Morgan Evans
 */
public class NonExistantScriptException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>NonExistantScriptException</code> without detail message.
     */
    public NonExistantScriptException() {
    }
    
    
    /**
     * Constructs an instance of <code>NonExistantScriptException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public NonExistantScriptException(String msg) {
        super(msg);
    }
}
