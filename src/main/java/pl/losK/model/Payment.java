package pl.losK.model;

/**
 * Created by Miki on 18.03.2017.
 */
public enum Payment {

    CASH("Gotówka"), CARD("Karta");

    private String payment;

    Payment(String payment) {
        this.payment = payment;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
