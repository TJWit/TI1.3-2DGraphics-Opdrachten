import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Arm_B {
    private BufferedImage image;
    private Point2D position;
    private double angle;
    private Point2D connectPoint;

    public Arm_B(Point2D position, int angle) {
        this.position = position;
        this.angle = angle;

        try {
            this.image = ImageIO.read(getClass().getResource("/images/bitmap_Arm B.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.connectPoint = new Point2D.Double( 243 * Math.sin(Math.toRadians(this.angle)),-243 * Math.cos(Math.toRadians(this.angle)));
    }

    public void update(int angle, Point2D position) {
        if (this.angle > angle) {
            this.angle -= 0.2;
        } else if (this.angle < angle) {
            this.angle += 0.2;
        } else {
        }

        this.position = position;

        this.connectPoint = new Point2D.Double( this.position.getX() + 243 * Math.sin(Math.toRadians(this.angle)),this.position.getY()-243 * Math.cos(Math.toRadians(this.angle)));
    }

    public void draw(FXGraphics2D graphics) {

        AffineTransform tx = new AffineTransform();
        double centerX = this.position.getX();
        double centerY = this.position.getY() + 270 ;
        tx.translate(centerX, centerY);
        tx.rotate(Math.PI + Math.toRadians(this.angle));
        tx.translate(- (double)this.image.getWidth()/2, this.image.getHeight() - 318);

        graphics.drawImage(this.image, tx, null);

    }

    public Point2D getConnectPoint() {
        return connectPoint;
    }
}
