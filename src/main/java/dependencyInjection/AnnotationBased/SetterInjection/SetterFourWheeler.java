package dependencyInjection.AnnotationBased.SetterInjection;

import dependencyInjection.AnnotationBased.Engine;
import dependencyInjection.AnnotationBased.Seat;
import dependencyInjection.AnnotationBased.Wheel;
import dependencyInjection.AnnotationBased.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class SetterFourWheeler {
    private Engine engine;
    private Wheel wheel;
    private Seat seat;
    private Window window;

    public SetterFourWheeler() {
        this.engine = engine;
        this.seat = seat;
        this.window = window;

        System.out.println("Setter Four Wheeler Constructor is started");
        System.out.println("Wheel "+ wheel);
        System.out.println("Engine "+ engine);
        System.out.println("Seat "+ seat);
        System.out.println("Window "+ window);
        System.out.println("Setter Four Wheeler Constructor is ended");
    }

    @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Autowired
    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    @Autowired(required = false)
    public void setSeatAndWindow(Seat seat,Window window) {
        this.seat = seat;
        this.window = window;
    }

    @PostConstruct
    public void init()
    {
        System.out.println("Setter Four Wheeler PostConstruct INIT is started");
        System.out.println("Wheel "+ wheel);
        System.out.println("Engine "+ engine);
        System.out.println("Seat "+ seat);
        System.out.println("Window "+ window);
        System.out.println("Setter Four Wheeler PostConstruct INIT  is ended");
    }

}
