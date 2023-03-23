import java.awt.*;
import java.awt.geom.*;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Mirror extends Application {
    ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Mirror");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate(canvas.getWidth()/2,canvas.getHeight()/2);

        graphics.drawLine(-500,0,500,0);
        graphics.drawLine(0,-500,0,500);

        graphics.drawLine(-300,750,300,-750);

        Area block = new Area(new Rectangle2D.Double(-50,-200,100,100));
        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.atan((double)10/25));
        tx.translate(-50,0);

        Area mirrored = new Area(block);
        AffineTransform tx2 = new AffineTransform();
        tx2.rotate(Math.PI/2);
        tx2.rotate(Math.atan((double)10/25));
        tx2.translate(-150,100);
        mirrored.transform(tx2);

        block.transform(tx);
        graphics.draw(block);
        graphics.draw(mirrored);



    }


    public static void main(String[] args)
    {
        launch(Mirror.class);
    }

}
