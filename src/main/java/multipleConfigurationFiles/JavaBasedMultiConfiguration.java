package multipleConfigurationFiles;

import config.JavaConfiguration;
import config.JavaConfiurationFileTwo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pojo.Payment;

public class JavaBasedMultiConfiguration {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(JavaConfiurationFileTwo.class, JavaConfiguration.class);
        Payment paymentUpi= (Payment) applicationContext.getBean("upi");
        paymentUpi.doPayment();
        Payment paymentCash= (Payment) applicationContext.getBean("cashOnDelivery");
        paymentCash.doPayment();
    }
}
