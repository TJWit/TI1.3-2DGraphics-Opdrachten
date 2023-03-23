import java.awt.*;
import java.awt.geom.*;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class GradientPaintExercise extends Application {
    private ResizableCanvas canvas;
    private Point2D focus;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);

        this.focus = new Point2D.Double(0,0);

        canvas.setOnMouseClicked((MouseEvent e) -> {
            this.focus = new Point2D.Double(e.getX(), e.getY());
        });

        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("GradientPaint");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        Area area = new Area(new Rectangle2D.Double(0,0,canvas.getWidth(),canvas.getHeight()));

        float[] fractions = { 0f, 1f };
        Color[] colors = { Color.RED, Color.BLUE };
        graphics.setPaint(new RadialGradientPaint(new Point2D.Double(canvas.getWidth()/2, canvas.getHeight()/2), 150f,new Point2D.Double(0,0), fractions, colors, MultipleGradientPaint.CycleMethod.NO_CYCLE));
        graphics.fill(area);
    }


    public static void main(String[] args) {
        launch(GradientPaintExercise.class);
    }

}
