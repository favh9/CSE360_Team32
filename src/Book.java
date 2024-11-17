public class Book {

    private String title;
    private String author;
    private String category;
    private String condition;
    private double price;

    public Book() {
        title = "";
        author = "";
        category = "";
        condition = "";
        price = 0.0;
    }

    public Book(String title, String author, String category, String condition, double price) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.condition = condition;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
