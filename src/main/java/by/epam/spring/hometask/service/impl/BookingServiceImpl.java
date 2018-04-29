package by.epam.spring.hometask.service.impl;

import by.epam.spring.hometask.dao.BookingDao;
import by.epam.spring.hometask.domain.Ticket;
import by.epam.spring.hometask.service.AuditoriumService;
import by.epam.spring.hometask.service.BookingService;
import by.epam.spring.hometask.domain.Event;
import by.epam.spring.hometask.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;

public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDao bookingDao;

    private static BookingServiceImpl instance = null;

    public static synchronized BookingService getInstance() {
        if (instance == null) {
            instance = new BookingServiceImpl();
        }
        return instance;
    }

    public void setBookingDao(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        return bookingDao.getTicketsPrice(event, dateTime, user, seats);
    }

    @Override
    public boolean bookTickets(@Nonnull String eventName, int amount, User user) {
        return bookingDao.bookTickets(eventName, amount, user);
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return bookingDao.getPurchasedTicketsForEvent(event, dateTime);
    }
}
