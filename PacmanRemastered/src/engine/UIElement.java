/*
 * UIElement.java
 * Class used to create UIElements objects used blindly by game but but as JavaFX
 * elements by GameEngine
 */
package engine;

import java.lang.reflect.Method;
import java.util.function.Consumer;

public class UIElement {
    // ======= DATA MEMBERS =======
    protected API api;
    protected String text;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Consumer<UIElement> action;
    // ============================
    
    // ======= CONSTRUCTOR ========
    /**
     * Creates a new object to be used as a UI element (i.e. button or textbox)
     * @param api
     * @param text Text displayed in button
     * @param x X coordinate on screen
     * @param y Y coordinate on screen
     * @param w Width dimension
     * @param h Height dimension
     * @param action
     */
    public UIElement(API api, String text, int x, int y, int w, int h, Consumer<UIElement> action) {
        this(api,text,x,y,w,h);
        this.action = action;
    }
    
    public UIElement(API api, String text, int x, int y, int w, int h){
        this.api = api;
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
    
    // Fires stored action.
    public void doAction() {if (action != null) action.accept(this);};
    // ============================
    
    // ======= SET METHODS ========
    /**
     * Changes text in element to passed string
     * @param s Text to be set to TextBox
     */
    public void setText(String s) {
        text = s;
        api.updateTextBox(this);
    }
    // ============================    
}
