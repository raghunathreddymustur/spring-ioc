package profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanTwo {
    public SpringBeanTwo() {
        System.out.println("Spring Bean Two");
    }
}
