import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Transaction {

    private final Long transactionID;
    private final Long buyerID;
    private final Long sellerID;
    private final Date date;
    private final double amount;

    public Transaction(double amount) {

        transactionID = Long.getLong("");
        buyerID = Long.getLong("");
        sellerID = Long.getLong("");
        Calendar calendar = Calendar.getInstance();
        date = calendar.getTime(); // i.e. Thu Nov 07 01:25:10 MST 2024
        this.amount = amount;

//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        int year = calendar.get(Calendar.YEAR);
    }

    public Long getTransactionID() {
        return transactionID;
    }

    public Long getBuyerID() {
        return buyerID;
    }

    public Long getSellerID() {
        return sellerID;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    // prints the date and time
    public String getDateString() {

        // i.e. 01:00:00 AM
        DateFormat dfTime = new SimpleDateFormat("hh:mm:ss aa");
        String dsTime = dfTime.format(date);
        System.out.println(dsTime);

        // i.e. 01/01/2000 01:00 AM
        DateFormat dfDateTime = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
        String dsDateTime = dfDateTime.format(date);
        System.out.println(dsDateTime);

        return dsDateTime;
    }

}
