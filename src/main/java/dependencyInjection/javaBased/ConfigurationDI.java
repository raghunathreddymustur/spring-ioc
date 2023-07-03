package dependencyInjection.javaBased;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pojo.Card;
import pojo.MultiplePaymentSupport;
import pojo.Payment;
import pojo.UPI;

@Configuration
public class ConfigurationDI {
    @Bean
    public Payment getUpiPayment()
    {
        return new UPI();
    }

    @Bean
    public Payment getCardPayment()
    {
        return new Card();
    }

    @Bean
    public Payment multiplePayment()
    {
        return new MultiplePaymentSupport(getUpiPayment(),getCardPayment());
    }

    //using Autowired
    @Bean
    public UPI getDirectUpiPayment()
    {
        return new UPI();
    }

    @Bean
    public Card getDirectCardPayment()
    {
        return new Card();
    }

    @Bean
    @Autowired
    public  Payment multiPaymentTwo(UPI upi, Card card)
    {
        return new MultiplePaymentSupport(upi,card);
    }



}
