package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;

public class Rocket implements Operations {

    protected ImageView currentIMG;
    protected int currentNUM;
    double rocketX = 0, rocketY = 800;
    double flameX, flameY;
    String type;
    int type_num;

    protected long startTime;
    double accelX, accelY;
    protected double trajAccel, factor;

    protected boolean launched = false;
    protected ImageView[] imageSequence;

    double img_disp_X, img_disp_Y;

    public Rocket(ImageView imageSequence[]){
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
        if(launched){

        }
        drawRocket(0, group);
        rocketY--;
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
        this.currentIMG = this.imageSequence[current];

        img_disp_X = currentIMG.computeAreaInScreen()/currentIMG.getFitHeight();
        img_disp_Y = currentIMG.getFitHeight();

        currentIMG.setX(rocketX+currentIMG.getFitWidth());
        currentIMG.setY(rocketY-currentIMG.getFitHeight());

        group.getChildren().addAll(currentIMG);
    }

    @Override
    public ImageView drawFlame(double x, double y) throws FileNotFoundException {
        return null;
    }

    @Override
    public void accelerate() {

    }

    @Override
    public void stage() {

    }

    @Override
    public void setPayload() {

    }
}
