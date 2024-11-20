public class PaymentInfo {
    private String nameOnCard;
    private String cardNumber;
    private String expirationDate;
    private String cvc;

    // Constructor
    public PaymentInfo(String nameOnCard, String cardNumber, String expirationDate, String cvc) {
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
    }

    // Getters
    public String getNameOnCard() {
        return nameOnCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public static void main(String[] args) {
        // Create a mock PaymentInfo object
        PaymentInfo paymentInfo = new PaymentInfo("Mike Hawk", "1234123412341234", "12/2025", "123");

        // Print the object's details to verify
        System.out.println(paymentInfo);
    }

    @Override
    public String toString() {
        return "PaymentInfo{" +
                "nameOnCard='" + nameOnCard + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvc='" + cvc + '\'' +
                '}';
    }
}
