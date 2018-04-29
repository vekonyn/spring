package by.epam.spring.hometask.dao.impl;

import by.epam.spring.hometask.dao.BookingDao;
import by.epam.spring.hometask.dao.DiscountDao;
import by.epam.spring.hometask.domain.Event;
import by.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.sql.DataSource;
import java.time.LocalDateTime;

public class MySqlDiscountDao implements DiscountDao {

    private static MySqlDiscountDao instance = null;

    private DataSource dataSource;

    public static synchronized DiscountDao getInstance() {
        if (instance == null) {
            instance = new MySqlDiscountDao();
        }
        return instance;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        return 0;
    }
}
