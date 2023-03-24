import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeoutException;

public class Character {
    private BufferedImage[] sprites;

    private Point2D position;
    private Point2D speed;
    private Point2D oldSpeed;

    private int counter;
    private int sprite;
    private int state;

    public Character(Point2D position, Point2D speed) {
        this.position = position;
        this.speed = speed;
        this.counter = 0;
        this.sprite = 32;
        this.state = 0;

        try {
            BufferedImage image = ImageIO.read(getClass().getResource("/images/sprite.png"));
            sprites = new BufferedImage[65];
            for (int i = 0; i < 65; i++) {
                sprites[i] = image.getSubimage(64 * (i%8),64 *(i/8), 64, 64);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setState(int state) {
        System.out.println(state);

        switch (state) {
            case 0:
                this.state = 0;
                this.sprite = 32;
                this.speed = this.oldSpeed;

                break;
            case 1:
                this.state = 1;
                this.sprite = 28;
                this.oldSpeed = this.speed;
                this.speed = new Point2D.Double(0,0);

                break;
            default:
                System.out.println("wrong state");
                break;
        }

    }

    public void update() {
        this.position = new Point2D.Double(this.position.getX() + this.speed.getX(), this.position.getY() + this.speed.getY());
        this.counter += 1;

        if (this.state == 0) {
            if (this.counter % 20 == 0) {
                this.sprite++;
                if (this.sprite == 40) {
                    this.sprite = 32;
                }
            }
        } else if (this.state == 1) {
            if (this.counter % 20 == 0) {
                this.sprite++;
                if (this.sprite == 29) {
                    this.sprite = 42;
                }
                if (this.sprite == 65) {
                    try {
                        Thread.sleep(500);
                        setState(0);
                    } catch (InterruptedException e) {
                        setState(0);
                    }
                }
                if (this.sprite == 48) {
                    this.sprite = 64;
                }
            }
        }

    }

    public void draw(FXGraphics2D graphics) {

        AffineTransform tx = new AffineTransform();
        tx.translate(this.position.getX(),this.position.getY());
        graphics.drawImage(this.sprites[this.sprite], tx, null);

    }

    public int getState() {
        return state;
    }
}


