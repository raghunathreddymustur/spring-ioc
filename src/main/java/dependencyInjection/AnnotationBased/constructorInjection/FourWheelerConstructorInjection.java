package dependencyInjection.AnnotationBased.constructorInjection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FourWheelerConstructorInjection {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ConstructorConfig.class);

        context.getBean("consturctorFourWheeler");
    }
}
