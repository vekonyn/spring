package by.epam.spring.hometask.service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import by.epam.spring.hometask.domain.User;

import java.util.Set;

/**
 * @author Yuriy_Tkach
 */
public interface UserService extends AbstractDomainObjectService<User> {

    /**
     * Finding user by email
     *
     * @param email Email of the user
     * @return found user or <code>null</code>
     */
    public @Nullable
    User getUserByEmail(@Nonnull String email);

    @Nonnull
    @Override
    public Set<User> getAll();

    @Nonnull
    public User logIn(@Nonnull User user);
}