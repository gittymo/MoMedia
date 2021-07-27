/*
 * MovieCell.java
 *
 * Created on 04 April 2002, 09:18
 */

package momedia.shared;

/**
 *
 * @author  Morgan Evans
 */
public class MovieScreen extends java.awt.Container {
    private momedia.movie.FilmStrip filmstrip;
    private momedia.movie.Movie movieinfo;
    private java.awt.Color bordercolour;
    private int framenumber;
    private boolean clippingset;
    private float scale;
    private int workwidth, workheight;
    private int workxpos, workypos;
    private int maxUnitIncrement = 4;
    private java.awt.Rectangle redrawarea;
    
    /** Creates a new instance of MovieCell */
    public MovieScreen(momedia.movie.FilmStrip _filmstrip, momedia.movie.Movie _movieinfo, boolean _clipbounds, java.awt.Color _bordercolour) {
        filmstrip = _filmstrip;
        movieinfo = _movieinfo;
        bordercolour = _bordercolour;
        framenumber = 1;
        clippingset = _clipbounds;
        scale = 1;
        setSize(movieinfo.getFrameWidth() + 32,movieinfo.getFrameHeight() + 32);
    }
    
    public void paint(java.awt.Graphics g) {
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
        
        // Set up some variables that area used to determine the position of the work page and
        // other objects.
        int contwidth = this.getWidth();
        int contheight = this.getHeight();
        workwidth = (int) (movieinfo.getFrameWidth() * scale);
        workheight = (int) (movieinfo.getFrameHeight() * scale);
        workxpos = (contwidth - workwidth) / 2;
        workypos = (contheight - workheight) / 2;
        int framecount = filmstrip.getFrameCount();
        if (redrawarea == null) redrawarea = new java.awt.Rectangle(0,0,contwidth,contheight);
        
        // Fill the background of the container to create the border.
        g2.setClip(0,0,contwidth,contheight);
        g2.setPaint(bordercolour);
        g2.fillRect(0, 0, contwidth, workypos);
        g2.fillRect(0, workypos+workheight, contwidth, contheight - (workypos+workheight));
        g2.fillRect(0, workypos, workxpos, workheight);
        g2.fillRect(workxpos + workwidth, workypos, contwidth - (workxpos + workwidth), workheight);
        
        // Draw the work area
        g2.setPaint(java.awt.Color.white);
        g2.fillRect(workxpos,workypos,workwidth,workheight);
        g2.setPaint(java.awt.Color.black);
        g2.drawRect(workxpos,workypos,workwidth,workheight);
        
        // Set clipping to workpage area if required
        if (clippingset) {
            if (workxpos >= 0 && workypos >=0 && workxpos + workwidth <= contwidth && workypos + workheight <= contheight) 
                g2.setClip(workxpos,workypos,workwidth,workheight);
            else {
                int cx,cy,cw,ch;
                if (workxpos < 0) cx = 0;
                else cx = workxpos;
                if (workypos < 0) cy = 0;
                else cy = workypos;
                if (workxpos + workwidth > contwidth) cw = contwidth;
                else cw = workxpos + workwidth;
                if (workypos + workheight > contheight) ch = contheight;
                else ch = workypos + workheight;
                
                g2.setClip(cx,cy,cw,ch);
            }
        }       
        // TEST CODE
        float oxstep = (float) workwidth / (float) filmstrip.getFrameCount();
        float oystep = (float) workheight / (float) filmstrip.getFrameCount();
        float oxpos = workxpos + (framenumber * oxstep);
        float oypos = workypos + (framenumber * oystep);
        float radius = (int) (32 * scale);
        g2.setPaint(java.awt.Color.blue);
        g2.fillOval((int) (oxpos - radius), (int) (oypos - radius), (int) (radius * 2), (int) (radius * 2));
        g2.setPaint(java.awt.Color.black);
        g2.drawOval((int) (oxpos - radius), (int) (oypos - radius), (int) (radius * 2), (int) (radius * 2));
        // TEST CODE
        try {
            java.util.Vector cl = filmstrip.getCharacterList();
            for (int i = 0; i < cl.size(); i++) {
                momedia.character.AbstractTag abstag = (momedia.character.AbstractTag) cl.elementAt(i); 
                if (abstag instanceof momedia.character.OvalTag && framenumber >= abstag.getStartLife() && framenumber <= abstag.getEndLife()) {
                    momedia.character.OvalTag oval = (momedia.character.OvalTag) cl.elementAt(i);
                    if (oval.getFillColour() instanceof java.awt.Color) g2.setPaint((java.awt.Color) oval.getFillColour());
                    g2.fillOval(oval.getXPos() - (int) oval.getXRadius(), oval.getYPos() - (int) oval.getYRadius(),(int) oval.getXRadius(),(int) oval.getYRadius());
                    if (oval.getLineColour() instanceof java.awt.Color) g2.setPaint((java.awt.Color) oval.getLineColour());
                    g2.drawOval(oval.getXPos() - (int) oval.getXRadius(), oval.getYPos() - (int) oval.getYRadius(),(int) oval.getXRadius(),(int) oval.getYRadius());
                }
            }
        } catch (momedia.movie.EmptyCharacterListException ex) {}
        // END OF TEST CODE
    }
    
    public void setCurrentFrame(int _framenumber) {
        framenumber = _framenumber;
        repaint();
    }
    
    public void setClipping(boolean _clipping) {
        clippingset = _clipping;
    }
    
    public void setScale(float _scale) {
        if (_scale > 0) scale = _scale;
    }
    
    public float getScale() {
        return scale;
    }
    
    public java.awt.Rectangle getWorkArea() {
        return new java.awt.Rectangle(workxpos,workypos,workwidth,workheight);
    }
    
    public void setRedrawArea(java.awt.Rectangle _newarea) {
        redrawarea = _newarea;
        this.repaint();
    }   
}