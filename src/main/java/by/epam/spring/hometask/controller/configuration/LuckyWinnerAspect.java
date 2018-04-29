package by.epam.spring.hometask.controller.configuration;

import by.epam.spring.hometask.domain.User;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LuckyWinnerAspect {

    private final static String LUCKY_MESSAGE = "You've been lucky and get a ticket on %s for free!";

    @After("execution(* by.epam.spring.hometask.dao.impl.MySqlBookingDao.bookTickets(..)) && args(eventName, user))")
    private void countEventsAccessedByName(String eventName, User user) {

        if (generateLuckyEvent()) {
            user.setLuckyEvent(String.format(LUCKY_MESSAGE, eventName));
        }
    }

    private boolean generateLuckyEvent() {
        return Math.random() < 0.5;
    }
}
