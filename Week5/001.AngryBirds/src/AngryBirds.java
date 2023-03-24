
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.nio.BufferOverflowException;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import javax.imageio.ImageIO;

public class AngryBirds extends Application {

    private ResizableCanvas canvas;
    private World world;
    private MousePicker mousePicker;
    private Camera camera;
    private boolean debugSelected = false;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    private BufferedImage background;
//    private GameObject bird;

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane mainPane = new BorderPane();

        try {
            this.background = ImageIO.read(getClass().getResource("/images/background.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add debug button
        javafx.scene.control.CheckBox showDebug = new CheckBox("Show debug");
//        showDebug.setOnAction(e -> {
//            debugSelected = showDebug.isSelected();
//        });
//        mainPane.setTop(showDebug);

        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        camera = new Camera(canvas, g -> draw(g), g2d);
        mousePicker = new MousePicker(canvas);

        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1) {
                    last = now;
                }
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane, 1920, 1080));
        stage.setTitle("Angry Birds");
        stage.show();
        draw(g2d);
    }

    public void init() {
        world = new World();
        world.setGravity(new Vector2(0, -9.8));

        Body bird = new Body();
        BodyFixture fixture = new BodyFixture(Geometry.createCircle(0.25));
        fixture.setRestitution(.25);
        bird.addFixture(fixture);
        bird.getTransform().setTranslation(new Vector2(-5,-3.5));
        bird.setMass(MassType.NORMAL);
        world.addBody(bird);

        Body box = new Body();
        BodyFixture fixtureBox = new BodyFixture(Geometry.createRectangle(0.45,0.45));
        fixtureBox.setRestitution(.25);
        box.addFixture(fixtureBox);
        box.getTransform().setTranslation(new Vector2(4,-2.5));
        box.setMass(MassType.NORMAL);
        world.addBody(box);

        Body box2 = new Body();
        box2.addFixture(fixtureBox);
        box2.getTransform().setTranslation(new Vector2(4,-3));
        box2.setMass(MassType.NORMAL);
        world.addBody(box2);

        Body box3 = new Body();
        box3.addFixture(fixtureBox);
        box3.getTransform().setTranslation(new Vector2(4,-3.5));
        box3.setMass(MassType.NORMAL);
        world.addBody(box3);

        Body ground = new Body();
        BodyFixture fixtureGround = new BodyFixture(Geometry.createRectangle(20,1));
        fixtureGround.setRestitution(.25);
        ground.addFixture(fixtureGround);
        ground.getTransform().setTranslation(new Vector2(0,-5.20));
        ground.setMass(MassType.INFINITE);

        world.addBody(ground);

        gameObjects.add(new GameObject("/images/bird.png", bird, new Vector2(0,0), 0.15));
        gameObjects.add(new GameObject("/images/box.png", box, new Vector2(0,0), 0.15));
        gameObjects.add(new GameObject("/images/box.png", box2, new Vector2(0,0), 0.15));
        gameObjects.add(new GameObject("/images/box.png", box3, new Vector2(0,0), 0.15));
    }

    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        AffineTransform backTX = graphics.getTransform();
        backTX.scale(0.25, 0.25);
        graphics.drawImage(this.background, backTX, null);

        AffineTransform originalTransform = graphics.getTransform();

        graphics.setTransform(camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()));
        graphics.scale(1, -1);

        for (GameObject go : gameObjects) {
            go.draw(graphics);
        }

        if (debugSelected) {
            graphics.setColor(Color.blue);
            DebugDraw.draw(graphics, world, 100);
        }

        graphics.setTransform(originalTransform);
    }

    public void update(double deltaTime) {
        mousePicker.update(world, camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()), 100);
        world.update(deltaTime);
    }

    public static void main(String[] args) {
        launch(AngryBirds.class);
    }

}
