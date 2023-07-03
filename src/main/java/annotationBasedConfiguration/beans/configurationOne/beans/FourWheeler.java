package annotationBasedConfiguration.beans.configurationOne.beans;

import org.springframework.stereotype.Component;

@Component
public class FourWheeler implements Vechicle{
    @Override
    public void printCategoryOfVechicle() {
        System.out.println("Four Wheeler Category");
    }
}
