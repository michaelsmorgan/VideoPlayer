import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

/**
 * Created by Michael Morgan on 4/13/2017.
 * @version 1.0
 */
public class VideoPane extends StackPane {
    public MediaPlayer mediaPlayer;
    public MediaView mediaView = new MediaView();
    Duration duration;

    /**
     *
     * @param mp - MediaPlayer object passed in to use for playing a video
     */
    public VideoPane(MediaPlayer mp) {
        mediaPlayer = mp;
        this.setStyle("-fx-background-color: black;");
        mediaView.setMediaPlayer(mediaPlayer);
        this.getChildren().add(mediaView);
        mediaView.setFitHeight(400);
        mediaView.setPreserveRatio(true);

        if (mediaPlayer != null) {
            mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    duration = mediaPlayer.getMedia().getDuration();

                }
            });
        }
    }

    /**
     *
     * @param vol is used to set the volume of the application
     */
    public void changeVol(double vol) {
        mediaPlayer.setVolume(vol);
    }

    /**
     * this plays the selected video
     */
    public void play() {
        mediaPlayer.play();
    }

    /**
     * this pauses the video
     */
    public void pause() {
        mediaPlayer.pause();
    }

    /**
     *
     * @return returns either null or the duration of the video that has been selected
     */
    public Duration getDuration() {
        if (mediaPlayer != null) {
            return mediaPlayer.getMedia().getDuration();
        }
        return null;
    }

    /**
     *
     * @return returns either null or the point the selected video is currently at
     */
    public Duration getTime() {
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentTime();
        }
        return null;
    }

    /**
     *
     * @param slider is a value from the timelapse slider in the ControlsBar class
     */
    public void setTime(double slider) {
        double percent = slider / 100;
        mediaPlayer.seek(new Duration(mediaPlayer.getMedia().getDuration().toMillis() * percent));
    }

}
