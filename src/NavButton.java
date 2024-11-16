
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;

// use this to create buttons for the navigation bar
// this class will create a button with a given image
// and adjust a default image size to it
public class NavButton extends Button {

    private ImageView imageView;

    public NavButton(Image image) {

        imageView = new ImageView(image);
        imageView.setFitWidth(65);
        imageView.setFitHeight(65);
        this.setGraphic(imageView);
        this.setBackground(Background.EMPTY);

    }

    public NavButton(Image image,double width, double height) {

        imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        this.setGraphic(imageView);
        this.setBackground(Background.EMPTY);

    }
    
}
