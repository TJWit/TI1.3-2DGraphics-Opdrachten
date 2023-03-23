import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class YingYang extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Ying Yang");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        Area one = new Area(new Ellipse2D.Double(-100,-100,200,200));
        Area two = new Area(new Rectangle(0, -100, 100, 200));
        Area three = new Area(new Ellipse2D.Double(-50,-100,100,100));
        Area four = new Area(new Ellipse2D.Double(-50,0,100,100));
        Area five = new Area(new Ellipse2D.Double(-10,-60,20,20));
        Area six = new Area(new Ellipse2D.Double(-10,40,20,20));

        Area area = new Area(one);
        area.intersect(two);
        area.subtract(three);
        area.add(four);
        area.add(five);
        area.subtract(six);

        graphics.fill(area);
        graphics.draw(one);

    }


    public static void main(String[] args) {
        launch(YingYang.class);
    }

}
