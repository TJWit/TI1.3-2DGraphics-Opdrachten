import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Eindopdracht extends Application {
    private TextField v1;
    private TextField v2;
    private TextField v3;
    private TextField v4;
    private Canvas canvas;

    private Base base;
    private Arm_A arm_a;
    private Arm_B arm_b;
    private Arm_C arm_c;

    private int a;
    private int b;
    private int c;

    private Label l1;
    private Label l2;
    private Label l3;
    private Label l4;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.canvas = new Canvas(1920, 1080);
        FXGraphics2D graphics = new FXGraphics2D(canvas.getGraphicsContext2D());
        graphics.translate(this.canvas.getWidth()/2, this.canvas.getHeight()/2);

        VBox mainBox = new VBox();
        HBox topBar = new HBox();
        mainBox.getChildren().add(topBar);
        mainBox.getChildren().add(new Group(canvas));

        topBar.getChildren().add(v1 = new TextField("-20"));
        topBar.getChildren().add(l1 = new Label("90\t-90"));
        topBar.getChildren().add(v2 = new TextField("70"));
        topBar.getChildren().add(l2 = new Label("-135 - 135"));
        topBar.getChildren().add(v3 = new TextField("90"));
        topBar.getChildren().add(l3 = new Label("-135 - 135"));
        topBar.getChildren().add(v4 = new TextField("FALSE"));
        topBar.getChildren().add(l4 = new Label("Set to 'TRUE' for absolute angles."));

        v1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20;");
        v2.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20;");
        v3.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20;");
        v4.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20;");

        v1.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
        v2.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
        v3.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));
        v4.textProperty().addListener(e -> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));

        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now)
            {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(graphics);
            }
        }.start();


        primaryStage.setScene(new Scene(mainBox));
        primaryStage.setTitle("Robot Arm");
        primaryStage.show();
        draw(graphics);
    }

    public void init() {
        this.base = new Base(new Point2D.Double(0,300));
        this.arm_a = new Arm_A(this.base.getConnectPoint(), -20);
        this.arm_b = new Arm_B(this.arm_a.getConnectPoint(), 50);
        this.arm_c = new Arm_C(this.arm_b.getConnectPoint(), 140);
    }

    private void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate(this.canvas.getWidth()/2, this.canvas.getHeight()/2);

        this.base.draw(graphics);
        this.arm_b.draw(graphics);
        this.arm_a.draw(graphics);
        this.arm_c.draw(graphics);
    }

    private void update(double deltaTime) {
        try {
            this.a = Integer.parseInt(v1.getText());
            this.b = Integer.parseInt(v2.getText());
            this.c = Integer.parseInt(v3.getText());
        } catch (java.lang.NumberFormatException e) {
        }
        if (this.a > 90) {
            v1.setText("90");
        } else if (this.a < -90) {
            v1.setText("-90");
        }
        if (this.b > 135) {
            v2.setText("135");
        } else if (this.b < -135) {
            v2.setText("-135");
        }
        if (this.c > 135) {
            v3.setText("135");
        } else if (this.c < -135) {
            v3.setText("-135");
        }

        if (v4.getText().equals("TRUE")) {
            this.arm_a.update(a);
            this.arm_b.update((b), this.arm_a.getConnectPoint());
            this.arm_c.update((c), this.arm_b.getConnectPoint());
        } else {
            this.arm_a.update(a);
            this.arm_b.update((a+b), this.arm_a.getConnectPoint());
            this.arm_c.update((a+b+c), this.arm_b.getConnectPoint());
        }
    }


}
