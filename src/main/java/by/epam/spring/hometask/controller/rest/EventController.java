package by.epam.spring.hometask.controller.rest;

import by.epam.spring.hometask.domain.Event;
import by.epam.spring.hometask.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public String getEvents() {
        Collection<Event> events = eventService.getAll();
        return "";
    }

}
