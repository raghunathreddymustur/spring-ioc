package pojo;

public class MultiplePaymentSupport implements Payment{
    Payment upi;
    Payment card;

    @Override
    public void doPayment() {
        System.out.println("Multiple Payments");
    }

    public MultiplePaymentSupport(Payment upi,Payment card) {
        this.card=card;
        this.upi=upi;
        System.out.println("Multiple Payments");
        confirm(upi,card);
    }

    private void confirm(Payment upi, Payment card) {
        upi.doPayment();
        card.doPayment();
    }
}
