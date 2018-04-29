package by.epam.spring.hometask.controller.command.impl;

import by.epam.spring.hometask.controller.command.Command;
import by.epam.spring.hometask.controller.request.ArtificialRequestWrapper;
import by.epam.spring.hometask.controller.request.AttrList;
import by.epam.spring.hometask.controller.request.ParamList;
import by.epam.spring.hometask.controller.request.SessionAttrList;
import by.epam.spring.hometask.domain.Event;
import by.epam.spring.hometask.domain.User;
import by.epam.spring.hometask.service.BookingService;
import by.epam.spring.hometask.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Set;

public class GetTicketPrice implements Command {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private EventService eventService;

    private static final String TICKETS_FOR_THIS_EVENT_NOT_FOUND = "You haven't got tickets for this event";

    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public ArtificialRequestWrapper execute(ArtificialRequestWrapper requestWrapper) {
        String eventName = (String) requestWrapper.getParameter(ParamList.EVENT_NAME);
        Event event = eventService.getByName(eventName);
        User user = (User) requestWrapper.getSessionAttr(SessionAttrList.USER);
        LocalDateTime localDateTime = (LocalDateTime) requestWrapper.getParameter(ParamList.TICKETS_DATE_TIME);
        Set<Long> seats = (Set<Long>) requestWrapper.getParameter(ParamList.SEATS);

        if (user != null && event != null && seats != null) {
            double ticketsPrice = bookingService.getTicketsPrice(event, localDateTime, user, seats);

            if(ticketsPrice > 0) {
                requestWrapper.setAttr(AttrList.REQUEST_RESULT, AttrList.SUCCESS_OP);
                requestWrapper.setAttr(AttrList.TICKETS_PRICE, "Combined price for your tickets for event " +eventName+" is: "+ticketsPrice+" $");
            } else {
                requestWrapper.setAttr(AttrList.REQUEST_RESULT, AttrList.FAILED_OP);
                requestWrapper.setAttr(AttrList.ERROR_MESSAGE, TICKETS_FOR_THIS_EVENT_NOT_FOUND);
            }
        }
        return requestWrapper;
    }
}
