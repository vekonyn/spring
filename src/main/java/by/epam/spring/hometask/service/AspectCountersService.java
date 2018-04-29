package by.epam.spring.hometask.service;

import javax.annotation.Nonnull;

public interface AspectCountersService {

    @Nonnull
    void trackEventAccessedByName(String eventName);

    @Nonnull
    void trackEventTicketPriceWereQueried(String eventName);

    @Nonnull
    void trackEventTicketsWereBought(String eventName);
}
