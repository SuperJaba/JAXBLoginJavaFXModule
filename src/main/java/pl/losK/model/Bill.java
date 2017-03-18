package pl.losK.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.*;

@XmlRootElement(name = "Bill")
@XmlType(propOrder = {"id", "listOfItems", "date", "price", "tax", "payment"})
public class Bill {

    private UUID id;
    private List<BillItem> listOfItems;
    private Date date;
    private Double price;
    private Double tax;
    private Payment payment;

    public Bill() {
        id = UUID.randomUUID();
        date = new Date();
        listOfItems = new ArrayList<>();

        price = 0.0;
        tax = 0.0;

        payment = Payment.CARD;
    }

    public void addItem(BillItem item, Integer amountBough) {

        Integer addedAmount = item.getAmount() * amountBough;

        if (listOfItems.contains(item)) {
            Integer oldAmount = listOfItems.get(listOfItems.indexOf(item)).getAmount();
            Integer newAmount = oldAmount + addedAmount;
            listOfItems.get(listOfItems.indexOf(item)).setAmount(newAmount);
        } else {
            item.setAmount(addedAmount);
            listOfItems.add(item);
        }

        price += item.getPrice() * addedAmount;
        tax += item.getTax() * addedAmount * item.getTax();
    }

    public void removeItem(BillItem item, Integer amountToRemove) {
        Integer removedAmount = item.getAmount() * amountToRemove;

        if (listOfItems.contains(item)) {
            Integer oldAmount = listOfItems.get(listOfItems.indexOf(item)).getAmount();
            Integer newAmount = oldAmount - removedAmount;
            listOfItems.get(listOfItems.indexOf(item)).setAmount(newAmount);

            if (listOfItems.get(listOfItems.indexOf(item)).getAmount() <= 0) {
                price -= item.getPrice() * oldAmount;
                tax -= item.getPrice() * oldAmount * item.getTax();

                listOfItems.remove(item);
            } else {
                price -= item.getPrice() * removedAmount;
                tax -= item.getPrice() * removedAmount * item.getTax();
            }
        }
    }

    public void updateItem(BillItem item, Integer amountBough) {

        Integer addedAmount = item.getAmount() * amountBough;

        if (listOfItems.contains(item)) {

            Integer oldAmount = listOfItems.get(listOfItems.indexOf(item)).getAmount();

            price -= item.getPrice() * oldAmount;
            tax -= item.getPrice() * oldAmount * item.getTax();

            Integer newAmount = addedAmount;
            listOfItems.get(listOfItems.indexOf(item)).setAmount(newAmount);


        } else {
            item.setAmount(addedAmount);
            listOfItems.add(item);
        }

        price += item.getPrice() * addedAmount;
        tax += item.getPrice() * addedAmount * item.getTax();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<BillItem> getListOfItems() {
        return listOfItems;
    }

    public void setListOfItems(List<BillItem> listOfItems) {
        this.listOfItems = listOfItems;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", listOfItems=" + listOfItems +
                ", date=" + date +
                ", price=" + price +
                ", tax=" + tax +
                ", payment=" + payment +
                '}';
    }
}
