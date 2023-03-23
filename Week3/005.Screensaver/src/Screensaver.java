import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Screensaver extends Application {
    private ResizableCanvas canvas;

    private int speed = 3;

    private Point point_a;
    private Point point_b;
    private Point point_c;
    private Point point_d;

    private Lines line_ab;
    private Lines line_bc;
    private Lines line_cd;
    private Lines line_ad;

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
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
        stage.setTitle("Screensaver");
        stage.show();
        draw(g2d);
    }


    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.BLACK);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        this.line_ab.draw(graphics);
        this.line_bc.draw(graphics);
        this.line_cd.draw(graphics);
        this.line_ad.draw(graphics);
    }

    public void init() {
        this.point_a = new Point(new Point2D.Double(Math.floor(Math.random() * 1920),Math.floor(Math.random() * 1080)), new Point2D.Double((Math.floor(Math.random() * 12) - 6),(Math.floor(Math.random() * 12) - 6)), this.speed);
        this.point_b = new Point(new Point2D.Double(Math.floor(Math.random() * 1920),Math.floor(Math.random() * 1080)), new Point2D.Double((Math.floor(Math.random() * 12) - 6),(Math.floor(Math.random() * 12) - 6)), this.speed);
        this.point_c = new Point(new Point2D.Double(Math.floor(Math.random() * 1920),Math.floor(Math.random() * 1080)), new Point2D.Double((Math.floor(Math.random() * 12) - 6),(Math.floor(Math.random() * 12) - 6)), this.speed);
        this.point_d = new Point(new Point2D.Double(Math.floor(Math.random() * 1920),Math.floor(Math.random() * 1080)), new Point2D.Double((Math.floor(Math.random() * 12) - 6),(Math.floor(Math.random() * 12) - 6)), this.speed);

        this.line_ab = new Lines(this.speed);
        this.line_bc = new Lines(this.speed);
        this.line_cd = new Lines(this.speed);
        this.line_ad = new Lines(this.speed);
    }

    public void update(double deltaTime) {
        this.point_a.update();
        this.point_b.update();
        this.point_c.update();
        this.point_d.update();

        this.line_ab.update(this.point_a.getLocation(),this.point_b.getLocation());
        this.line_bc.update(this.point_b.getLocation(),this.point_c.getLocation());
        this.line_cd.update(this.point_c.getLocation(),this.point_d.getLocation());
        this.line_ad.update(this.point_a.getLocation(),this.point_d.getLocation());
    }

    public static void main(String[] args)
    {
        launch(Screensaver.class);
    }

}
