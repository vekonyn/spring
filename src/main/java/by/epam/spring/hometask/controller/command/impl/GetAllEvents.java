package by.epam.spring.hometask.controller.command.impl;

import by.epam.spring.hometask.controller.command.Command;
import by.epam.spring.hometask.controller.request.ArtificialRequestWrapper;
import by.epam.spring.hometask.controller.request.AttrList;
import by.epam.spring.hometask.controller.request.SessionAttrList;
import by.epam.spring.hometask.domain.Event;
import by.epam.spring.hometask.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Set;

public class GetAllEvents implements Command {
    @Autowired
    private EventService eventService;

    public EventService getEventService() {
        return eventService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public ArtificialRequestWrapper execute(ArtificialRequestWrapper requestWrapper) {

        Set<Event> allEvents = eventService.getNextEvents(LocalDateTime.of(2018, 12, 30, 0, 0));
        requestWrapper.setSessionAttr(SessionAttrList.EVENT_LIST, allEvents);
        requestWrapper.setAttr(AttrList.REQUEST_RESULT, AttrList.SUCCESS_OP);
        return requestWrapper;
    }
}
