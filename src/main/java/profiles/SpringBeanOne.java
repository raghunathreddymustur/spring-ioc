package profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("QA")
public class SpringBeanOne {
    public SpringBeanOne() {
        System.out.println("Spring bean One");
    }
}
