package dependencyInjection.AnnotationBased.constructorInjection;

import dependencyInjection.AnnotationBased.Engine;
import dependencyInjection.AnnotationBased.Seat;
import dependencyInjection.AnnotationBased.Wheel;
import dependencyInjection.AnnotationBased.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class ConsturctorFourWheeler {

    private Engine engine;
    private Optional<Wheel> wheel;
    private Seat seat;
    private Window window;

    public ConsturctorFourWheeler(Engine engine, Seat seat, Window window) {
        this.engine = engine;
        this.seat = seat;
        this.window = window;

        System.out.println("Constructor Four Wheeler Constructor is started");
        System.out.println("Wheel "+ wheel);
        System.out.println("Engine "+ engine);
        System.out.println("Seat "+ seat);
        System.out.println("Window "+ window);
        System.out.println("Constructor Four Wheeler Constructor is ended");
    }

    @Autowired
    public ConsturctorFourWheeler(Engine engine, Optional<Wheel> wheel, @Nullable Seat seat, @Autowired(required = false)  Window window) {
        this.engine = engine;
        this.wheel = wheel;
        this.seat = seat;
        this.window = window;

        System.out.println("Constructor Four Wheeler Constructor is started");
        System.out.println("Wheel "+ wheel);
        System.out.println("Engine "+ engine);
        System.out.println("Seat "+ seat);
        System.out.println("Window "+ window);
        System.out.println("Constructor Four Wheeler Constructor is ended");
    }

    @PostConstruct
    public void init()
    {
        System.out.println("Constructor Four Wheeler PostConstruct INIT is started");
        System.out.println("Wheel "+ wheel);
        System.out.println("Engine "+ engine);
        System.out.println("Seat "+ seat);
        System.out.println("Window "+ window);
        System.out.println("Constructor Four Wheeler PostConstruct INIT  is ended");
    }
}





