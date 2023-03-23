import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Colors extends Application {
    private ResizableCanvas canvas;
    private ArrayList<Color> colors = new ArrayList<>(
            Arrays.asList(
                    Color.black,
                    Color.blue,
                    Color.cyan,
                    Color.darkGray,
                    Color.gray,
                    Color.green,
                    Color.lightGray,
                    Color.magenta,
                    Color.orange,
                    Color.pink,
                    Color.red,
                    Color.white,
                    Color.yellow
            ));

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Colors");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        Area square = new Area(new Rectangle2D.Double(0,0,64,64));

        for (int i = 0; i < 13; i++) {
            graphics.setColor(Color.black);
            graphics.draw(square);
            graphics.setColor(colors.get(i));
            graphics.fill(square);
            graphics.translate(64, 0);
        }
    }


    public static void main(String[] args)
    {
        launch(Colors.class);
    }

}
