package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class Rocket implements Operations {

    /**public int width = 1000, height = 800; **/
    double throttleVal = 1;

    Image launchGIF = new Image("sample/GIF/launch1.gif");
    ImageView LaunchGIF = new ImageView(launchGIF);


    Pane gifA = new Pane();
    Pane gifB = new Pane();


    Image flame = new Image("sample/GIF/flame.gif");
    ImageView Flame = new ImageView(flame);

    private Image background1 = new Image("sample/backgroundImages/launchpad.png");
    private ImageView Background1 = new ImageView(background1);

    private Image background2 = new Image("sample/backgroundImages/pad_2.png");
    private ImageView Background2 = new ImageView(background2);

    private Image background3 = new Image("sample/backgroundImages/pad_3.png");
    private ImageView Background3 = new ImageView(background3);

    private ImageView currentIMG;
    int currentNUM;

    double accelFactor;
    double rocketX = 266, rocketY = 655, altitude = 0, velocity = 0;
    double backgroundX, backgroundY = -6202+800;
    double rotatedAngle, rollAngle;
    boolean runthru = false;
    
    double rocketXdisp, rocketYdisp, flameXdisp, flameYdisp;

    String type;
    private int type_num;

    long startTime, elapsedTime;

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
        this.Mission(time, group);
        return null;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public double getTime() {
        return (Math.round(elapsedTime*100.0)/100.0);
    }

    @Override
    public double getSpeed() { //return velocity in m/s
        return Math.round(velocity*20000.0*0.195)/100.0; //convert to approximate scale speed (m/s)
    }

    @Override
    public String getPos() {
        return null;
    }

    @Override
    public double getAccel() {
        return Math.round(accelFactor*20000.0*0.195)/100.0;
    }

    @Override
    public void launch(long time) {
        if(!launched){
            launched = true;
            startTime = time;
        }
    }

    @Override
    public void drawRocket(int current, Pane group) {    	
        currentIMG = imageSequence[current];
        
        img_disp_X = currentIMG.computeAreaInScreen()/currentIMG.getFitHeight();
        img_disp_Y = currentIMG.getFitHeight();            
         
        switch(currentNUM) {
        case 0:
        	rocketXdisp = 0;
        	rocketYdisp = 0;
        	break;
        case 1:
        	rocketXdisp = 7;
        	rocketYdisp = -10;
        	break;
        case 3:
        	rocketXdisp = 7;
        	rocketYdisp = -135;
        }
        currentIMG.setX(rocketX+currentIMG.getFitWidth()+rocketXdisp);
        currentIMG.setY(rocketY-currentIMG.getFitHeight()+rocketYdisp);
        currentIMG.setRotate(rotatedAngle);
        group.getChildren().add(currentIMG);
        //currentIMG.setX(rocketX+currentIMG.getFitWidth());
        //currentIMG.setY(rocketY-currentIMG.getFitHeight());
        //currentIMG.setRotate(rotatedAngle);

        
    }

    @Override
    public void drawFlame(int current, Pane group) {
        this.drawFlame(current, group);
    }

    @Override
    public void drawBackground(long time, Pane group) {
        Background1.setX(backgroundX);
        Background1.setY(backgroundY);
        Background1.setFitWidth(600);
        Background1.setPreserveRatio(true);
        Background2.setX(backgroundX);
        Background2.setY(backgroundY - 120000);
        Background2.setFitWidth(600);
        Background2.setFitHeight(120000);
        Background3.setX(backgroundX);
        Background3.setY(backgroundY - 120010 - 6912);
        Background3.setFitWidth(600);
        Background3.setPreserveRatio(true);

        group.getChildren().addAll(Background1, Background2, Background3);
    }

    @Override
    public Pane getGIFA() {
        gifA.setPrefSize(195, 200);
        gifA.setStyle("-fx-background-color:darkgray");
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
        if(rocketY <= 400 && backgroundY < 127000){
            backgroundX -= x_shift;
            backgroundY += y_shift;
        }
        rotatedAngle += angle;
        rollAngle += roll;
        System.out.println(backgroundY);
    }

    @Override
    public void motion(double x_accel, double y_accel) {
    }

    @Override
    public void stage() {
        currentNUM++;
        switch (currentNUM){
            case 1:
                rocketX += 15;

        }
    }

    @Override
    public void setPayload() {

    }
}
