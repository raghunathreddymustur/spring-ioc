package multipleConfigurationFiles;

import config.JavaConfigThree;
import config.JavaConfiguration;
import config.JavaConfiurationFileTwo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pojo.MobilePhone;
import pojo.Payment;
import pojo.UPI;

import java.util.Map;

public class JavaBasedMultiConfiguration {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(JavaConfiurationFileTwo.class, JavaConfiguration.class, JavaConfigThree.class
        );

        //accessing via id or name
        Payment paymentUpi= (Payment) applicationContext.getBean("upi");
        paymentUpi.doPayment();
        Payment paymentCash= (Payment) applicationContext.getBean("cashOnDelivery");
        paymentCash.doPayment();

        //accessing bean without typecasting
        UPI paymentUpiTwo=  applicationContext.getBean("upi", UPI.class);
        System.out.println("accessing bean without typecasting");
        paymentUpiTwo.doPayment();

        //accessing vai type
        MobilePhone mobilePhone=applicationContext.getBean(MobilePhone.class);
        System.out.println("accessing vai type");
        mobilePhone.pringTypeOfCategory();

        //retrieving all beans of a specific type
        Map<String,Payment> paymentMap=applicationContext.getBeansOfType(Payment.class);
        System.out.println("retrieving all beans of a specific type "+paymentMap);

        //retrieves the names of all beans of a specific type
        String[] beanNames=applicationContext.getBeanNamesForType(Payment.class);
        System.out.println("retrieves the names of all beans of a specific type");
        for(String name:beanNames)
        {
            System.out.println(" beans of a specific type" +name);
        }

        //retrieving via aliases
        applicationContext.getBean("phoneCategory",MobilePhone.class).pringTypeOfCategory();


    }
}
