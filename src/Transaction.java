import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Transaction {

    private Long transactionID;
    private Long buyerID;
    private Long sellerID;
    private Calendar calendar;
    private Date date;
    private Book book;
    private String timestamp;
    private double amount;
    private String soldToUsername;
    private String transactionType;

    public Transaction() {

        transactionID = Long.getLong("");
        buyerID = Long.getLong("");
        sellerID = Long.getLong("");
        calendar = Calendar.getInstance();
        date = calendar.getTime(); // i.e. Thu Nov 07 01:25:10 MST 2024

        book = new Book();
        timestamp = getTimestamp();
        amount = 0.0;
        soldToUsername = "";

        transactionType = "";

//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        int year = calendar.get(Calendar.YEAR);
    }

    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public Long getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(Long buyerID) {
        this.buyerID = buyerID;
    }

    public Long getSellerID() {
        return sellerID;
    }

    public void setSellerID(Long sellerID) {
        this.sellerID = sellerID;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSoldToUsername() {
        return soldToUsername;
    }

    public void setSoldToUsername(String soldToUsername) {
        this.soldToUsername = soldToUsername;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    // prints the date and time
    public String getTimestamp() {

        // i.e. 01:00:00 AM
        DateFormat dfTime = new SimpleDateFormat("hh:mm:ss aa");
        String dsTime = dfTime.format(date);

        // i.e. 01/01/2000 01:00 AM
        DateFormat dfDateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dsDateTime = dfDateTime.format(date);

        return dsDateTime;
    }

}
