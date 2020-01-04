package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Rocket implements Operations {

    private Image flame = new Image(new FileInputStream(
            "C:\\Users\\mbagr\\IdeaProjects\\RocketSimulator\\src\\sample\\rocket_plume.png"));
    private ImageView Flame = new ImageView(flame);

    private ImageView currentIMG;
    protected int currentNUM;

    double rocketX = 0, rocketY = 800, altitude = 0, velocity = 0;
    double flameX, flameY;
    double rotatedAngle, rollAngle;
    boolean runthru = false;

    private String type;
    private int type_num;

    private long startTime;

    private boolean launched = true;
    private ImageView[] imageSequence;

    private double img_disp_X, img_disp_Y;


    public Rocket(ImageView imageSequence[]) throws FileNotFoundException {
        this.imageSequence = imageSequence;
    }

    @Override
    public void init() {
        switch (this.imageSequence.length){
            case 6:
                this.type = "Soyuz2_0";
                this.type_num = 0;
        }
    }

    @Override
    public Group Mission(long time, Group group) throws FileNotFoundException {
            velocity+= 0.003;

            if (launched) {
                motion(0, velocity, 0, 0);
            }

            drawRocket(0, group);

        return null;
    }

    @Override
    public void launch(long time) {
        if(!launched){
            this.launched = true;
            this.startTime = time;
        }
    }

    @Override
    public void drawRocket(int current, Group group) {
        currentIMG = imageSequence[current];

        img_disp_X = currentIMG.computeAreaInScreen()/currentIMG.getFitHeight();
        img_disp_Y = currentIMG.getFitHeight();

        currentIMG.setX(rocketX+currentIMG.getFitWidth());
        currentIMG.setY(rocketY-currentIMG.getFitHeight());
        currentIMG.setRotate(rotatedAngle);

        group.getChildren().add(currentIMG);
    }

    @Override
    public void drawFlame(Group group) {
        Flame.setX(rocketX+30);
        Flame.setY(rocketY);
        Flame.setFitWidth(50);
        Flame.setPreserveRatio(true);
        group.getChildren().add(Flame);
    }

    @Override
    public void motion(double x_shift, double y_shift, double angle, double roll) {
        rocketX += x_shift;
        rocketY -= y_shift;
        rotatedAngle += angle;
        rollAngle += roll;
    }

    @Override
    public void motion(double x_accel, double y_accel) {
    }

    @Override
    public void stage() {

    }

    @Override
    public void setPayload() {

    }
}
