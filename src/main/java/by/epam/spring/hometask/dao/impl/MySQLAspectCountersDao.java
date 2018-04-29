package by.epam.spring.hometask.dao.impl;

import by.epam.spring.hometask.dao.AspectCountersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class MySQLAspectCountersDao implements AspectCountersDao {

    private static MySQLAspectCountersDao instance = null;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String BOOK_TICKETS_QUERY = "update events e set e.e_accessed_by_name = e.e_accessed_by_name+1 where e.e_name=?";
    private static final String TICKET_PRICE_WERE_QUERIED_QUERY = "update events e set e.e_prices_queried = e.e_prices_queried+1 where e.e_name=?";
    private static final String EVENT_TICKETS_BOUGHT_QUERY = "update events e set e.e_ticket_was_bought = e.e_ticket_was_bought+1 where e.e_name=?";

    public static synchronized MySQLAspectCountersDao getInstance() {
        if (instance == null) {
            instance = new MySQLAspectCountersDao();
        }
        return instance;
    }

    @Override
    public void trackEventAccessedByName(String eventName) {
        jdbcTemplate.update(BOOK_TICKETS_QUERY, eventName);
    }

    @Override
    public void trackEventTicketPriceWereQueried(String eventName){
        jdbcTemplate.update(TICKET_PRICE_WERE_QUERIED_QUERY, eventName);
    }

    @Override
    public void trackEventTicketsWereBought(String eventName){
        jdbcTemplate.update(EVENT_TICKETS_BOUGHT_QUERY, eventName);
    }
}
