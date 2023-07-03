package annotationBasedConfiguration.beans.configurationTwo.beans;

import org.springframework.stereotype.Component;

@Component
public class UPI implements Payment{
    @Override
    public void doPayment() {
        System.out.println("upi payment");
    }
}
