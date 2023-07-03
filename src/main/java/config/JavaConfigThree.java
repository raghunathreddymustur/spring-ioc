package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pojo.Category;
import pojo.MobilePhone;

@Configuration
public class JavaConfigThree {
    @Bean(name = {"mobileCategory","phoneCategory"})
    public Category getMobileCategory()
    {
        return new MobilePhone();
    }
}
