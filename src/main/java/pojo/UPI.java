package pojo;

public class UPI implements Payment{
    public UPI() {
        System.out.println("Creating UPI");
    }

    @Override
    public void doPayment() {
        System.out.println("paid via UPI");
    }
}
