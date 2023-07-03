package annotationBasedConfiguration.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "annotationBasedConfiguration.beans.configurationTwo.beans")
public class ConfigurationTwo {
}
