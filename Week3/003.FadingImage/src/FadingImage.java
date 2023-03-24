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

public class FadingImage extends Application {
    private ResizableCanvas canvas;
    private BufferedImage imageOne;
    private BufferedImage imageTwo;

    private float fade;
    private boolean up;

    @Override
    public void start(Stage stage) throws Exception {

        try {
            this.imageOne = ImageIO.read(getClass().getResource("/images/Wallpaper17.jpg"));
            this.imageTwo = ImageIO.read(getClass().getResource("/images/Wallpaper20.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.fade = 0;
        this.up = true;

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle(long now) {
		if(last == -1)
                    last = now;
		update((now - last) / 1000000000.0);
		last = now;
		draw(g2d);
            }
        }.start();
        
        stage.setScene(new Scene(mainPane));
        stage.setTitle("Fading image");
        stage.show();
        draw(g2d);
    }
    
    
    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int)canvas.getWidth(), (int)canvas.getHeight());

        AffineTransform tx = new AffineTransform();

        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 1));
        graphics.drawImage(this.imageOne, tx, null);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.fade));
        graphics.drawImage(this.imageTwo, tx, null);
    }
    

    public void update(double deltaTime) {
        float amount = 1/200f;

        if (this.up) {
            this.fade += amount;
        } else {
            this.fade -= amount;
        }

        if (this.fade > 1) {
            this.fade -= amount;
            this.up = false;
        }
        if (this.fade < 0){
            this.fade += amount;
            this.up = true;
        }
    }

    public static void main(String[] args) {
        launch(FadingImage.class);
    }

}
