/*
 * WindowFunctions.java
 *
 * Created on 17 April 2002, 12:26
 */

package momedia.utils;

/**
 *
 * @author  Morgan Evans
 */
public class WindowFunctions {
    
    /** Creates a new instance of WindowFunctions */
    public WindowFunctions() {
    }
    
    public static void centreDialog(javax.swing.JFrame parentwindow, javax.swing.JDialog childwindow) {
        java.awt.Dimension pd = parentwindow.getSize();
        java.awt.Dimension cd = childwindow.getSize();
        java.awt.Point pl = parentwindow.getLocation();
        int cx = (int) pl.getX() + (int) ((pd.getWidth() - cd.getWidth()) / 2);
        int cy = (int) pl.getY() + (int) ((pd.getHeight() - cd.getHeight()) / 2);
        childwindow.setLocation(cx,cy);
    }
}
