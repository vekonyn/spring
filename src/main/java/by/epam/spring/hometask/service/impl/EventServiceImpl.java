package by.epam.spring.hometask.service.impl;

import by.epam.spring.hometask.dao.EventDao;
import by.epam.spring.hometask.domain.Event;
import by.epam.spring.hometask.service.BookingService;
import by.epam.spring.hometask.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    private static EventServiceImpl instance = null;

    public static synchronized EventService getInstance() {
        if (instance == null) {
            instance = new EventServiceImpl();
        }
        return instance;
    }

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return eventDao.getByName(name);
    }

    @Nonnull
    @Override
    public Set<Event> getForDateRange(@Nonnull LocalDate from, @Nonnull LocalDate to) {
        return eventDao.getForDateRange(from, to);
    }

    @Nonnull
    @Override
    public Set<Event> getNextEvents(@Nonnull LocalDateTime to) {
        return eventDao.getNextEvents(to);
    }

    @Override
    public Event save(@Nonnull Event object) {
        return null;
    }

    @Override
    public void remove(@Nonnull Event object) {

    }

    @Override
    public Event getById(@Nonnull Long id) {
        return null;
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return null;
    }
}
