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

public class Rainbow extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage stage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        stage.setScene(new Scene(mainPane));
        stage.setTitle("Rainbow");
        stage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.translate(this.canvas.getWidth()/2, this.canvas.getHeight()/2);
        graphics.setBackground(Color.white);
        graphics.clearRect(-2*(int)canvas.getWidth(), -2*(int)canvas.getHeight(), 20*(int)canvas.getWidth(), 20*(int)canvas.getHeight());

        Font font = new Font("Arial", Font.PLAIN, 90);
//        graphics.draw(AffineTransform.getTranslateInstance(100,100).createTransformedShape(shape));

        String word = "regenboog";
        for (int i = 0; i < word.length(); i++) {
            double angle = (i+0.00001) / (double)word.length() * 3.14 - 1.57;
            double x = (i+0.00001) / (double)word.length() * 400-200;
            double y = Math.sin((i+0.00001) / (double)word.length() * 3.14) * -100;
            graphics.setColor(Color.getHSBColor((float)Math.sin((i+0.00001) / (double)word.length()), 1, 1));


            Shape letter = font.createGlyphVector(graphics.getFontRenderContext(), word.substring(i, i+1)).getOutline();
            AffineTransform transform = new AffineTransform();
            transform.translate(x, y);
            transform.rotate(angle);
            Shape transformedShape = transform.createTransformedShape(letter);
            graphics.fill(transformedShape);
            graphics.setColor(Color.BLACK);
            graphics.draw(transformedShape);
        }
    }


    public static void main(String[] args)
    {
        launch(Rainbow.class);
    }

}
