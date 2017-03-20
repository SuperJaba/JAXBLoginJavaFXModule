package pl.losK.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Miki on 18.03.2017.
 */
@XmlRootElement(name = "Bill")
@XmlType(propOrder = {"id", "listOfItems", "date", "price", "tax", "payment"})
public class Bill {
    private UUID id;
    private List<BillItem> listOfItems;
    //    private HashMap<String, Integer> mapOfItems;
    private Date date;
    private Double price;
    private Double tax;
    private Payment payment;

    public Bill() {
        id = UUID.randomUUID();
        date = new Date();
        listOfItems = new ArrayList<BillItem>();
        //        mapOfItems = new HashMap<String, Integer>();
        price = 0.0;
        tax = 0.0;
        payment = Payment.CASH;
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
        tax += item.getPrice() * addedAmount * item.getTax();
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

    public void updateItem(BillItem item, String valueInString) {
        Integer valueOf = 0;
        try {
            valueOf = Integer.valueOf(valueInString);
        } catch (Exception n) {
        }
        updateItem(item, valueOf);
    }

    public void updateItem(BillItem item, Integer amountBough) {
        if (amountBough < 0) {
            amountBough = 0;
        }
        Integer addedAmount = amountBough;
        if (listOfItems.contains(item)) {
            Integer oldAmount = listOfItems.get(listOfItems.indexOf(item)).getAmount();
            Integer newAmount = addedAmount;
            listOfItems.get(listOfItems.indexOf(item)).setAmount(newAmount);
        } else {
            item.setAmount(addedAmount);
            listOfItems.add(item);
        }
        setPrice();
        setTax();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    private void setPrice() {
        price = 0.0;
        for (BillItem e : this.getListOfItems()) {
            price += e.getAmount() * e.getPrice();
        }
    }

    public Double getTax() {
        return tax;
    }

    public void setTax() {
        tax = 0.0;
        for (BillItem e : this.getListOfItems()) {
            tax += e.getAmount() * e.getPrice() * e.getTax();
        }
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<BillItem> getListOfItems() {
        return listOfItems;
    }

    public void setListOfItems(List<BillItem> listOfItems) {
        this.listOfItems = listOfItems;
    }
}
