package dependencyInjection.AnnotationBased;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(Config.class);
        context.registerShutdownHook();
        context.getBean("twoWheeler", TwoWheeler.class).move();
    }
}
