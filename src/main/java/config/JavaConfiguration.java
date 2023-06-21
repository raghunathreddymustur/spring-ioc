package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pojo.Card;
import pojo.Payment;
import pojo.UPI;

@Configuration
public class JavaConfiguration {

    @Bean(name = "card")
    public Payment getCardPayment()
    {
        return new Card();
    }

    @Bean(name = "upi")
    public Payment getUPIPayment()
    {
        return new UPI();
    }

}
