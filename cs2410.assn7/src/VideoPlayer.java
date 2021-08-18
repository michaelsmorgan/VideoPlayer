import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;


/**
 * Created by Michael Morgan on 4/13/2017.
 * @version 1.0
 */
public class VideoPlayer extends Application {
    String fileName;
    TopMenuBar menuBar;
    VideoPane videoPane = new VideoPane(null);
    ControlsBar controls = new ControlsBar();
    BorderPane pane = new BorderPane();
    private File mediaFile;
    private Stage primaryStage;
    private Media media;
    MediaPlayer player;
    Scene scene = new Scene(pane);

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Media Player");
        this.primaryStage = primaryStage;
        menuBar = new TopMenuBar();

        pane.setPrefSize(700, 550);
        pane.setBottom(controls);
        pane.setTop(menuBar);
        pane.setCenter(videoPane);

        initHandlers();

        primaryStage.setScene(scene);
        primaryStage.show();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("For extra credit:\n" + "Space bar pauses and plays video\n" + "Graphics have been added to play/pause/stop buttons and menu items");
        alert.show();
        Alert temp = new Alert(Alert.AlertType.INFORMATION);
        temp.setContentText("Everything seems to work, except the trackbar moving while the video plays. You can still use the trackbar to move through the video");
        temp.show();

    }

    /**
     * initializes the handlers for the different buttons and menu items
     */
    private void initHandlers() {
        controls.play.setOnAction(this::playButton);
        controls.stop.setOnAction(this::stopButton);
        controls.volumeSlider.setOnMouseDragged(this::volumeSet);
        controls.timeLapse.setOnMouseDragged(this::timeSet);
        menuBar.open.setOnAction(this::openFile);
        menuBar.close.setOnAction(this::closeFile);
        menuBar.exit.setOnAction(this::exitRequest);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, KeyEvent -> {
            if (KeyEvent.getCode() == KeyCode.SPACE) {
                if (mediaFile != null) {
                    controls.changeImg();
                    if (controls.playing()) {
                        videoPane.play();
                    } else {
                        videoPane.pause();
                    }
                }
            }
        });
    }

    /**
     *
     * @param e - this method makes the video play and pause
     */
    private void playButton(ActionEvent e) {
        controls.changeImg();
        if (mediaFile != null) {
            if (controls.playing()) {
                videoPane.play();
            } else {
                videoPane.pause();
            }
        }
    }

    /**
     *
     * @param e - this method stops the video and returns it back to the beginning
     */
    private void stopButton(ActionEvent e) {
        if (mediaFile != null) {
            videoPane.pause();
            controls.timeLapse.setValue(0);
            videoPane.mediaPlayer.seek(new Duration(0));
            if (controls.playing()) {
                controls.changeImg();
            }
        }
    }

    /**
     *
     * @param e - this method changes the volume of the video
     */
    private void volumeSet(MouseEvent e) {
        if (mediaFile != null) {
            videoPane.changeVol(controls.getVolume());
        }
    }

    /**
     *
     * @param e - this sets the time of the video based on the position of the timelapse slider
     */
    private void timeSet(MouseEvent e) {
        if (mediaFile != null) {
            videoPane.setTime(controls.currentTime());
        }
    }

    /**
     *
     * @param e - this method allows the user to open a video file to display
     */
    private void openFile(ActionEvent e) {
        Stage newStage = new Stage();
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose A File To Open");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.m4v", "*.m4a"));
        try {
            mediaFile = chooser.showOpenDialog(newStage);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        if (mediaFile != null) {
            fileName = mediaFile.toURI().toString();
            media = new Media(fileName);
            player = new MediaPlayer(media);
            player.setOnReady(new Runnable() {
                @Override
                public void run() {
                    controls.setTimeLapse(videoPane.getDuration());
                }
            });
            videoPane = new VideoPane(player);

            pane.setCenter(videoPane);
        }

    }

    /**
     *
     * @param e - this method closes the video
     */
    private void closeFile(ActionEvent e) {
        videoPane = new VideoPane(null);
        pane.setCenter(videoPane);
        controls.timeLapse.setValue(0);
    }

    /**
     *
     * @param e - this method closes the program
     */
    private void exitRequest(ActionEvent e) {
        primaryStage.close();
    }
}