import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.sql.Date;

public class Seller_PostBookControl extends Pane {

    public double width;
    public double height;
    private final Seller_PostBookPane pane;
    private User user;

    public Seller_PostBookControl(User user, double width, double height) {

        this.width = width;
        this.height = height;
        this.user = user;

        pane = new Seller_PostBookPane(user,width,height);
        Button listmybookButton = pane.getListmybookButton();
        listmybookButton.setOnAction(new ButtonHandler());

        this.getChildren().addAll(pane);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {

            if(pane.emptyFields()) {

                pane.displayEmptyFields();

            } else if (!(pane.isPriceValid())) {

                pane.displayInvalidPrice();

            } else {

                pane.computeGeneratedPrice();

                if(pane.confirmPost()) {

                    insertBook();
                    pane.displayBookPosted();

                }

            }
        }

        // once the user confirms to post their book
        // use this method to insert the book into the database
        public void insertBook() {

            Date year = new Date(Integer.parseInt(pane.getPublishedYear()));
            Book book =  new Book();
            book.setTitle(pane.getBookTitle());
            book.setAuthor(pane.getAuthor());
            book.setCategory(pane.getCategory());
            book.setCondition(pane.getCondition());
            book.setPublishedYear(year);
            book.setPrice(pane.getPrice());
            DataBase.listBook(pane.getBookTitle(),year, pane.getAuthor(), pane.getCategory(),pane.getCondition(),pane.getPrice());

            // call database to insert book
            // DataBase.insertBook(user,...);

        }
    }
}
