package dependencyInjection.AnnotationBased;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutowiredOnCollection {
    @Autowired
    List<Vechicle> vechicleLis;

    public void printVechicle()
    {
        vechicleLis.forEach(System.out::println);
    }

}
