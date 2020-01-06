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

    private long lastUpdate = 0 ;
    int count = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Soyuz2_0 soyuz = new Soyuz2_0();
        soyuz.scale();

        Group root = new Group();
        HBox hBox = new HBox();

        Pane rocketPanel = new Pane();

        VBox RocketPanel = new VBox();
        RocketPanel.getChildren().addAll(rocketPanel);
        RocketPanel.setPrefSize(600, 800);

        VBox ButtonPanel = new VBox();

        Button launch = new Button("Launch");
        launch.setPrefSize(100, 50);
        launch.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                System.out.println("Launched");
                soyuz.launch(0);
            }
        });
        ButtonPanel.getChildren().addAll(launch);

        primaryStage.setTitle("Rocket Mission Simulator");

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
                    soyuz.Mission((long) elapsedSeconds, rocketPanel);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


            }
        }.start();

        hBox.getChildren().addAll(RocketPanel, ButtonPanel);
        root.getChildren().addAll(hBox);
        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
