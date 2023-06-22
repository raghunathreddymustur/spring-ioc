package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pojo.CashOnDelivery;
import pojo.Payment;
import pojo.loanProvider;

@Configuration
public class JavaConfiurationFileTwo {

    @Bean(name = "cashOnDelivery")
    public Payment getCashOnDelivery()
    {
        return new CashOnDelivery();
    }
    @Bean(name = "loanProvider")
    public Payment getSwipeLoan()
    {
        return new loanProvider();
    }
}
