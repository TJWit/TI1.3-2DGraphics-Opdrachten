import java.awt.*;
import java.awt.geom.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Spiral extends Application {
    private Canvas canvas;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Spiral");
        primaryStage.show();
    }
    
    
    public void draw(FXGraphics2D graphics) {
        graphics.translate(this.canvas.getWidth()/2, this.canvas.getHeight()/2);

        double prevX = 0;
        double prevY = 0;
        double n = 5;

        graphics.draw(new Line2D.Double(-500, 0, 500, 0));
        graphics.draw(new Line2D.Double(0, -500, 0, 500));

        for (double i = 0; i < 100; i+=0.01) {
            double x = n * i * Math.cos(i);
            double y = n * i * Math.sin(i);
            graphics.draw(new Line2D.Double(prevX, prevY, x, y));
            prevX = x;
            prevY = y;
        }
    }
    
    
    
    public static void main(String[] args) {
        launch(Spiral.class);
    }

}
