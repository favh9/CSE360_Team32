import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Transaction {

    public int transactionID;
    public int buyerID;
    public int sellerID;
    public Date date;
    public String timestamp;
    public Book book;
    public double amount;
    public char buyer_reviewed;
    public char seller_reviewed;
    public Transaction() {

        transactionID = 0;
        buyerID = 0;
        sellerID = 0;
        timestamp = "";
        book = new Book();
        buyer_reviewed = 'N';
        seller_reviewed = 'N';
    }

    public char getBuyer_reviewed() {
        return buyer_reviewed;
    }

    public void setBuyer_reviewed(char buyer_reviewed) {
        this.buyer_reviewed = buyer_reviewed;
    }

    public char getSeller_reviewed() {
        return seller_reviewed;
    }

    public void setSeller_reviewed(char seller_reviewed) {
        this.seller_reviewed = seller_reviewed;
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

//  public String getTimestamp() {
//      return timestamp.toString();
//  }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String string) {
        timestamp = string;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


    public double getAmount() {return amount;}

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // prints the date and time
//    public String getTimestamp() {
//
//        // i.e. 01:00:00 AM
//        DateFormat dfTime = new SimpleDateFormat("hh:mm:ss aa");
//        String dsTime = dfTime.format(date);
//
//        // i.e. 01/01/2000 01:00 AM
//        DateFormat dfDateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String dsDateTime = dfDateTime.format(date);
//
//        return dsDateTime;
//    }

}
