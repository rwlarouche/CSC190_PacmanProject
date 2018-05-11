/*
 * UIElement.java
 * Class used to create UIElements objects used blindly by game but but as JavaFX
 * elements by GameEngine
 */
package engine;

public class UIElement {
    // ======= DATA MEMBERS =======
    protected API api;
    protected String text;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    // ============================
    
    // ======= CONSTRUCTOR ========
    /**
     * Creates a new object to be used as a UI element (i.e. button or textbox)
     * @param text Text displayed in button
     * @param x X coordinate on screen
     * @param y Y coordinate on screen
     * @param w Width dimension
     * @param h Height dimension
     */
    public UIElement(String text, int x, int y, int w, int h) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }
    // ============================
    
    // ===== ACCESSOR METHODS =====
    /**
     * Returns text contained in element
     * @return text
     */
    public String getText() {
        return text;
    }
    
    /**
     * Returns x coordinate of element
     * @return x
     */
    public int getX() {
        return x;
    }
    
    /**
     * Returns y coordinate of element
     * @return y
     */
    public int getY() {
        return y;
    }
    
    /**
     * Returns height dimension of element
     * @return height
     */
    public int getHeight(){
        return height;
    }
    
    /**
     * Returns width dimension of element
     * @return width
     */
    public int getWidth() {
        return width;
    }
    
    // Override with desired functionality if element is a button
    public void action() {};
    // ============================
    
    // ======= SET METHODS ========
    /**
     * Changes text in element to passed string
     * @param s Text to be set to TextBox
     */
    public void setText(String s) {
        text = s;
    }
    // ============================    
}
