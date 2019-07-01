package group.flowbird.mediationservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"group.flowbird"})
@Configuration
public class Application {

    public static void main(String[] args) {
        log.info("######################### Start The Application ########################");
        SpringApplication.run(Application.class, args);
        log.info("#########################The Application has started succesfully######################");
    }
}
