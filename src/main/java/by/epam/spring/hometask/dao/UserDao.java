package by.epam.spring.hometask.dao;

import by.epam.spring.hometask.domain.Event;
import by.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface UserDao {

    public @Nullable
    User getById(@Nonnull Long id);

    public @Nullable
    User getUserByEmail(@Nonnull String email);

    public @Nullable
    User getByName(@Nonnull String name);

    public @Nullable
    Set<User> getAll();

    public @Nullable
    User saveUser(@Nonnull User user);

    public @Nullable
    User logIn(@Nonnull User user);

}
