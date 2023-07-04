package dependencyInjection.AnnotationBased;

import org.springframework.stereotype.Component;

@Component
public class Wheel {
    public Wheel() {
        System.out.println("Wheel Constructor");
    }
}
