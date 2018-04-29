package by.epam.spring.hometask.dao.impl;

import by.epam.spring.hometask.dao.EventDao;
import by.epam.spring.hometask.domain.Event;
import by.epam.spring.hometask.domain.EventRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class MySqlEventDao implements EventDao {


    private static MySqlEventDao instance = null;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String GET_EVENT_BY_NAME_QUERY = "SELECT e.e_id, e.e_name, e.e_base_price, e_event_rating from events e where e.e_name=?";
    private final static String GET_NEXT_EVENTS_QUERY = "SELECT e.e_id, e.e_name, e.e_base_price, e.e_event_rating, GROUP_CONCAT(DISTINCT a.a_airdate) as airdates from events e join events_has_airdates eha on e.e_id = eha.events_e_id join airdates a on eha.airdates_a_id = a.a_id where a.a_airdate < ? group by e.e_name";

    public static synchronized MySqlEventDao getInstance() {
        if (instance == null) {
            instance = new MySqlEventDao();
        }
        return instance;
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        Event event = jdbcTemplate.queryForObject(GET_EVENT_BY_NAME_QUERY,
                new Object[]{name}, new RowMapper<Event>() {
                    @Override
                    public Event mapRow(ResultSet resultSet, int rowNum) throws SQLException {

                        Long id = resultSet.getLong("e_id");
                        String name = resultSet.getString("e_name");
                        Double basePrice = resultSet.getDouble("e_base_price");
                        EventRating rating = EventRating.valueOf(resultSet.getString("e_event_rating"));

                        return new Event(id,name,null,basePrice,rating,null);
                    }
                });
        return event;
    }

    @Nonnull
    @Override
    public Set<Event> getForDateRange(@Nonnull LocalDate from, @Nonnull LocalDate to) {
        return null;
    }

    @Nonnull
    @Override
    public Set<Event> getNextEvents(@Nonnull LocalDateTime to) {
        Timestamp timestamp = Timestamp.valueOf(to);
        List<Event> events = jdbcTemplate.query(GET_NEXT_EVENTS_QUERY,
                new Object[]{timestamp}, new RowMapper<Event>() {
                    @Override
                    public Event mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        Long id = resultSet.getLong("e_id");
                        String name = resultSet.getString("e_name");
                        Double basePrice = resultSet.getDouble("e_base_price");
                        EventRating rating = EventRating.valueOf(resultSet.getString("e_event_rating"));
                        String [] airdatesStrings = resultSet.getString("airdates").split(",");
                        NavigableSet<LocalDateTime> airdates = new TreeSet<LocalDateTime>();
                        for(String airdate: airdatesStrings){
                            Timestamp timestamp = Timestamp.valueOf(airdate);
                            LocalDateTime airdateTime = timestamp.toLocalDateTime();
                            airdates.add(airdateTime);
                        }
                        return new Event(id,name,airdates,basePrice,rating,null);
                    }
                });
        return new HashSet<>(events);
    }

    @Nonnull
    @Override
    public Set<Event> getAll() {
        return null;
    }

}
