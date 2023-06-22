package pojo;

public class CashOnDelivery implements Payment{
    public CashOnDelivery() {
        System.out.println("Cash on Delivery");
    }

    @Override
    public void doPayment() {
        System.out.println("Paid vai cash");
    }
}
