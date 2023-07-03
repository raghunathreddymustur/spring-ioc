package annotationBasedConfiguration.beans.configurationTwo.beans;

import org.springframework.stereotype.Component;

@Component
public class Card implements Payment{
    @Override
    public void doPayment() {
        System.out.println("Card Payment");
    }
}
