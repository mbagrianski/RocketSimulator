package sample;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;

public interface Operations {
    public void init();
    public Group Mission(long time, Group group) throws FileNotFoundException;

    public void launch(long time);
    public void drawRocket(int i, Group group);
    public ImageView drawFlame(double x, double y) throws FileNotFoundException;
    public void accelerate();
    public void stage();
    public void setPayload();
}
