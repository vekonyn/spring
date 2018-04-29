package by.epam.spring.hometask.service.impl;

import by.epam.spring.hometask.dao.AspectCountersDao;
import by.epam.spring.hometask.service.AspectCountersService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;

public class AspectCountersServiceImpl implements AspectCountersService {

    @Autowired
    private AspectCountersDao aspectCountersDao;

    private static AspectCountersServiceImpl instance = null;

    public static synchronized AspectCountersServiceImpl getInstance() {
        if (instance == null) {
            instance = new AspectCountersServiceImpl();
        }
        return instance;
    }

    @Nonnull
    @Override
    public void trackEventAccessedByName(String eventName) {
        aspectCountersDao.trackEventAccessedByName(eventName);
    }

    @Nonnull
    @Override
    public void trackEventTicketPriceWereQueried(String eventName) {
        aspectCountersDao.trackEventTicketPriceWereQueried(eventName);
    }

    @Nonnull
    @Override
    public void trackEventTicketsWereBought(String eventName) {
        aspectCountersDao.trackEventTicketsWereBought(eventName);
    }
}
