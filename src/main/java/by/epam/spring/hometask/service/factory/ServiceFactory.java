package by.epam.spring.hometask.service.factory;

import by.epam.spring.hometask.service.*;
import by.epam.spring.hometask.service.impl.*;

public class ServiceFactory {

    private static ServiceFactory instance;
    private final AuditoriumService auditoriumService = new AuditoriumServiceImpl();
    private final BookingService bookingService = new BookingServiceImpl();
    private final DiscountService discountService = new DiscountServiceImpl();
    private final EventService eventService = new EventServiceImpl();
    private final UserService userService = new UserServiceImpl();

    public static synchronized ServiceFactory getInstance() {
        if (null == instance) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public AuditoriumService getAuditoriumService() {
        return auditoriumService;
    }

    public BookingService getBookingService() {
        return bookingService;
    }

    public DiscountService getDiscountService() {
        return discountService;
    }

    public EventService getEventService() {
        return eventService;
    }

    public UserService getUserService() {
        return userService;
    }
}
