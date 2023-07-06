package dependencyInjection.AnnotationBased;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class FourWheeler implements Vechicle{

    @Autowired
    private Wheel wheel;

    @Autowired
    private Optional<Engine> engine;

    @Autowired
    @Nullable
    private Seat seat;

    @Autowired(required = false)
    private Window window;



    public FourWheeler() {
        System.out.println("Four Wheeler Constructor is started");
        System.out.println("Wheel "+ wheel);
        System.out.println("Engine "+ engine);
        System.out.println("Seat "+ seat);
        System.out.println("Window "+ window);
        System.out.println("Four Wheeler Constructor is ended");
    }

    @Override
    public void move() {
        System.out.println("Four Wheeler");
    }

    @PostConstruct
    public void init()
    {
        System.out.println("Four Wheeler PostConstruct INIT is started");
        System.out.println("Wheel "+ wheel);
        System.out.println("Engine "+ engine);
        System.out.println("Seat "+ seat);
        System.out.println("Window "+ window);
        System.out.println("Four Wheeler PostConstruct INIT  is ended");
    }
}
