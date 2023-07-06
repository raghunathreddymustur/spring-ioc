package dependencyInjection.AnnotationBased;

import org.springframework.stereotype.Component;

@Component
public class Seat {
    public Seat() {
        System.out.println("Get Seat");
    }
    private void getSeat()
    {
        System.out.println("Take seat");
    }
}
