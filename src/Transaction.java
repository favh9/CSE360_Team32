import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Transaction {

    private final Calendar calendar;
    private final Date date;
    private final int month;
    private final int day;
    private final int year;
    private final double amount;

    public Transaction(double amount) {

        this.amount = amount;
        calendar = Calendar.getInstance();
        date = calendar.getTime(); // i.e. Thu Nov 07 01:25:10 MST 2024
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        year = calendar.get(Calendar.YEAR);
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public Date getDate() {
        return date;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public double getAmount() {
        return amount;
    }

    // prints the date and time
    public String toString() {

        DateFormat dfTime = new SimpleDateFormat("hh:mm::ss aa");
        String dsTime = dfTime.format(new Date());
        System.out.println(dsTime);

        DateFormat dfDateTime = new SimpleDateFormat("dd/MM/yyyy hh::ss aa");
        String dsDateTime = dfDateTime.format(new Date());
        System.out.println(dsDateTime);

        return dsDateTime;
    }

}
