import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StatisticsPane extends BorderPane {

    private final Button refreshButton;

    public StatisticsPane(User user, double width, double height) {

        NavigationAdminControl navBarVBox = new NavigationAdminControl(user,width,height);

        // set attributes for the Title Label
        Label titleLabel = new Label("Statistics");
        titleLabel.setFont(Font.font(42));

        // set attributes for refresh button image
        ImageView refreshImage = new ImageView(Main.refreshIcon);
        refreshImage.setFitHeight(30);
        refreshImage.setFitWidth(30);

        // set attributes for refresh button
        refreshButton = new Button();
        refreshButton.setGraphic(refreshImage);
        refreshButton.setBackground(Background.fill(Color.TRANSPARENT));
        refreshButton.setAlignment(Pos.CENTER);

        // set attributes for the header of the main VBox
        HBox headerHBox = new HBox(titleLabel,refreshButton);
        headerHBox.setAlignment(Pos.BASELINE_LEFT);
        headerHBox.setSpacing(40);
        HBox.setMargin(refreshButton,new Insets(0,0,20,0));

        // set attributes for the pie chart
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Users",3),
                        new PieChart.Data("Available Books",10),
                        new PieChart.Data("Sold Books",2),
                        new PieChart.Data("Revenue",5),
                        new PieChart.Data("Most Sold",5),
                        new PieChart.Data("Top Seller",3)
                );
        final PieChart chart = new PieChart(pieChartData);
        chart.setLegendSide(Side.LEFT);

        // set attributes for the main VBox which stores the header and the body
        VBox mainVBox = new VBox(headerHBox,chart);
        mainVBox.setPadding(new Insets(40,40,0,40));
        mainVBox.setPrefWidth(width - navBarVBox.getWidth() - 40);
        mainVBox.setStyle(
                "-fx-background-radius: 2em;" + "-fx-background-color: #ffffff;"
        );

        // set attributes for the container that holds the navigation bar and main page
        HBox navBarAndMainHBox = new HBox(navBarVBox,mainVBox);
        navBarAndMainHBox.setSpacing(20);

        this.setCenter(navBarAndMainHBox);
        BorderPane.setMargin(navBarAndMainHBox, new Insets(20,20,20,20));

        // these are by default what we use for the scene
        this.setPrefSize(width, height);
        this.setBackground(Background.fill(Color.web("#4A1E2C"))); // darker maroon

    }

    public Button getRefreshButton() {
        return refreshButton;
    }
}
