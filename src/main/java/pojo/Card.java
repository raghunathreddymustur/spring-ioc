package pojo;

public class Card implements Payment{
    public Card() {
        System.out.println("Genearatin Card");
    }

    @Override
    public void doPayment() {
        System.out.println("Paid via card");
    }
}
