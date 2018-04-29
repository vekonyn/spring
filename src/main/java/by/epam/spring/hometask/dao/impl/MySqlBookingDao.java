package by.epam.spring.hometask.dao.impl;

import by.epam.spring.hometask.dao.BookingDao;
import by.epam.spring.hometask.domain.Event;
import by.epam.spring.hometask.domain.Ticket;
import by.epam.spring.hometask.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class MySqlBookingDao implements BookingDao {

    private static BookingDao instance = null;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String BOOK_TICKETS_QUERY = "update tickets t set t.users_u_id = ? where t.events_e_id = (select e.e_id from events e where e.e_name = ?) and t.users_u_id is null limit ?";

    public static synchronized BookingDao getInstance() {
        if (instance == null) {
            instance = new MySqlBookingDao();
        }
        return instance;
    }

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        double ticketsPrice = 0d;
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        StringBuilder buildQuery = new StringBuilder();
        seats.stream().forEach(seat -> buildQuery.append(seat.toString() + ","));
        String getTicketsPriceQuery = "select sum(e.e_base_price) as combined_price from tickets t join events e on t.events_e_id=e.e_id join events_has_airdates eha on e.e_id=eha.events_e_id join airdates a on eha.airdates_a_id=a.a_id join users u on t.users_u_id=u.u_id  where e.e_name=? and t.t_seat in (" + buildQuery.deleteCharAt(buildQuery.length() - 1).toString() + ") and u.u_firstname=? and a.a_airdate=?";

        ticketsPrice = jdbcTemplate.queryForObject(getTicketsPriceQuery,
                new Object[]{event.getName(), user.getFirstName(), timestamp}, new RowMapper<Double>() {
                    @Override
                    public Double mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        return resultSet.getDouble("combined_price");
                    }
                });
        return ticketsPrice;
    }

    @Override
    public boolean bookTickets(@Nonnull String eventName, int amount, User user) {
        int result = jdbcTemplate.update(BOOK_TICKETS_QUERY, String.valueOf(user.getId()), eventName, amount);
        return result > 0;
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return null;
    }
}
