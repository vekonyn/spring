package by.epam.spring.hometask.controller.configuration;

import by.epam.spring.hometask.controller.Controller;
import by.epam.spring.hometask.controller.command.Command;
import by.epam.spring.hometask.controller.command.CommandName;
import by.epam.spring.hometask.controller.command.CommandProvider;
import by.epam.spring.hometask.controller.command.impl.*;
import by.epam.spring.hometask.controller.request.ArtificialRequestWrapper;
import by.epam.spring.hometask.dao.*;
import by.epam.spring.hometask.dao.impl.*;
import by.epam.spring.hometask.domain.User;
import by.epam.spring.hometask.service.AspectCountersService;
import by.epam.spring.hometask.service.BookingService;
import by.epam.spring.hometask.service.EventService;
import by.epam.spring.hometask.service.UserService;
import by.epam.spring.hometask.service.impl.AspectCountersServiceImpl;
import by.epam.spring.hometask.service.impl.BookingServiceImpl;
import by.epam.spring.hometask.service.impl.EventServiceImpl;
import by.epam.spring.hometask.service.impl.UserServiceImpl;
import by.epam.spring.hometask.view.ViewPerformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.LinkedHashMap;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "by.epam.spring.hometask")
@PropertySource(value = { "classpath:db.properties" })
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean (name = "viewPerformer")
    public ViewPerformer getViewPerformer() {
        return new ViewPerformer();
    }

    @Bean
    public Controller getController() {
        return Controller.getInstance();
    }

    @Bean
    public ArtificialRequestWrapper getArtificialRequestWrapper() {
        return new ArtificialRequestWrapper();
    }

    @Bean
    public CommandProvider getCommandProvider() {
        return new CommandProvider();
    }

    @Bean
    public LinkedHashMap<CommandName, Command> getRepository(){
        LinkedHashMap<CommandName, Command> repository = new LinkedHashMap<>();
        repository.put(CommandName.SAVE_USER, getSaveUserCommand());
        repository.put(CommandName.LOGIN, getLoginUserCommand());
        repository.put(CommandName.GET_ALL_EVENTS, getGetAllEventsCommand());
        repository.put(CommandName.VIEW_TICKETS_PRICE_FOR_EVENT, getGetTicketPriceCommand());
        repository.put(CommandName.BOOK_TICKET, getBookTicketsCommand());
        repository.put(CommandName.WRONG_REQUEST, getWrongRequestCommand());
        return repository;
    }

    @Bean
    public Command getSaveUserCommand(){
        return new SaveUser();
    }

    @Bean
    public Command getLoginUserCommand(){
        return new LoginUser();
    }

    @Bean
    public Command getGetAllEventsCommand(){
        return new GetAllEvents();
    }

    @Bean
    public Command getGetTicketPriceCommand(){
        return new GetTicketPrice();
    }

    @Bean
    public Command getBookTicketsCommand(){
        return new BookTickets();
    }

    @Bean
    public Command getWrongRequestCommand(){
        return new WrongRequest();
    }

    @Bean
    public EventService getEventService() {
        return EventServiceImpl.getInstance();
    }

    @Bean
    public UserService getUserService(){
        return UserServiceImpl.getInstance();
    }

    @Bean
    public BookingService getBookingService(){
        return BookingServiceImpl.getInstance();
    }

    @Bean
    public AuditoriumDao getAuditoriumDao(){
        return MySqlAuditoriumDao.getInstance();
    }

    @Bean
    public BookingDao getBookingDao(){
        return MySqlBookingDao.getInstance();
    }

    @Bean
    public EventDao getEventDao(){
        return MySqlEventDao.getInstance();
    }

    @Bean
    public UserDao getUserDao(){
        return new MySqlUserDao();
    }

    @Bean
    public CounterAspect getCounterAspect(){
        return new CounterAspect();
    }

    @Bean
    public DiscountAspect getDiscountAspect(){
        return new DiscountAspect();
    }

    @Bean
    public LuckyWinnerAspect getLuckyWinnerAspect(){
        return new LuckyWinnerAspect();
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dateSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dateSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

    @Bean
    public DataSource getDateSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.mysql.driver"));
        dataSource.setUrl(env.getRequiredProperty("spring.datasource.url"));
        dataSource.setUsername(env.getRequiredProperty("spring.datasource.username"));
        dataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));
        return dataSource;
    }

    @Bean
    public AspectCountersService aspectCountersService(){
        return new AspectCountersServiceImpl();
    }

    @Bean
    public AspectCountersDao aspectCountersDao(){
        return new MySQLAspectCountersDao();
    }

}
