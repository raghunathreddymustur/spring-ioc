package dependencyInjection.AnnotationBased;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FourWheeler implements Vechicle{

    @Autowired
    private Wheel wheel;
    @Autowired
    private Engine engine;

    public FourWheeler() {
        System.out.println("Foru Wheeler Consturctor");
    }

    @Override
    public void move() {
        System.out.println("Four Wheeler");
    }
}
