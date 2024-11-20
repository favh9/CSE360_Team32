import java.sql.Date;

public class Book {

    private String title;
    private String author;
    private String category;
    private String condition;
    private int publishedYear;
    //private int quantity;
    private int id;
    private double price;

    public Book() {
        id = 0;
        title = "";
        author = "";
        category = "";
        condition = "";
        publishedYear = 0;
        price = 0.0;
    }

    public Book(String title, String author, String category, String condition, double price,
                int publishedYear , int id) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.condition = condition;
        this.publishedYear = publishedYear;
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

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

//    public int getQuantity() {
//        return quantity;
//    }

    public int getPublishedYear(){return this.publishedYear;}

    public double getPrice() {return this.price;}

}
