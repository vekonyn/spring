package by.epam.spring.hometask.controller.rest;

import by.epam.spring.hometask.domain.Event;
import by.epam.spring.hometask.domain.User;
import by.epam.spring.hometask.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Set;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/ticketPrice")
    public String getTicketsPrice() {
        Event event = null;
        LocalDateTime time = null;
        User user = null;
        Set<Long> seats = null;
        double ticketsPrice = bookingService.getTicketsPrice(event, time, user, seats);
        return "";
    }
}
