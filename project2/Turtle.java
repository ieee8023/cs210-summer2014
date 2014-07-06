    
/*************************************************************************
 *  Compilation:  javac Turtle.java
 *  Execution:    java Turtle
 *
 *  The goal of this program is to create a window to display 
 *  turtle graphics that is as easy to use as System.out.println.
 *
 *  Note: extends JFrame so that we can overload the paint() method.
 *
 *  Perhaps have method to set pen up/down instead of
 *  goForward/flyForward and go/fly.
 *
 *  See TurtlePanel for a more object-oriented version instead of
 *  this static version.
 * 
 *  begin/end instead of create/destroy?
 *
 *  Requested features
 *  ------------------
 *     -  Add support for CubicCurve2D or QudarticCurve2D or Arc2D
 *     -  Add support for fill, gradient fill, etc.
 *
 *************************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

// for images and audio
import java.net.URLConnection;
import java.net.URL;

// for audio files only
import java.applet.Applet;
import java.applet.AudioClip;


public class Turtle extends JFrame {
    private static Turtle turtle;

    private static Image offscreenImage;              // double buffered image
    private static Graphics2D offscreen;

    private static double x = 0.0, y = 0.0;           // turtle is at coordinate (x, y)
    private static double orientation = 0.0;          // facing this many degrees counterclockwise
    private static Insets insets;                     // border around JFrame that we shouldn't use
    private static int width, height;                 // size of drawing area in pixels
    private static Color background = Color.white;    // background color
    private static Color foreground = Color.black;    // foreground color

    // user can't instantiate new objects
    private Turtle() { }

    // accessor methods
    public static double x()           { return x;           }
    public static double y()           { return y;           }
    public static double orientation() { return orientation; }

    // create a canvas with drawing area width-by-height
    public static void create(int width, int height) {

        // If we don't already have a JFrame, open up a new one
        if (turtle == null) {
            turtle = new Turtle();
            Turtle.width = width;
            Turtle.height = height;
            turtle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            turtle.setTitle("Turtle Graphics");
            turtle.setResizable(false);
            turtle.setVisible(true);

            // re-adjust the size of the frame so that we don't lose space for insets
            insets = turtle.getInsets();
            turtle.setSize(new Dimension(width  + insets.left + insets.right,
                                         height + insets.top  + insets.bottom));

            // create double buffered image and graphics handle
            offscreenImage = turtle.createImage(width, height);
            offscreen = (Graphics2D) offscreenImage.getGraphics();

            // clear the screen
            clear(Color.white);
       }
       else throw new RuntimeException("Error: attempted to call Turtle.create twice");
    }

    // prevent the turtle from doing any other actions
    public static void destroy() {
        turtle.render();

        // turtle.dispose();      // close the window
        // turtle = null;
    }



    // clear the background
    public static void clear() { clear(background); }

    // clear the background with a new color
    public static void clear(Color background) {
        Turtle.background = background;
        offscreen.setColor(background);
        offscreen.fillRect(0, 0, width, height);
        offscreen.setColor(foreground);
    }
   

    public static void setColor(Color color) {
        foreground = color;
        offscreen.setColor(foreground);
    }

    public static void fly(double x, double y) {
        Turtle.x = x;
        Turtle.y = y;
    }

    public static void go(double x, double y) {
        offscreen.draw(new Line2D.Double(Turtle.x, height - Turtle.y, x, height - y));
        Turtle.x = x;
        Turtle.y = y;
    }

    // draw w-by-h rectangle, centered at current location
    public static void spot(double w, double h) {
        offscreen.fill(new Rectangle2D.Double(x - w/2, height - y - h/2, w, h));
    }

    // draw circle of diameter d, centered at current location
    public static void spot(double d) {
       if (d <= 1) offscreen.drawRect((int) x, (int) y, 1, 1);
       offscreen.fill(new Ellipse2D.Double(x - d/2, height - y - d/2, d, d));
    }


    // ImageIcon uses a MediaTracker internally - to load a single image, try this
    // Image image = new ImageIncon(url).getImage();

    // draw spot using gif - fix to be centered at (x, y)
    public static void spot(String s) {
        URL url     = Turtle.class.getResource(s); 
        Image image = Toolkit.getDefaultToolkit().getImage(url); 

        // Wait for image to load
        MediaTracker tracker = new MediaTracker(turtle);
        tracker.addImage(image, 1);
        try { tracker.waitForAll(); }
        catch (InterruptedException e) { }

        int w = image.getWidth(null);
        int h = image.getHeight(null);
   
        // center of rotation is x, height - y
        offscreen.rotate(Math.toRadians(orientation), x, height - y);
        offscreen.drawImage(image, (int) (x - w/2.0), (int) (height - y - h/2.0), null);
        offscreen.rotate(Math.toRadians(-orientation), x, height - y);
    }

    // draw spot using gif, centered on (x, y), scaled of size w-by-h
    public static void spot(String s, double w, double h) {
        URL url     = Turtle.class.getResource(s); 
        Image image = Toolkit.getDefaultToolkit().getImage(url); 

        // Wait for image to load
        MediaTracker tracker = new MediaTracker(turtle);
        tracker.addImage(image, 1);
        try { tracker.waitForAll(); }
        catch (InterruptedException e) { }

        offscreen.rotate(Math.toRadians(orientation), x, height - y);
        offscreen.drawImage(image, (int) (x - w/2.0), (int) (height - y - h/2.0),
                                   (int) w, (int) h, null);
        offscreen.rotate(Math.toRadians(-orientation), x, height - y);
    }

    public static void pixel(int x, int y) {
        offscreen.drawRect(x, height - y, 1, 1);
    }

    // rotate counterclockwise in degrees
    public static void rotate(double angle) { orientation += angle; }

    // walk forward with pen down
    public static void goForward(double d) {
        double oldx = x;
        double oldy = y;
        x += d * Math.cos(Math.toRadians(orientation));
        y += d * Math.sin(Math.toRadians(orientation));
        offscreen.draw(new Line2D.Double(x, height - y, oldx, height - oldy));
    }

    // walk forward with pen up
    public static void flyForward(double d) {
        x += d * Math.cos(Math.toRadians(orientation));
        y += d * Math.sin(Math.toRadians(orientation));
    }

    // write the given string in the current font
    public static void write(String s) {
        FontMetrics metrics = offscreen.getFontMetrics();
        int w = metrics.stringWidth(s);
        int h = metrics.getHeight();
        offscreen.drawString(s, (float) (x - w/2.0), (float) (height - y + h/2.0));
    }

    // write the given string in the given font
    public static void write(String s, Font f) {
        offscreen.setFont(f);
        write(s);
    }


    // wait for a short while *and* repaint
    public static void pause(int delay) {
        Turtle.render();
        try { Thread.currentThread().sleep(delay); }
        catch (InterruptedException e) { }
    }


    // play a wav or midi sound
    public static void grunt(String s) {
        URL url = Turtle.class.getResource(s); 
        AudioClip clip = Applet.newAudioClip(url);
        clip.play();
    }


    // transfer the offscreen graphics to the screen
    public static void render() { turtle.repaint(); }

    // user does not directly call this method
    public void paint(Graphics g) {
        if (g != null && offscreenImage != null)
            g.drawImage(offscreenImage, insets.left, insets.top, null);
    }


}
