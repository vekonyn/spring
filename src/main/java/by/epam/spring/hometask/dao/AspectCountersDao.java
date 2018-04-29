package by.epam.spring.hometask.dao;

import javax.annotation.Nonnull;

public interface AspectCountersDao {

    @Nonnull
    void trackEventAccessedByName(String eventName);

    @Nonnull
    void trackEventTicketPriceWereQueried(String eventName);

    @Nonnull
    void trackEventTicketsWereBought(String eventName);

}
