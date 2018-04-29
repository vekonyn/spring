package by.epam.spring.hometask.view;

import by.epam.spring.hometask.controller.configuration.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ViewRunner {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        ViewPerformer performer = ctx.getBean("viewPerformer", ViewPerformer.class);
        performer.openConsoleSession();
        ctx.close();
    }
}
