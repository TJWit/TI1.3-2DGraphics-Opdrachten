import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Base {
    private BufferedImage image;
    private Point2D position;
    private Point2D connectPoint;

    public Base(Point2D position) {
        this.position = position;

        try {
            this.image = ImageIO.read(getClass().getResource("/images/bitmap_Base.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.connectPoint = new Point2D.Double(this.position.getX(),this.position.getY() - 30);

    }

    public void draw(FXGraphics2D graphics) {

        AffineTransform tx = new AffineTransform();
        tx.translate(this.position.getX() - this.image.getWidth()/2,this.position.getY() - this.image.getHeight()/2);
        graphics.drawImage(this.image, tx, null);

    }

    public Point2D getConnectPoint() {
        return connectPoint;
    }
}
