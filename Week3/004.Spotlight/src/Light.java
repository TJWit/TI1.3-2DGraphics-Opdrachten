import java.awt.*;
import java.awt.geom.Point2D;

public class Light {
    private Point2D location;
    private int size;

    public Light(Point2D location, int size) {
        this.location = location;
        this.size = size;
    }

    public void update(Point2D location) {
        this.location = location;
    }

    public Point2D getLocation() {
        return location;
    }

    public int getSize() {
        return size;
    }
}
