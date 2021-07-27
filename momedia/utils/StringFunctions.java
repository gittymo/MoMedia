/*
 * StringFunctions.java
 *
 * Created on 02 April 2002, 10:13
 */

package momedia.utils;

/**
 *
 * @author  Morgan Evans
 */
public class StringFunctions {
    
    public static boolean isValidID(String _id) {        
        byte bid[] = _id.getBytes();
        boolean valid = false;
        
        if (bid.length > 0) valid = true;
        if ((bid[0] >= 'A' && bid[0] <= 'Z') || (bid[0] >= 'a' && bid[0] <= 'z') || bid[0] == '_') valid = true;
        else valid = false;
        
        if (valid) {
            for (int i = 0; i < bid.length && valid; i++) {
                if (bid[i] < '0') valid = false;
                else {
                    if (bid[i] > '9' && bid[i] < 'A') valid = false;
                    else {
                        if (bid[i] > 'Z' && bid[i] < 'a') valid = false;
                        else {
                            if (bid[i] > 'z') valid = false;
                            else {
                                // if (bid[i] != '_') valid = false;
                            }
                        }
                    }
                }
            }
        }
        return valid;
    }
}
