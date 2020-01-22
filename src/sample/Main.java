package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {
    public int width = 1004, height = 800;

    private long lastUpdate = 0 ;
    int count = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        String uriString = new File("C:\\Users\\mbagr\\IdeaProjects\\RocketSimulator\\src\\sample\\audio\\SST_launch.mp3").toURI().toString();
        //for some reason this needs the absolute path. Don't know why.
        MediaPlayer player = new MediaPlayer( new Media(uriString));

        Soyuz2_0 rocket = new Soyuz2_0(); //create new soyuz object
        rocket.scale();
        rocket.init();

        Group root = new Group();
        HBox hBox = new HBox();
        hBox.setSpacing(5);

        Pane rocketPanel = new Pane();
        rocketPanel.setStyle("-fx-background-color:black");

        VBox RocketPanel = new VBox();
        RocketPanel.getChildren().addAll(rocketPanel);
        RocketPanel.setPrefSize(600, 800);

        VBox ButtonPanel = new VBox();

        HBox rowA = new HBox();
        HBox rowB = new HBox();
        HBox rowC = new HBox();
        HBox rowD = new HBox();

        Label title = new Label();
        title.setPrefSize(395, 40);
        title.setFont(new Font("Arial", 18));
        title.setStyle("-fx-background-color:lightgray");

        Label Velocity = new Label();
        Velocity.setPrefSize(230, 10);
        Velocity.setFont(new Font("Arial", 12));
        Velocity.setPadding(new Insets(5, 0, 0, 5));

        Label Acceleration = new Label();
        Acceleration.setPrefSize(230, 10);
        Acceleration.setFont(new Font("Arial", 12));
        Acceleration.setPadding(new Insets(0, 0, 0, 5));

        Label Pitch = new Label();
        Pitch.setPrefSize(230, 10);
        Pitch.setFont(new Font("Arial", 12));
        Pitch.setPadding(new Insets(0, 0, 0, 5));

        Label Roll = new Label();
        Roll.setPrefSize(230, 10);
        Roll.setFont(new Font("Arial", 12));
        Roll.setPadding(new Insets(0, 0, 0, 5));
        
        Label Altitude = new Label();
        Altitude.setPrefSize(230, 10);
        Altitude.setFont(new Font("Arial", 12));
        Altitude.setPadding(new Insets(0, 0, 0, 5));

        VBox labels = new VBox(Velocity, Acceleration, Pitch, Roll, Altitude);
        labels.setSpacing(5);

        Pane stats1 = new Pane();
        stats1.setPrefSize(240, 240);
        stats1.setStyle("-fx-background-color:darkgray");

        Pane stats2 = new Pane();
        stats2.setPrefSize(150, 240);
        stats2.setStyle("-fx-background-color:darkgray");
        Circle circle = new Circle();
        circle.setCenterX(75);
        circle.setCenterY(75);
        circle.setRadius(50);
        Line rollOffset = new Line(75, 75, 75, 25);
        rollOffset.setStroke(Color.RED);

        stats1.getChildren().addAll(labels);
        stats2.getChildren().addAll(circle, rollOffset);

        Button launch = new Button("Abort");
        launch.setStyle("-fx-background-color:tomato");
        launch.setPrefSize(100, 50);

        VBox clusters = new VBox();
        clusters.setSpacing(5);
        Button[] cluster = new Button[5];
        for(int i = 0; i< cluster.length; i++){
            cluster[i] = new Button();
            cluster[i].setPrefSize(50, 20);
            cluster[i].setText(String.valueOf(i+1));
            cluster[i].setStyle("-fx-background-color:green");
            clusters.getChildren().addAll(cluster[i]);
        }
        clusters.setPadding(new Insets(20, 0, 0, 0));

        rowA.getChildren().addAll(stats1, stats2);
        rowA.setSpacing(5);
        rowA.setPadding(new Insets(0, 0, 4, 0));

        Button countdownInit = new Button("Initialize\nCountdown");
        countdownInit.setPrefSize(100, 50);
        countdownInit.setStyle("-fx-background-color:olive");
        
        rowB.getChildren().addAll(launch, countdownInit);
        rowB.setSpacing(4);


        Slider throttle = new Slider(0, 100, 50);
        throttle.setPrefSize(50, 150);
        throttle.setMajorTickUnit(10);
        throttle.setShowTickLabels(true);
        throttle.setOrientation(Orientation.VERTICAL);
        throttle.setSnapToTicks(true);

        Label thLabel = new Label("Throttle");
        thLabel.setTextFill(Color.LIGHTGRAY); 
        thLabel.setPrefSize(50, 10);
        thLabel.setFont(new Font("Arial", 12));
        
        VBox Throttle = new VBox(thLabel, throttle);
        Throttle.setSpacing(5);
        
        Slider pitch = new Slider(-2, 2, 0);
        pitch.setPrefSize(50, 150);
        pitch.setMajorTickUnit(1);
        pitch.setShowTickLabels(true);
        pitch.setOrientation(Orientation.VERTICAL);
        pitch.setSnapToTicks(true);
        pitch.setPadding(new Insets(5, 0, 0, 0));

        Label ptLabel = new Label("Deg/s");
        ptLabel.setTextFill(Color.LIGHTGRAY);        
        ptLabel.setPrefSize(50, 10);
        ptLabel.setFont(new Font("Arial", 12));

        VBox Pitchlbl = new VBox(ptLabel, pitch);
        Throttle.setSpacing(5);
        
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setPrefWidth(200);
        textArea.setPrefHeight(165);

        ScrollPane log = new ScrollPane();
        log.setHbarPolicy(ScrollBarPolicy.NEVER);
        log.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        log.setPrefWidth(225);
        log.setPrefHeight(100);

        rowC.getChildren().addAll(Throttle, clusters, Pitchlbl, textArea);
        rowC.setSpacing(5);
        rowC.setPadding(new Insets(5, 0, 0, 0));


        //rowD.getChildren().addAll(rocket.getGIFA(), rocket.getGIFB());
        rowD.setSpacing(5);

        ButtonPanel.getChildren().addAll(title, rowA, rowB, rowC, rowD);
        ButtonPanel.setSpacing(5);

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

                rocket.accelFactor = throttle.getValue() / 10000 - 1/200.0;

                launch.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent e){
                        System.out.println("Launched");
                        rocket.launch(elapsedSeconds);
                    }
                });
                countdownInit.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent e){
                        player.play();
                        rocket.countDown(elapsedSeconds);
                    }
                });

                title.setText(" Mission control simulation: " + rocket.getType() + " T+ " + rocket.getTime());
                Velocity.setText("Velocity: "+ rocket.getSpeed()+" m/s");
                Acceleration.setText("Acceleration: "+ rocket.getAccel() * 49+" m/s^2");
                Pitch.setText("Pitch: "+ rocket.rotatedAngle +" deg");
                Roll.setText("Roll: "+ rocket.rollAngle +" deg");
                Altitude.setText("Altitude: "+ rocket.getAltitude() +" m");

                textArea.setText(rocket.getUpdate(rocket.getTime()));
                textArea.setScrollTop(10);
                log.setContent(textArea);

                for(int i = 0; i< cluster.length; i++){
                    int finalI = i;
                    cluster[i].setOnAction(new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent e){
                            cluster[finalI].setStyle("-fx-background-color:tomato");
                        }
                    });
                }
                rocketPanel.getChildren().clear();

                try {
                    rocket.Mission(elapsedSeconds, rocketPanel);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


            }
        }.start();

        hBox.getChildren().addAll(RocketPanel, ButtonPanel);
        hBox.setStyle("-fx-background-color:black");
        hBox.setPrefSize(width, height);
        root.getChildren().addAll(hBox);
        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
