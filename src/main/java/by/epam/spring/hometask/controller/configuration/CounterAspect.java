package by.epam.spring.hometask.controller.configuration;

import by.epam.spring.hometask.domain.Event;
import by.epam.spring.hometask.service.AspectCountersService;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class CounterAspect {

    @Autowired
    private AspectCountersService aspectCountersService;

    public void setAspectCountersService(AspectCountersService aspectCountersService) {
        this.aspectCountersService = aspectCountersService;
    }

    @AfterReturning(value = "execution(* *.getByName(..))", returning = "returnedEvent")
    private void countEventsAccessedByName(Object returnedEvent) {
        Event event = (Event) returnedEvent;
        aspectCountersService.trackEventAccessedByName(event.getName());
    }

    @AfterReturning(value = "execution(* *.bookTickets(..)) && args(eventName,..)")
    private void countTicketsForEventBought(String eventName) {
        aspectCountersService.trackEventTicketPriceWereQueried(eventName);
    }

    @AfterReturning(value = "execution(* *.getTicketsPrice(..)) && args(event,..)")
    private void countEventPriceQueried(Event event) {
        aspectCountersService.trackEventTicketsWereBought(event.getName());
    }

}
