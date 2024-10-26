
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;

// use this to create buttons for the navigation bar
// this class will create a button with a given image
// and adjust a default image size to it
public class NavButton extends Button {

    public NavButton(Image image) {

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(120);
        imageView.setFitWidth(120);
        this.setGraphic(imageView);
        this.setBackground(Background.EMPTY);

    }
    
}
