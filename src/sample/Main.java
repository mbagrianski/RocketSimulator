package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {
    public int width = 1000, height = 800;
    int panePrefX = 650, panePrefY = 1000;

    double backgroundX = 0, backgroundY = -1330;
    double a = 0;

    private long lastUpdate = 0 ;
    int count = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Image flame = new Image(new FileInputStream("C:\\Users\\mbagr\\IdeaProjects\\RocketSimulator\\src\\sample\\SoyuzTMA.gif"));
        javafx.scene.image.ImageView Stage1 = new javafx.scene.image.ImageView(flame);



        Group rocketPanel = new Group();

        primaryStage.setTitle("Rocket Mission Simulator");

        Soyuz2_0 soyuz = new Soyuz2_0();
        soyuz.scale();

        Scene scene = new Scene(rocketPanel, width, height);
        primaryStage.setScene(scene);

        new AnimationTimer() {
            @Override
            public void start() {
                lastUpdate = System.nanoTime();
                super.start();
            }
            double t = 0;

            @Override
            public void handle(long now) {
                long elapsedNanoSeconds = now - lastUpdate;
                double elapsedSeconds = elapsedNanoSeconds / 1_000_000_000.0;

                rocketPanel.getChildren().clear();

                try {
                    soyuz.Mission(elapsedNanoSeconds, rocketPanel);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                lastUpdate= now;

            }
        }.start();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
