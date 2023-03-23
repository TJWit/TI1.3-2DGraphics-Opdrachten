import java.awt.geom.Point2D;

public class Point {
    private Point2D location;
    private Point2D direction;

    private int counter;
    private int speed;

    private int maxX = 1920;
    private int maxY = 1080;
    private int minX = 0;
    private int minY = 0;

    public Point(Point2D location, Point2D direction, int speed) {
        this.location = location;
        this.direction = direction;
        this.counter = 0;
        this.speed = speed;
    }

    public void update() {

        this.counter++;

        if (this.counter % this.speed == 0) {

            if (this.location.getX() + this.direction.getX() > this.maxX || this.location.getX() + this.direction.getX() < this.minX) {
                this.direction = new Point2D.Double(-1*this.direction.getX(), this.direction.getY());
            }

            if (this.location.getY() + this.direction.getY() > this.maxY || this.location.getY() + this.direction.getY() < this.minY) {
                this.direction = new Point2D.Double(this.direction.getX(),-1*this.direction.getY());
            }

            this.location = new Point2D.Double(this.location.getX() + this.direction.getX(), this.location.getY() + this.direction.getY());
        }

    }

    public Point2D getLocation() {
        return this.location;
    }
}
