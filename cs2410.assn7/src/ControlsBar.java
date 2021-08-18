import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Created by Michael Morgan on 4/13/2017.
 * @version 1.0
 */
public class ControlsBar extends VBox {
    public Image playImage;
    private Image pauseImage;
    private Image stopImage;
    private Image volumeImage;
    private ImageView playImg;
    private ImageView stopImg;
    private ImageView volumeGraphic;

    public Slider volumeSlider;
    public Slider timeLapse;
    public Button play;
    public Button stop;

    private MediaPlayer mediaPlayer;

    /**
     * constructor for the ControlsBar class
     */
    public ControlsBar() {
        int size = 70;
        playImage = new Image("file:img/play.png", size, size, false, true);
        pauseImage = new Image("file:img/pause.png", size, size, true, true);
        stopImage = new Image("file:img/stop.png", size, size, true, true);
        volumeImage = new Image("file:img/volume.png");

        playImg = new ImageView(playImage);
        stopImg = new ImageView(stopImage);

        this.setHeight(150);


        play = new Button("", playImg);
        stop = new Button("", stopImg);

        volumeSlider = new Slider();
        volumeSlider.setMaxWidth(100);
        volumeSlider.setValue(100);

        volumeGraphic = new ImageView(volumeImage);
        volumeGraphic.setFitWidth(20);
        volumeGraphic.setFitHeight(20);

        timeLapse = new Slider();
        timeLapse.setMaxWidth(500);

        HBox hBox = new HBox(play, stop, volumeGraphic, volumeSlider);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);
        hBox.setPadding(new Insets(0,15, 5, 15));

        this.getChildren().addAll(timeLapse, hBox);
        this.setSpacing(20);

        this.setAlignment(Pos.CENTER);
    }

    /**
     *
     * @return returns the value of the volume slider
     */
    public double getVolume() {
        return (volumeSlider.getValue() / 100);
    }

    /**
     *
     * @return returns true if the pauseImage is showing, or false if it isn't
     */
    public boolean playing() {
        if (playImg.getImage() == pauseImage) {
            return true;
        }
        else
            return false;
    }

    /**
     * changes the image showing on the play button
     */
    public void changeImg() {
        if (playImg.getImage() == playImage) {
            playImg.setImage(pauseImage);
        }
        else playImg.setImage(playImage);
    }

    /**
     *
     * @param duration is used to set the maximum value of the timelapse slider
     */
    public void setTimeLapse(Duration duration) {
        timeLapse.setMax(100 / duration.toSeconds());
    }

    /**
     *
     * @return returns the current value of the timelapse slider
     */
    public double currentTime() {
        return timeLapse.getValue();

    }
}
