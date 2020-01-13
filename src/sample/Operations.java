package sample;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;

public interface Operations {
    public void init();
    public Group Mission(long time, Pane group) throws FileNotFoundException;

    public String getType();
    public double getTime();
    public double getSpeed();
    public String getPos();
    public double getAccel();


    public void launch(long time);
    public void drawRocket(int stage, Pane group);
    public void drawFlame(int stage, Pane group);
    public void drawBackground(long time, Pane group);
    public Pane getGIFA();
    public Pane setGIFA(ImageView gif);
    public void clearGIFA();

    public Pane getGIFB();
    public Pane setGIFB(ImageView gif);
    public void clearGIFB();



    public void motion(double x_shift, double y_shift, double angle, double roll);
    public void motion(double x_accel, double y_accel);
    public void stage();
    public void setPayload();
}
