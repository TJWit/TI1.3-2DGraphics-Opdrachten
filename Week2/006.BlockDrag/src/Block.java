import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Block {
    private Point2D location;
    private int size;
    private Color color;

    public Block(Point2D location, int size, Color color) {
        this.location = location;
        this.size = size;
        this.color = color;
    }

    public void draw(FXGraphics2D graphics) {
        Area block = new Area(new Rectangle2D.Double(this.location.getX(), this.location.getY(), this.size, this.size));
        graphics.setColor(color.black);
        graphics.draw(block);
        graphics.setColor(this.color);
        graphics.fill(block);
    }

    public Point2D getLocation() {
        return location;
    }

    public int getSize() {
        return size;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }
}
