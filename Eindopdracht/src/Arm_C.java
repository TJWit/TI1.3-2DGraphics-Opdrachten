import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Arm_C {
    private BufferedImage image;
    private Point2D position;
    private double angle;

    public Arm_C(Point2D position, int angle) {
        this.position = position;
        this.angle = angle;

        try {
            this.image = ImageIO.read(getClass().getResource("/images/bitmap_Arm C.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(int angle, Point2D position) {
        if (this.angle > angle) {
            this.angle -= 0.2;
        } else if (this.angle < angle) {
            this.angle += 0.2;
        } else {
        }

        this.position = position;
    }

    public void draw(FXGraphics2D graphics) {

        AffineTransform tx = new AffineTransform();
        double centerX = this.position.getX();
        double centerY = this.position.getY() + 270 ;
        tx.translate(centerX, centerY);
        tx.rotate(Math.toRadians(this.angle));
        tx.translate(- (double)this.image.getWidth()/2, this.image.getHeight() - 300);

        graphics.drawImage(this.image, tx, null);

    }

}
