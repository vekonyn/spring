package by.epam.spring.hometask.dao.impl;

import by.epam.spring.hometask.dao.AuditoriumDao;
import by.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.sql.DataSource;
import java.io.*;
import java.util.*;

public class MySqlAuditoriumDao implements AuditoriumDao {

    private static MySqlAuditoriumDao instance = null;

    private DataSource dataSource;

    public static final String AUDITORIUUM_PROPS_PATH = "src/main/resources/properties/auditorium.properties";

    public static synchronized AuditoriumDao getInstance() {
        if (instance == null) {
            instance = new MySqlAuditoriumDao();
        }
        return instance;
    }

    private static Set<Auditorium> auditoriumSet = Collections.emptySet();


    static {
        Properties props = new Properties();

        try {
            InputStream in = new FileInputStream(AUDITORIUUM_PROPS_PATH);
            props.load(in);

        } catch (FileNotFoundException e) {
            //"fail to read prop file"
            e.printStackTrace();
        } catch (IOException e) {
            //"failed to load props"
            e.printStackTrace();
        }
        auditoriumSet = new HashSet<Auditorium>(Arrays.asList(new Auditorium(props.getProperty("auditorium.1.name"), Long.valueOf(props.getProperty("auditorium.1.number.of.seats")), new HashSet<>(Arrays.asList(1L, 2L, 3L))),
                new Auditorium(props.getProperty("auditorium.2.name"), Long.valueOf(props.getProperty("auditorium.2.number.of.seats")), new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L))),
                new Auditorium(props.getProperty("auditorium.3.name"), Long.valueOf(props.getProperty("auditorium.3.number.of.seats")), new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L)))));
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void setauditoriumSet(Set<Auditorium> auditoriumSet) {
        MySqlAuditoriumDao.auditoriumSet = auditoriumSet;
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return auditoriumSet;
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriumSet.stream().filter(auditorium -> auditorium.getName().equals(name)).findFirst().get();
    }
}
