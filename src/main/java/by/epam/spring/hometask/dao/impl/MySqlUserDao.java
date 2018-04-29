package by.epam.spring.hometask.dao.impl;

import by.epam.spring.hometask.dao.UserDao;
import by.epam.spring.hometask.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MySqlUserDao implements UserDao {

    private static MySqlUserDao instance = null;

    private static Set<User> userSet = Collections.emptySet();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_USER_BY_ID_QUERY = "SELECT u_id, u_firstname, u_lastname, u_email FROM users where u_id = ?";
    private static final String LOG_IN_USER_QUERY  = "SELECT u_id, u_firstname, u_lastname, u_email FROM users where u_firstname = ? and u_password = ?";


    public static synchronized UserDao getInstance(){
        if (instance == null) {
            instance = new MySqlUserDao();
        }
        return instance;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Nullable
    @Override
    public Set<User> getAll() {
        return userSet;
    }

    @Nullable
    @Override
    public User saveUser(@Nonnull User user) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("users").usingGeneratedKeyColumns(
                "u_id");
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("u_firstname", user.getFirstName());
        parameters.put("u_password", user.getPassword());
        parameters.put("u_lastname", user.getLastName());
        parameters.put("u_email", user.getEmail());
        long loggedUserId = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(
                parameters)).longValue();
        return getById(loggedUserId);
    }

    public @Nullable
    User getById(@Nonnull Long id) {
       User user = jdbcTemplate.queryForObject(GET_USER_BY_ID_QUERY,
               new Object[]{id}, new RowMapper<User>() {
                   @Override
                   public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                       Long id = resultSet.getLong("u_id");
                       String firstName = resultSet.getString("u_firstname");
                       String lastName = resultSet.getString("u_lastname");
                       String email = resultSet.getString("u_email");
                       return new User(id, firstName,lastName,email);
                   }
               });
       return user;
    }

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return null;
    }

    @Nullable
    @Override
    public User getByName(@Nonnull String name) {
        return null;
    }

    @Nullable
    @Override
    public User logIn(@Nonnull User userToLogin) {
        User loggedInUser = jdbcTemplate.queryForObject(LOG_IN_USER_QUERY,
                new Object[]{userToLogin.getFirstName(), userToLogin.getPassword()}, new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        Long id = resultSet.getLong("u_id");
                        String firstName = resultSet.getString("u_firstname");
                        String lastName = resultSet.getString("u_lastname");
                        String email = resultSet.getString("u_email");
                        User u = new User(id, firstName, lastName, email);
                        return u;
                    }
                });
        return loggedInUser;
    }

}
