import java.sql.Date;

public class Book {

    private String title;
    private String author;
    private String category;
    private String condition;
    private int publishedYear;
    //private int quantity;
    private int id;
    private double profit;
    private double price;
    private String publisher;
    private int seller_id;
    private double seller_rating;

    public Book() {
        id = 0;
        title = "";
        author = "";
        category = "";
        condition = "";
        publishedYear = 0;
        price = 0.0;
        publisher = "";
        seller_id = 0;
        seller_rating = 0.0;
    }

    public Book(String title, String author, String category, String condition, double profit, double price,
                int publishedYear , int id, int seller_id, String publisher, double seller_rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.condition = condition;
        this.publishedYear = publishedYear;
        this.profit = profit;
        this.price = price;
        this.publisher = publisher;
        this.seller_id = seller_id;
        this.seller_rating = seller_rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSeller_rating(double seller_rating) {this.seller_rating = seller_rating;}

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setPublisher(String publisher) {this.publisher = publisher;}

    public void setSeller_id(int seller_id) {this.seller_id = seller_id;}

//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {return this.title;}

    public int getID() {return this.id;}

    public String getAuthor() {return this.author;}

    public String getCategory() {return this.category;}

    public String getCondition() {return this.condition;}

    public String getPublisher() {return this.publisher;}

    public int getSellerID() {return this.seller_id;}

    public String getSellerRating() {return Double.toString(this.seller_rating);}

//    public int getQuantity() {
//        return quantity;
//    }

    public int getPublishedYear(){return this.publishedYear;}

    public double getPrice() {return this.price;}

}
