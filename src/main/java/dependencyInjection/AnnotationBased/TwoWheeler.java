package dependencyInjection.AnnotationBased;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwoWheeler implements Vechicle{
    @Autowired
    private Wheel wheel;
    @Autowired
    private Engine engine;

    public TwoWheeler() {
        System.out.println("Two Wheeler Consturctor");
    }

    @Override
    public void move() {
        System.out.println("Two Wheeler Moved");
    }
}
