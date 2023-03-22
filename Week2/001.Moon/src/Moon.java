import java.awt.*;
import java.awt.geom.*;

import javafx.application.Application;

import static java.awt.geom.Arc2D.OPEN;
import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Moon extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Moon");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate(this.canvas.getWidth()/2, this.canvas.getHeight()/2);
//        Arc2D moon = new Arc2D.Double(50,50,100,100,240,110,0);

        GeneralPath moon = new GeneralPath();
        moon.moveTo(100 * Math.cos(240* Math.PI / 180), -100 * Math.sin(240* Math.PI / 180));
        for (double i = 241; i <= 460; i++){
            double rad1 = (i-0.5) * Math.PI / 180;
            double rad2 = i * Math.PI / 180;
            double x1 = 100 * Math.cos(rad1);
            double y1 = -100 * Math.sin(rad1);
            double x2 = 100 * Math.cos(rad2);
            double y2 = -100 * Math.sin(rad2);
            moon.quadTo(x1,y1,x2,y2);
        }

        moon.moveTo(100 * Math.cos(240* Math.PI / 180), -100 * Math.sin(240* Math.PI / 180));
        moon.quadTo(50,0,100 * Math.cos(100 * Math.PI / 180),-100 * Math.sin(100 * Math.PI / 180));

        System.out.println(100*Math.cos(240));

        graphics.draw(moon);
    }


    public static void main(String[] args)
    {
        launch(Moon.class);
    }

}
