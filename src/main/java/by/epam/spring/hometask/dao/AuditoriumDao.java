package by.epam.spring.hometask.dao;

import by.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public interface AuditoriumDao {

    public @Nonnull
    Set<Auditorium> getAll();

    public @Nullable
    Auditorium getByName(@Nonnull String name);
}
