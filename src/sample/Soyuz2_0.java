package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Soyuz2_0 extends Rocket {
    static {
        stage1 = new Image("sample/soyuzImages/soyuz_stage1.png");
        stage2atm = new Image("sample/soyuzImages/soyuz_stage2_atm.png");
        stage2trns = new Image("sample/soyuzImages/soyuz_stage2_transfer.png");
        stage3trns = new Image("sample/soyuzImages/soyuz_stage3_transfer.png");
        capsuletrns = new Image("sample/soyuzImages/soyuz_capsule_transfer.png");
        capsule = new Image("sample/soyuzImages/soyuz_capsule.png");
    }

    static Image stage1;
    static Image stage2atm;
    static Image stage3trns;
    static Image stage2trns;
    static Image capsuletrns;
    static Image capsule;

    private static ImageView Stage1 = new ImageView(stage1);
    private static ImageView Stage2atm = new ImageView(stage2atm);
    private static ImageView Stage2trns = new ImageView(stage2trns);
    private static ImageView Stage3trns = new ImageView(stage3trns);
    private static ImageView Capsuletrns = new ImageView(capsuletrns);
    private static ImageView Capsule = new ImageView(capsule);

    public void scale(){
        Stage1.setPreserveRatio(true);
        Stage1.setFitHeight(250);

        Stage2atm.setFitHeight(225);
        Stage2atm.setPreserveRatio(true);

        Stage2trns.setFitHeight(200);
        Stage2trns.setPreserveRatio(true);

        Stage3trns.setFitHeight(75);
        Stage3trns.setPreserveRatio(true);

        Capsuletrns.setFitHeight(40);
        Capsuletrns.setPreserveRatio(true);

        Capsule.setFitHeight(40);
        Capsule.setPreserveRatio(true);
    }

    public Group Mission(double time, Pane group) throws FileNotFoundException {
        double atmosphere = 1;
        drawBackground(time, group);
        
        if(countdownInit) {   	
        	elapsedTime = time-initTime -30;//the constant is the extra time before launch
        	if(elapsedTime >= 0) launch(time);
        }
        
        if(drawFlame) {
            drawFlame(currentNUM, group);
        }
        
        if (launched) {
        	countdownInit = false;
        	elapsedTime = time-startTime;

            motion(0, velocity*atmosphere, rotatedAngle, rollAngle);//atmosphere is a variable that
            //increases accel. after leaving earth's atmosphere
            if(elapsedTime > 40) atmosphere = time*time / (10.0 * time);

            if(elapsedTime > 70 && currentNUM < 1) stage();
            if(elapsedTime > 150 && currentNUM < 2) stage();
            if(elapsedTime > 200 && currentNUM < 3) stage();
            
            
            if(velocity > 200) {
            	logUpdate = "Throttle down for max dynamic pressure";
            }
            drawFlame = true;
            /**Mission timeline goes here**/

            velocity+= accelFactor*atmosphere;
            
            
            
            if(rocketY > originalRocketY) {
            	crash(group);
            }else if(backgroundY < originalBackgroundY) {
            	crash(group);
            }
        }
        
        drawRocket(currentNUM, group);
        return null;
    }
    
    
    @Override
    public void drawFlame(int current, Pane group) {

        switch (current){//draw flame differently depending on the stage
            case 0:
            	flameXdisp = -28;
            	flameYdisp = -14;
                Flame.setX(rocketX + flameXdisp);
                Flame.setY(rocketY + flameYdisp);
                Flame.setFitWidth(125);
                Flame.setPreserveRatio(true);
                break;
            case 1:
            	flameXdisp = -11;
            	flameYdisp = -14;
                Flame.setX(rocketX + flameXdisp);
                Flame.setY(rocketY + flameYdisp);
                Flame.setFitWidth(130);
                Flame.setFitHeight(200);
                break;
            case 3:
            	flameXdisp = 4;
            	flameYdisp = -136;
            	Flame.setFitWidth(75);
                Flame.setFitHeight(100);
                Flame.setX(rocketX + flameXdisp);
                Flame.setY(rocketY + flameYdisp);                           
                break;
        }
        group.getChildren().add(Flame);
    }
    
    @Override
	public String getUpdate(double time) {
		String text = "";

    	if(countdownInit || launched) { //detailed mission profile would go in this method (is printed on scrollpane)
    	    if(time < 10) drawFlame = false;
    		if(time > -13) {
    			text += "Autosequence start T-12.0\n";
    		}if(time > -11) {
    			text += "Standby for terminal count T-10\n";
    		}if(time > -6) {
    			text += "Go for main engine start T-5.0\n";
    			drawFlame = true;
    		}if(time > 0) {
    			text += "";
    		}if(time > -2) {
    			text += "Throttle up 75% T-1.0\n";
    		}if(time > 1) {
    			text += "Liftoff  T+0.0\n";
    		}if(time >= 5) {
                text += "Throttle up 95% T+5.0\n";
            }if(time >= 15) {
                text += "Approaching maximum dynamic pressure prepare for throttle down 60%\n";
            }if(time >= 19) {
                text += "Throttle down 60% T+20.0\n";
            }

    	}
    	return text;
	}

    private static ImageView[] imageSequence = {Stage1, Stage2atm, Stage2trns, Stage3trns, Capsuletrns, Capsule};

    public Soyuz2_0() throws FileNotFoundException {
        super(imageSequence);
    }
}
