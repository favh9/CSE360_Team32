public class Book {

    private String title;
    private String author;
    private String category;
    private String condition;
    private int quantity;
    private double price;

    public Book() {
        title = "";
        author = "";
        category = "";
        condition = "";
        quantity = 0;
        price = 0.0;
    }

    public Book(String title, String author, String category, String condition, int quantity, double price) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.condition = condition;
        this.quantity = quantity;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getCondition() {
        return condition;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

}
