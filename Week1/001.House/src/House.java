import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Line2D;
import java.util.ArrayList;

public class House extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));

        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("House");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics) {
        graphics.draw(new Line2D.Double(100, 920, 800, 920));
        graphics.draw(new Line2D.Double(800, 920, 800, 480));
        graphics.draw(new Line2D.Double(800, 480, 450, 80));
        graphics.draw(new Line2D.Double(450, 80, 100, 480));
        graphics.draw(new Line2D.Double(100, 480, 100, 920));
        graphics.draw(new Line2D.Double(150, 920, 150, 620));
        graphics.draw(new Line2D.Double(150, 620, 300, 620));
        graphics.draw(new Line2D.Double(300, 620, 300, 920));
        graphics.draw(new Line2D.Double(350, 780, 750, 780));
        graphics.draw(new Line2D.Double(750, 780, 750, 620));
        graphics.draw(new Line2D.Double(750, 620, 350, 620));
        graphics.draw(new Line2D.Double(350, 620, 350, 780));
    }



    public static void main(String[] args) {
        launch(House.class);
    }



}
