import java.awt.*;
import java.awt.geom.*;

/**
 * This class creates the shapes that are used to create the 
 * ball and paddle
 * @author jacob. muizz, raheel
 *
 */
public class Shape {
    
    private Color color;
    private boolean fill;
    private RectangularShape shape;
    
    /**
     * This constructor will create the shapes
     * @param shape
     * @param color
     * @param fill
     */
    public Shape(RectangularShape shape, Color color, boolean fill) {
        
        this.shape = shape;
        this.color = color;
        this.fill = fill;
    }
    
    public Color getColor(){
        return color;
    }
    
    /**
     * This method will change the color of the shape
     * @param color
     */
    public void changeColor(Color color) {
        this.color = color;
    }
    
    /**
     * This method will draw and add color to the shapes
     * @param g2
     */
    public void draw(Graphics2D g2) {
        g2.setPaint(color);
        g2.draw(shape);
        if(fill) {
            g2.fill(shape);
        }
    }
    
    /**
     * This method is used to determine if two shapes are intersecting each other
     * @param object
     * @return
     */
    public boolean intersection(Shape object) {
    	return this.shape.intersects(object.shape.getBounds2D());
    }
    
    /**
     * This method is used to determine if the shapes intersected the bricks
     * @param object
     * @return
     */
    public boolean intersection(Rectangle2D object) {
    	return this.shape.intersects(object.getBounds2D());
    }
    
    public double getX() {
        return shape.getX();
    }
    
    public double getY() {
        return shape.getY();
    }
    
    public double getHeight() {
        return shape.getHeight();
    }
    
    public double getWidth() {
        return shape.getWidth();
    }
    
    /**
     * This method moves the shapes 
     * @param dx
     * @param dy
     */
    public void move (double dx, double dy) {
        shape.setFrame(getX() + dx, getY()+ dy, getWidth(), getHeight());
    }

}