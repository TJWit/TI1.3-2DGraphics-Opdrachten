import java.awt.*;
import java.awt.geom.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Graph extends Application {
    private Canvas canvas;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Graph");
        primaryStage.show();
    }
    
    
    public void draw(FXGraphics2D graphics) {
        double prevY = (1080-100);
        double prevX = 50;
        double y;

        graphics.draw(new Line2D.Double(50,980, 1920, 980));
        graphics.draw(new Line2D.Double(50, 980, 50, 0));

        for (double x = 52; x < 1820; x+=2) {
            y = 980 - (Math.pow(((x-50)/30), 3));
            graphics.draw(new Line2D.Double(prevX, prevY, x, y));
            prevX = x;
            prevY = y;
        }
    }
    
    
    
    public static void main(String[] args) {
        launch(Graph.class);
    }

}
