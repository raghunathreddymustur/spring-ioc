package annotationBasedConfiguration.beans.configurationOne.beans;

import org.springframework.stereotype.Component;

@Component
public class TwoWheeler implements Vechicle{
    @Override
    public void printCategoryOfVechicle() {
        System.out.println("Two Wheeler Category");
    }
}
