import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

    public int transactionID;
    public int buyerID;
    public int sellerID;
    public Date date;
    public Book book;
    public double amount;
    public Transaction() {

        transactionID = 0;
        buyerID = 0;
        sellerID = 0;
        date  = new Date(2024 - 1900, 11 - 1, 19, 14, 30, 0);  // November 19, 2024, 14:30:00

        book = new Book();
        amount = 0.0;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
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



    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
