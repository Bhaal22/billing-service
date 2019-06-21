package group.flowbird.mediationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan(basePackages = {"group.flowbird"})
@Configuration
public class Application {

    public static void main(String[] args) {
        final SpringApplication springApplication = new SpringApplication(Application.class);
        ConfigurableApplicationContext context = springApplication.run(args);
        System.out.println("***********Hello from the Springboot Service************");
    }

    public static String getName(){
        return "mediationservice";
    }

    public static String getVersion(){
        return "0.0.1";
    }
}
