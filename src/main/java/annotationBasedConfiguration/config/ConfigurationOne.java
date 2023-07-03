package annotationBasedConfiguration.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "annotationBasedConfiguration.beans.configurationOne.beans")
public class ConfigurationOne {
}
