package by.epam.spring.hometask.dao;

import by.epam.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

public interface EventDao {

    public @Nullable
    Event getByName(@Nonnull String name);

    /*
     * Finding all events that air on specified date range
     *
     * @param from Start date
     *
     * @param to End date inclusive
     *
     * @return Set of events
     */
    public @Nonnull
    Set<Event> getForDateRange(@Nonnull LocalDate from,
                               @Nonnull LocalDate to);

    /*
     * Return events from 'now' till the the specified date time
     *
     * @param to End date time inclusive
     * s
     * @return Set of events
     */
    public @Nonnull
    Set<Event> getNextEvents(@Nonnull LocalDateTime to);

    @Nonnull
    public Set<Event> getAll();


}
