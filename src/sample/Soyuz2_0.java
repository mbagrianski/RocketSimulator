package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

        Stage2atm.setFitHeight(250);
        Stage2atm.setPreserveRatio(true);

        Stage2trns.setFitHeight(220);
        Stage2trns.setPreserveRatio(true);

        Stage3trns.setFitHeight(75);
        Stage3trns.setPreserveRatio(true);

        Capsuletrns.setFitHeight(40);
        Capsuletrns.setPreserveRatio(true);

        Capsule.setFitHeight(40);
        Capsule.setPreserveRatio(true);
    }



    private static ImageView[] imageSequence = {Stage1, Stage2atm, Stage2trns, Stage3trns, Capsuletrns, Capsule};

    public Soyuz2_0() throws FileNotFoundException {
        super(imageSequence);
    }
}
