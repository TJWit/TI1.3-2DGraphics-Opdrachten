import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Lines {
    private ArrayList<Point2D> firstPoints = new ArrayList();
    private ArrayList<Point2D> secondPoints = new ArrayList();

    private int counter;
    private int speed;
    private int index;
    private boolean done;

    public Lines(int speed) {
        this.counter = 0;
        this.index = 0;
        this.done = false;
        this.speed = speed;
    }

    public void update(Point2D first, Point2D second) {

        this.counter++;

        if (this.counter % this.speed == 0) {

            if (this.done) {
                this.firstPoints.set(this.index, first);
                this.secondPoints.set(this.index, second);
            } else {
                this.firstPoints.add(this.index, first);
                this.secondPoints.add(this.index, second);
            }

            if (this.index == 59) {
                this.index = 0;
                this.done = true;
            } else {
                this.index++;
            }

        }



    }

    public void draw(FXGraphics2D graphics) {

        for (Point2D firstPoint : firstPoints) {
            int index = firstPoints.indexOf(firstPoint);
            graphics.setColor(Color.WHITE);
            graphics.drawLine((int)firstPoint.getX(), (int)firstPoint.getY(), (int)secondPoints.get(index).getX(), (int)secondPoints.get(index).getY());
        }

    }
}
