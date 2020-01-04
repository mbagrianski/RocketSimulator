package sample;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;

public interface Operations {
    public void init();
    public Group Mission(long time, Group group) throws FileNotFoundException;

    public void launch(long time);
    public void drawRocket(int i, Group group);
    public void drawFlame(Group group);
    public void motion(double x_shift, double y_shift, double angle, double roll);
    public void motion(double x_accel, double y_accel);
    public void stage();
    public void setPayload();
}
