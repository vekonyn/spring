package by.epam.spring.hometask.service.impl;

import by.epam.spring.hometask.dao.AuditoriumDao;
import by.epam.spring.hometask.dao.impl.MySqlAuditoriumDao;
import by.epam.spring.hometask.domain.Auditorium;
import by.epam.spring.hometask.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired
    private AuditoriumDao auditoriumDao;

    private static AuditoriumServiceImpl instance = null;

    public static synchronized AuditoriumService getInstance() {
        if (instance == null) {
            instance = new AuditoriumServiceImpl();
        }
        return instance;
    }

    public void setAuditoriumDao(AuditoriumDao auditoriumDao) {
        this.auditoriumDao = auditoriumDao;
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return auditoriumDao.getAll();
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriumDao.getByName(name);
    }

}
