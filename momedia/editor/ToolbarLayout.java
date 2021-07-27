/*
 * ToolbarLayout.java
 *
 * Created on 10 April 2002, 17:44
 */

package momedia.editor;

/**
 *
 * @author  Morgan Evans
 */
public class ToolbarLayout implements java.awt.LayoutManager {
    private int columns = 1;
    private int rows = 1;
    private Object[] components;
    private int[] colspan;
    private int writeindex = 0;
    private int prefwidth, prefheight;
    private int minwidth, minheight;
    private int maxwidth;
    private int hgap = 2;
    private int vgap = 2;
    private boolean sizeunknown = true;
    
    /** Creates a new instance of ToolbarLayout */
    public ToolbarLayout(int _columns, int _rows) {
        columns = _columns;
        rows = _rows;
        components = new Object[columns * rows];
        colspan = new int[columns * rows];
        for (int i = 0; i < components.length; i++) {
            components[i] = null;
            colspan[i] = 0;
        }
    }
    
    public void setHGap(int _hgap) {
        hgap = _hgap;
    }
    
    public void setVGap(int _vgap) {
        vgap = _vgap;
    }
    
    public void addLayoutComponent(String str, java.awt.Component component) {
        if (writeindex < components.length) {
            components[writeindex] = component;
            colspan[writeindex] = 1;
            writeindex++;
        }
    }
    
    public void addLayoutComponent(java.awt.Component component, int _colspan) {
        if (writeindex + (_colspan - 1) < components.length) {
            components[writeindex] = component;
            colspan[writeindex] = _colspan;
            writeindex += _colspan;
        }
    }   
    
    private void setSizes(java.awt.Container parent) {
        // Get the insets for the parent container
        java.awt.Insets pins = parent.getInsets();
        
        // Add the insets to the preferred width and height parameters
        prefwidth = 0;
        prefheight = 0;
        
        // To get the preferred size for the container we need to:
        // a) Work through each row and record the width of the row which takes the most amount of space.
        // b) Increment the vertical height according to the the depth of each row
        boolean endofrow = false;
        int currentcol = 0;
        int currentwidth = 0;
        maxwidth = 0;
        int maxheight = 0;
        for (int i = 0; i < components.length; i++) {
            java.awt.Component c = (java.awt.Component) components[i];
            java.awt.Dimension cd = c.getPreferredSize();
            currentwidth += cd.getWidth();
            currentwidth += hgap;
            currentcol += colspan[i];
            if (cd.getHeight() > maxheight) maxheight = (int) cd.getHeight();
            if (currentcol >= columns) {
                currentwidth -= hgap;
                if (currentwidth > maxwidth) maxwidth = currentwidth;
                prefheight += maxheight;
                currentcol = 0;
                currentwidth = 0;
                maxheight = 0;
            }
        }
        prefheight -= vgap;
        prefwidth += maxwidth;
        
        // Because it's a toolbar we don't want people to be able to resize it below the preferred area.
        minwidth = prefwidth;
        minheight = prefheight;
        
        sizeunknown = false;
    }
    
    public void layoutContainer(java.awt.Container container) {
        /*java.awt.Insets insets = container.getInsets();
        java.awt.Dimension dim = container.getSize();
        int cx = insets.left;
        int cy = insets.top;
        int firstonrow = 0;
        int rowwidth = 0;
        int currentcol = 0;
        int maxheight = 0;
        for (int i = 0; i < components.length; i++) {
            java.awt.Component c = components[i];
            java.awt.Dimension cd = c.getPreferredSize();
            rowwidth += (int) cd.getWidth();
            rowwidth += hgap;
            currentcol += colspan[i];
            if (cd.getHeight() > maxheight) maxheight = (int) cd.getHeight();
            if (currentcol >= columns) {
                int xpos = (maxwidth - rowwidth) / 2;
                
                for (int j = firstonrow; j < currentcol; j++) {
                    
                maxheight = 0;
                rowwidth = 0;
                firstonrow = currentcol;
                }
            }
        }*/
    }
    
    public java.awt.Dimension minimumLayoutSize(java.awt.Container container) {
        // Make sure the preferred dimensions of the component are known
        if (sizeunknown) setSizes(container);
        // Get the parent's insets.
        java.awt.Insets insets = container.getInsets();
        // Create a new dimension object containing the minimum required dimensions for the container ...
        java.awt.Dimension dim = new java.awt.Dimension(minwidth + insets.left + insets.right,minheight + insets.top + insets.bottom);
        // ... and pass them back to the calling function.
        return dim;
    }
    
    public java.awt.Dimension preferredLayoutSize(java.awt.Container container) {
        if (sizeunknown) setSizes(container);
        java.awt.Insets insets = container.getInsets();
        java.awt.Dimension dim = new java.awt.Dimension(prefwidth + insets.left + insets.right,prefheight + insets.top + insets.bottom);
        return dim;
    }
    
    public void removeLayoutComponent(java.awt.Component component) {
    
    }
    
    public String toString() {
        return getClass().getName() + "[]";
    }
}