/*
 * OvalTag.java
 *
 * Created on 09 April 2002, 12:25
 */

package momedia.character;

/**
 *
 * @author  Morgan Evans
 */
public class OvalTag extends momedia.character.AbstractTag {
    private int xpos,ypos;
    private float xrad,yrad;
    private Object fillcolour,linecolour;
    private java.awt.BasicStroke linestyle;
    private boolean outlined, filled;
    
    /** Creates a new instance of OvalTag */
    public OvalTag(String _id, int _startlife, int _endlife) throws momedia.character.InvalidCharacterIDException {
        super(_id,_startlife,_endlife);
        xpos = -1; ypos = -1;
        xrad = 64; yrad = 64;
        outlined = true;
        filled = true;
        
        // START OF TEST CODE
        linecolour = java.awt.Color.black;
        fillcolour = java.awt.Color.blue;
    }
    
    public void setPosition(int _xpos, int _ypos) {
        xpos = _xpos;
        ypos = _ypos;
    }
    
    public void setRadius(float _xrad, float _yrad) {
        if (_xrad >= 0 && _yrad >= 0) {
            xrad = _xrad;
            yrad = _yrad;
        }
    }
    
    public void setColours(Object _linecolour, Object _fillcolour) {
        linecolour = _linecolour;
        fillcolour = _fillcolour;
    }
    
    public Object getLineColour() {
        return linecolour;
    }
    
    public Object getFillColour() {
        return fillcolour;
    }
    
    public int getXPos() {
        return xpos;
    }
    
    public int getYPos() {
        return ypos;
    }
    
    public float getXRadius() {
        return xrad;
    }
    
    public float getYRadius() {
        return yrad;
    }
}