package application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sample.Operations;
import sample.Soyuz;

import java.io.*;
import java.lang.reflect.Array;


public class Rocket
{

    protected Class type;
    protected ImageView[] imageSequence;

    Image flame1 = new Image(new FileInputStream("C:\\Users\\mbagr\\IdeaProjects\\RocketSimulator\\src\\sample\\flame.gif"));

    protected int stages;
    protected String name;


    public Rocket(Pane pane) throws FileNotFoundException {}


    public Rocket() throws FileNotFoundException {

    }


}
