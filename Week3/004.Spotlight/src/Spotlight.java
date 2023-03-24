import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Spotlight extends Application {
    private ResizableCanvas canvas;
    private BufferedImage image;

    private Light light;
    private Point2D mouse;

    @Override
    public void start(Stage stage) throws Exception {

        try {
            this.image = ImageIO.read(getClass().getResource("/images/Wallpaper.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.mouse = new Point2D.Double(-1000,-1000);

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        canvas.setOnMouseMoved(e -> mouseMoved(e));

        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now)
            {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane));
        stage.setTitle("Spotlight");
        stage.show();
        draw(g2d);
    }

    private void mouseMoved(MouseEvent e) {
        this.mouse = new Point2D.Double(e.getX(), e.getY());
    }


    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        AffineTransform tx = new AffineTransform();
        graphics.drawImage(this.image, tx, null);

        Shape shape = new Rectangle2D.Double(this.light.getLocation().getX() - ((double) this.light.getSize() / 2), this.light.getLocation().getY() - ((double) this.light.getSize() / 2), this.light.getSize(), this.light.getSize());
        graphics.draw(shape);
        graphics.clip(shape);
    }

    public void init() {
        this.light = new Light(new Point2D.Double(-1000,-1000), 200);
    }

    public void update(double deltaTime) {
        this.light.update(this.mouse);
    }

    public static void main(String[] args) {
        launch(Spotlight.class);
    }

}
