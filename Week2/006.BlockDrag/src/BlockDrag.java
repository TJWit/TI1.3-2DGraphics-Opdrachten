import java.awt.*;
import java.awt.geom.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class BlockDrag extends Application {
    ResizableCanvas canvas;
    private ArrayList<Block> blocks = new ArrayList<>(
            Arrays.asList(
                new Block(new Point2D.Double(100,100), 64, Color.red),
                new Block(new Point2D.Double(300,100), 64, Color.red),
                new Block(new Point2D.Double(100,300), 64, Color.green),
                new Block(new Point2D.Double(200,400), 64, Color.yellow),
                new Block(new Point2D.Double(500,300), 64, Color.pink)
            ));

    private Block current;
    private Point2D oldPoint;
    private Point2D newPoint;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Block Dragging");
        primaryStage.show();

        canvas.setOnMousePressed(e -> mousePressed(e));
        canvas.setOnMouseReleased(e -> mouseReleased(e));
        canvas.setOnMouseDragged(e -> mouseDragged(e));

        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        for (Block block : this.blocks) {
            block.draw(graphics);
        }
    }


    public static void main(String[] args) {
        launch(BlockDrag.class);
    }

    private void mousePressed(MouseEvent e) {
        Point2D mouseLocation = new Point2D.Double(e.getX(), e.getY());
        this.oldPoint = mouseLocation;

        System.out.println(mouseLocation);

        for (Block block : this.blocks) {
            if (block.getLocation().getX() <= mouseLocation.getX() && mouseLocation.getX() <= (block.getLocation().getX() + block.getSize()) && block.getLocation().getY() <= mouseLocation.getY() && mouseLocation.getY() <= (block.getLocation().getY() + block.getSize())) {

                this.current = block;

                System.out.println(this.current);
                break;
            } else {
                this.current = null;
            }
        }
    }

    private void mouseReleased(MouseEvent e) {
        try {
            Point2D newLocation = new Point2D.Double((this.current.getLocation().getX() + (this.newPoint.getX() - this.oldPoint.getX())), (this.current.getLocation().getY() + (this.newPoint.getY() - this.oldPoint.getY())));
            this.current.setLocation(newLocation);
            this.draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        } catch (NullPointerException d) {

        }
    }

    private void mouseDragged(MouseEvent e) {
        Point2D mouseLocation = new Point2D.Double(e.getX(), e.getY());
        this.newPoint = mouseLocation;
    }

}
