import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Created by Michael Morgan on 4/13/2017.
 * @version 1.0
 */
public class TopMenuBar extends MenuBar {
    private Menu file = new Menu("File");
    private Menu help = new Menu("Help");

    public MenuItem open = new MenuItem("Open");
    public MenuItem close = new MenuItem("Close");
    public MenuItem exit = new MenuItem("Exit");

    private MenuItem doc = new MenuItem("Documentation");
    private MenuItem about = new MenuItem("About");

    private CustomMenuItem custom1 = new CustomMenuItem();
    private CustomMenuItem custom2 = new CustomMenuItem();




    public TopMenuBar() {
        exit.setGraphic(new ImageView(new Image("file:img/exit.png", 30, 30, true, true)));
        close.setGraphic(new ImageView(new Image("file:img/close.png", 30, 30, true, true)));
        open.setGraphic(new ImageView(new Image("file:img/open.png", 30, 30, true, true)));

        this.getMenus().addAll(file, help);
        this.setPadding(new Insets(5,15, 5, 15));
        this.setHeight(100);

        file.getItems().addAll(open, close, exit);

        help.getItems().addAll(doc, about);
        Slider slider = new Slider(0, 10, 5);
        slider.setOrientation(Orientation.VERTICAL);
        slider.setShowTickLabels(true);
        custom1.setContent(slider);

        Button button = new Button("Button in a Menu");
        custom2.setContent(button);
    }
}