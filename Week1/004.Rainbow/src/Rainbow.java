import java.awt.*;
import java.awt.geom.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Rainbow extends Application {
    private Canvas canvas;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Rainbow");
        primaryStage.show();
    }
    
    
    public void draw(FXGraphics2D graphics) {
        graphics.translate(this.canvas.getWidth()/2, this.canvas.getHeight()/2);

        double r1 = 300;
        double r2 = 200;

        graphics.draw(new Line2D.Double(-500, 0, 500, 0));
        graphics.draw(new Line2D.Double(0, -500, 0, 500));

        for (double i = 0; i <= 2*Math.PI; i+=1/500f) {
            double x1 = r1 * Math.cos(i);
            double y1 = r1 * Math.sin(i);
            double x2 = r2 * Math.cos(i);
            double y2 = r2 * Math.sin(i);

            graphics.setColor(Color.getHSBColor((float)(i/2/Math.PI), 1, 1));
            graphics.draw(new Line2D.Double(x1, y1, x2, y2));
        }

    }
    
    
    
    public static void main(String[] args) {
        launch(Rainbow.class);
    }

}
