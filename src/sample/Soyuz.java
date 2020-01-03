package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Soyuz{

    Image stage1 = new Image(new FileInputStream("C:\\Users\\mbagr\\IdeaProjects\\RocketSimulator\\src\\sample\\soyuzImages\\soyuz_stage1.png"));
    Image stage2atm = new Image(new FileInputStream("C:\\Users\\mbagr\\IdeaProjects\\RocketSimulator\\src\\sample\\soyuzImages\\soyuz_stage2_atm.png"));
    Image stage2trns = new Image(new FileInputStream("C:\\Users\\mbagr\\IdeaProjects\\RocketSimulator\\src\\sample\\soyuzImages\\soyuz_stage2_transfer.png"));
    Image stage3trns = new Image(new FileInputStream("C:\\Users\\mbagr\\IdeaProjects\\RocketSimulator\\src\\sample\\soyuzImages\\soyuz_stage3_transfer.png"));
    Image capsuletrns = new Image(new FileInputStream("C:\\Users\\mbagr\\IdeaProjects\\RocketSimulator\\src\\sample\\soyuzImages\\soyuz_capsule_transfer.png"));
    Image capsule = new Image(new FileInputStream("C:\\Users\\mbagr\\IdeaProjects\\RocketSimulator\\src\\sample\\soyuzImages\\soyuz_capsule.png"));
    Image flame = new Image(new FileInputStream("C:\\Users\\mbagr\\IdeaProjects\\RocketSimulator\\src\\sample\\flame.gif"));

    ImageView Stage1 = new ImageView(stage1);
    ImageView Stage2atm = new ImageView(stage2atm);
    ImageView Stage2trns = new ImageView(stage2trns);
    ImageView Stage3trns = new ImageView(stage3trns);
    ImageView Capsuletrns = new ImageView(capsuletrns);
    ImageView Capsule = new ImageView(capsule);


    ImageView[] imageSequence = {Stage1, Stage2atm, Stage2trns, Stage3trns, Capsuletrns, Capsule};
    protected ImageView currentIMG;
    protected int currentNUM;
    double rocketX = 292, rocketY = 700;
    double flameX = rocketX+5, flameY = 2000;

    double accelX = 0, accelY = 0;
    protected double trajAccel, factor;
    boolean launched = false;

    public Soyuz() throws FileNotFoundException {
    }


/*
    public Soyuz() throws FileNotFoundException {
        super();
        super.imageSequence = imageSequence;
    }*/

    public void launch() {
        if(trajAccel == 0 && factor == 0) {
            this.trajAccel = 0.001;
            this.factor = 0.002;
            drawRocket(currentNUM);
        }else{
            this.trajAccel = trajAccel;
            this.factor = factor;
        }
        flameY = rocketY + 220;
        launched = true;
    }

    public Group draw() throws FileNotFoundException {
        Group assembly = new Group(drawRocket(currentNUM),
                drawFlame(rocketX+5, rocketY+148),
                drawFlame(rocketX+13, rocketY+148),
                drawFlame(rocketX-5, rocketY+148));
        return assembly;
    }

    public ImageView drawRocket(int i) {
        currentNUM = i;
        currentIMG = imageSequence[i];
        currentIMG.setX(rocketX + accelX);
        currentIMG.setY(rocketY + accelY);
        currentIMG.setFitWidth(40);
        currentIMG.setPreserveRatio(true);
        return currentIMG;
    }

    public ImageView drawFlame(double x, double y) throws FileNotFoundException {

        ImageView Flame = new ImageView(flame);
        Flame.setY(y + accelY);
        Flame.setX(x + accelX);
        Flame.setFitWidth(30);
        Flame.setPreserveRatio(true);
        return Flame;
    }

    public void accelerate() {
        if(launched){
            rocketY-= trajAccel;
            flameY-= trajAccel;
            trajAccel += factor;
        }
    }

    public void stage() {
        if(currentNUM < imageSequence.length-1){
            currentNUM++;
            drawRocket(currentNUM);
        }
    }

    public void setPayload() {

    }
}
