package by.epam.spring.hometask.view;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "by.epam.spring.hometask")
public class ViewRunner {

    public static void main(String[] args) {
        SpringApplication.run(ViewRunner.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            ViewPerformer performer = ctx.getBean("viewPerformer", ViewPerformer.class);
            performer.openConsoleSession();
        };
    }
}
