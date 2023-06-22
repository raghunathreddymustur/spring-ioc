package pojo;

public class loanProvider implements Payment{
    public loanProvider() {
        System.out.println("Swipe loan provider");
    }

    @Override
    public void doPayment() {
        System.out.println("Paid via loan");
    }
}
