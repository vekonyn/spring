package by.epam.spring.hometask.controller.command.impl;

import by.epam.spring.hometask.controller.command.Command;
import by.epam.spring.hometask.controller.request.ArtificialRequestWrapper;
import by.epam.spring.hometask.controller.request.AttrList;
import by.epam.spring.hometask.controller.request.ParamList;
import by.epam.spring.hometask.controller.request.SessionAttrList;
import by.epam.spring.hometask.domain.User;
import by.epam.spring.hometask.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;

public class BookTickets implements Command {

    private final static String SUCCESS_TICKETS_BOUGHT = "%s tickets for %s event bought!";
    private final static String ERROR_BOUGHT = "Failed to buy tickets for %s event!";

    @Autowired
    private BookingService bookingService;

    public BookingService getBookingService() {
        return bookingService;
    }

    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public ArtificialRequestWrapper execute(ArtificialRequestWrapper requestWrapper) {

        String eventToBuyTickets = (String) requestWrapper.getParameter(ParamList.EVENT_NAME);
        int amountOfTickets = Integer.valueOf((String)requestWrapper.getParameter(ParamList.AMOUNT_OF_TICKETS));
        User user = (User) requestWrapper.getSessionAttr(SessionAttrList.USER);
        if (eventToBuyTickets != null && user != null) {
            boolean result = bookingService.bookTickets(eventToBuyTickets, amountOfTickets, user);
            if (result) {
                requestWrapper.setAttr(AttrList.REQUEST_RESULT, AttrList.SUCCESS_OP);
                requestWrapper.setAttr(AttrList.SUCCESS_MESSAGE, String.format(SUCCESS_TICKETS_BOUGHT, amountOfTickets, eventToBuyTickets));
            } else {
                requestWrapper.setAttr(AttrList.REQUEST_RESULT, AttrList.FAILED_OP);
                requestWrapper.setAttr(AttrList.ERROR_MESSAGE, String.format(ERROR_BOUGHT, eventToBuyTickets));
            }
        }
        return requestWrapper;
    }
}
