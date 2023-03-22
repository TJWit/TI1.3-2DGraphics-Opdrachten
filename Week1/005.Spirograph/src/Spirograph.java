import java.awt.*;
import java.awt.geom.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Spirograph extends Application  {
    private TextField v1;
    private TextField v2;
    private TextField v3;
    private TextField v4;
    private Canvas canvas;
    
    @Override
    public void start(Stage primaryStage) throws Exception  {
        this.canvas = new Canvas(1920, 1080);
        FXGraphics2D graphics = new FXGraphics2D(canvas.getGraphicsContext2D());
        graphics.translate(this.canvas.getWidth()/2, this.canvas.getHeight()/2);
       
        VBox mainBox = new VBox();
        HBox topBar = new HBox();
        mainBox.getChildren().add(topBar);
        mainBox.getChildren().add(new Group(canvas));
        
        topBar.getChildren().add(v1 = new TextField("300"));
        topBar.getChildren().add(v2 = new TextField("2"));
        topBar.getChildren().add(v3 = new TextField("300"));
        topBar.getChildren().add(v4 = new TextField("10"));

        v1.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
        v2.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
        v3.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
        v4.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));

        
        draw(graphics);
        primaryStage.setScene(new Scene(mainBox));
        primaryStage.setTitle("Spirograph");
        primaryStage.show();
    }
    
    
    public void draw(FXGraphics2D graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(-1*(int)canvas.getWidth(), -1*(int)canvas.getHeight(), 2*(int)canvas.getWidth(), 2*(int)canvas.getHeight());
        graphics.setColor(Color.BLACK);

        double a;
        double b;
        double c;
        double d;
        double max = 500;
        double interval = 1/50f;

        try {
            a = Integer.parseInt(v1.getText()); // replace with your desired value
            b = Integer.parseInt(v2.getText()); // replace with your desired value
            c = Integer.parseInt(v3.getText()); // replace with your desired value
            d = Integer.parseInt(v4.getText()); // replace with your desired value
        } catch (java.lang.NumberFormatException e) {
            a = 0;
            b = 0;
            c = 0;
            d = 0;
        }

        for (double i = 0; i < max; i+=interval) {
            double x1 = a * Math.cos(b * i) + c * Math.cos(d * i);
            double y1 = a * Math.sin(b * i) + c * Math.sin(d * i);
            double x2 = a * Math.cos(b * i-interval) + c * Math.cos(d * i-interval);
            double y2 = a * Math.sin(b * i-interval) + c * Math.sin(d * i-interval);
            graphics.draw(new Line2D.Double(x1, y1, x2, y2));
        }
    }
    
    
    
    public static void main(String[] args) {
        launch(Spirograph.class);
    }

}
