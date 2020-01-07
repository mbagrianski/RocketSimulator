package sample;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Rocket implements Operations {

    /**public int width = 1000, height = 800; **/

    private Image launchGIF = new Image(new FileInputStream(
            "C:\\Users\\mbagr\\IdeaProjects\\RocketSimulator\\src\\sample\\launch1.gif"));
    private ImageView LaunchGIF = new ImageView(launchGIF);


    Pane gifA = new Pane();
    Pane gifB = new Pane();


    private Image flame = new Image(new FileInputStream(
            "C:\\Users\\mbagr\\IdeaProjects\\RocketSimulator\\src\\sample\\flame.gif"));
    private ImageView Flame = new ImageView(flame);

    private Image background1 = new Image(new FileInputStream(
            "C:\\Users\\mbagr\\IdeaProjects\\RocketSimulator\\src\\sample\\backgroundImages\\launchpad.png"));
    private ImageView Background1 = new ImageView(background1);

    private Image background2 = new Image(new FileInputStream(
            "C:\\Users\\mbagr\\IdeaProjects\\RocketSimulator\\src\\sample\\backgroundImages\\pad_2.png"));
    private ImageView Background2 = new ImageView(background2);

    private Image background3 = new Image(new FileInputStream(
            "C:\\Users\\mbagr\\IdeaProjects\\RocketSimulator\\src\\sample\\backgroundImages\\pad_3.png"));
    private ImageView Background3 = new ImageView(background3);

    private ImageView currentIMG;
    protected int currentNUM;

    double accelFactor = 0.003;
    double rocketX = 261, rocketY = 600, altitude = 0, velocity = 0;
    double backgroundX, backgroundY = -6202+800;
    double rotatedAngle, rollAngle;
    boolean runthru = false;

    String type;
    private int type_num;

    private long startTime, elapsedTime;

    boolean launched = false;
    private ImageView[] imageSequence;
    VBox vbox = new VBox();

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
    public Group Mission(long time, Pane group) throws FileNotFoundException {
        elapsedTime = time-startTime;
            drawBackground(time, group);
            if (launched) {
                motion(0, velocity, rotatedAngle, rollAngle);
                drawFlame(0, group);
                velocity+= accelFactor;

                if(elapsedTime < 4) setGIFA(LaunchGIF);
                if (elapsedTime > 4) clearGIFA();
                if(2 < elapsedTime && elapsedTime < 30) accelFactor = 0.005;
            }
            drawRocket(currentNUM, group);
        return null;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getTime() {
        return null;
    }

    @Override
    public String getSpeed() {
        return String.valueOf(velocity);
    }

    @Override
    public String getPos() {
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
    public void drawRocket(int current, Pane group) {
        currentIMG = imageSequence[current];

        img_disp_X = currentIMG.computeAreaInScreen()/currentIMG.getFitHeight();
        img_disp_Y = currentIMG.getFitHeight();

        currentIMG.setX(rocketX+currentIMG.getFitWidth());
        currentIMG.setY(rocketY-currentIMG.getFitHeight());
        currentIMG.setRotate(rotatedAngle);

        group.getChildren().add(currentIMG);
    }

    @Override
    public void drawFlame(int current, Pane group) {
        switch (current){
            case 0:
                Flame.setX(rocketX - 28);
                Flame.setY(rocketY - 14);
                Flame.setFitWidth(125);
                Flame.setPreserveRatio(true);
                group.getChildren().add(Flame);
        }
    }

    @Override
    public void drawBackground(long time, Pane group) {
        Background1.setX(backgroundX);
        Background1.setY(backgroundY);
        Background2.setX(backgroundX);
        Background2.setY(backgroundY - 6300);
        Background3.setX(backgroundX);
        Background3.setY(backgroundY - 6300 - 6912);

        group.getChildren().addAll(Background1, Background2, Background3);
    }

    @Override
    public Pane getGIFA() {
        gifA.setPrefSize(195, 200);
        gifA.setStyle("-fx-background-color:black");
        return gifA;
    }

    @Override
    public Pane setGIFA(ImageView gif) {
        gif.setPreserveRatio(true);
        gif.setFitWidth(195);
        gifA.getChildren().clear();
        gifA.getChildren().addAll(gif);
        return null;
    }

    @Override
    public void clearGIFA() {
        gifA.getChildren().clear();
    }

    @Override
    public Pane getGIFB() {
        gifB.setPrefSize(195, 200);
        gifB.setStyle("-fx-background-color:black");
        return gifB;
    }

    @Override
    public Pane setGIFB(ImageView gif) {
        gif.setPreserveRatio(true);
        gif.setFitWidth(195);
        gifB.getChildren().clear();
        gifB.getChildren().addAll(gif);
        return null;
    }

    @Override
    public void clearGIFB() {
        gifB.getChildren().clear();
    }

    @Override
    public void motion(double x_shift, double y_shift, double angle, double roll) {
        if(rocketY > 400){
            rocketX += x_shift;
            rocketY -= y_shift;
        }
        if(rocketY <= 400){
            backgroundX -= x_shift;
            backgroundY += y_shift;
        }
        rotatedAngle += angle;
        rollAngle += roll;
    }

    @Override
    public void motion(double x_accel, double y_accel) {
    }

    @Override
    public void stage() {
        currentNUM++;
    }

    @Override
    public void setPayload() {

    }
}
