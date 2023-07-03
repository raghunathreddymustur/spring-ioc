package annotationBasedConfiguration.beans.configurationOne.beans;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

@Component("oldBullTransport")
public class BullCart {
    BullCart()
    {
        System.out.println("Creating BullCart");
    }

}
