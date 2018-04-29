package by.epam.spring.hometask.service.impl;

import by.epam.spring.hometask.dao.UserDao;
import by.epam.spring.hometask.domain.User;
import by.epam.spring.hometask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private static UserServiceImpl instance = null;

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public User save(@Nonnull User user) {
        return userDao.saveUser(user);
    }

    @Override
    public void remove(@Nonnull User object) {

    }

    @Override
    public User getById(@Nonnull Long id) {
        return null;
    }

    @Nonnull
    @Override
    public Set<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User logIn(@Nonnull User user) {
        return userDao.logIn(user);
    }
}
