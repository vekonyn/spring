package by.epam.spring.hometask.view;

import by.epam.spring.hometask.controller.Controller;
import by.epam.spring.hometask.controller.command.CommandName;
import by.epam.spring.hometask.controller.request.ArtificialRequestWrapper;
import by.epam.spring.hometask.controller.request.AttrList;
import by.epam.spring.hometask.controller.request.ParamList;
import by.epam.spring.hometask.controller.request.SessionAttrList;
import by.epam.spring.hometask.domain.Event;
import by.epam.spring.hometask.domain.User;
import by.epam.spring.hometask.view.util.PrinterHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.*;

public class ViewPerformer {

    public ViewPerformer() {
    }

    private final static Logger log = Logger.getLogger(ViewPerformer.class);

    private final static String FAILED_OP = "Requested operation failed...";
    private final static String ERROR_MSG = "Operation error. Contact the system administrator.";
    private final static String SAVE_USER = "1";
    private final static String LOGIN = "2";
    private final static String GET_ALL_EVENTS = "3";
    private final static String VIEW_TICKET_PRICE_FOR_EVENT = "5";

    private final static String BOOK_TICKET = "4";
    private final static String SAVE_EVENT = "6";
    private final static String GET_PURCHASED_TICKETS_FOREVENT = "7";

    private final static String EXIT = "0";

    @Autowired
    private Controller controller;
    @Autowired
    private ArtificialRequestWrapper requestWrapper;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setRequestWrapper(ArtificialRequestWrapper requestWrapper) {
        this.requestWrapper = requestWrapper;
    }

    public void openConsoleSession() {

        Scanner terminal = new Scanner(System.in);
        List<String> welcomeMsgBox = new ArrayList<>(
                Arrays.asList(
                        "Welcome to the Theater!",
                        "What can I do for you today?"
                ));
        PrinterHelper.printMessageInBox(welcomeMsgBox);
        String menuIdx = "";

        while (!menuIdx.equals(EXIT)) {
            List<String> menuMsgBox = new ArrayList<>(
                    Arrays.asList(
                            "",
                            "1. Register",
                            "2. Login",
                            "3. View events",
                            "4. Book tickets",
                            "5. View your tickets price",
                            //"6. Enter event",
                            // "7. View purchased tickets",
                            "0. Exit",
                            ""
                    ));
            PrinterHelper.printMessageInBox(menuMsgBox);

            if (terminal.hasNext()) {
                menuIdx = terminal.next();
            }

            switch (menuIdx) {
                case SAVE_USER:

                    processSaveUserChoice(terminal);

                    break;

                case LOGIN:

                    loginUserChoice(terminal);

                    break;

                case GET_ALL_EVENTS:

                    processViewEvents();

                    break;

                case VIEW_TICKET_PRICE_FOR_EVENT:

                    processViewTicketPriceForEvent(terminal);

                    break;

                case BOOK_TICKET:

                    processBuyTickets(terminal);

                    break;

                case EXIT:

                    break;

                default:
                    log.info("Wrong input.");
                    break;
            }
        }

    }

    private void processViewTicketPriceForEvent(Scanner terminal) {
        String eventName = null;
        LocalDateTime ticketsDate = null;
        Set<Long> ticketsSeats = null;
        AttrList requestResult = null;
        log.info("Enter the event on which to show the price:");
        eventName = terminal.next();

        ticketsDate = inputTicketsDateToRetvieve(terminal);
        ticketsSeats = inputTicketsSeats(terminal);
        requestWrapper.setParameter(ParamList.COMMAND, CommandName.VIEW_TICKETS_PRICE_FOR_EVENT.get());
        requestWrapper.setParameter(ParamList.TICKETS_DATE_TIME, ticketsDate);
        requestWrapper.setParameter(ParamList.TICKETS_SEATS, ticketsSeats);
        requestWrapper.setParameter(ParamList.EVENT_NAME, eventName);
        requestWrapper = controller.executeTask(requestWrapper);
        requestResult = (AttrList) requestWrapper.getAttr(AttrList.REQUEST_RESULT);
        if (null != requestResult && requestResult.equals(AttrList.SUCCESS_OP)) {
            log.info(requestWrapper.getAttr(AttrList.TICKETS_PRICE));
        } else {
            log.info(requestWrapper.getAttr(AttrList.ERROR_MESSAGE));
        }
        requestWrapper.clearRequestStorage();
    }


    private void processBuyTickets(Scanner terminal) {
        String eventName = null;
        String amountOfTickets = null;
        User buyTicketsFor = null;
        AttrList requestResult = null;

        log.info("Enter event to buy tickets for:");
        eventName = terminal.next();

        log.info("How much tickets to buy ?");
        amountOfTickets = terminal.next();

        buyTicketsFor = (User) requestWrapper.getSessionAttr(SessionAttrList.USER);
        if (buyTicketsFor != null) {
            requestWrapper.setParameter(ParamList.COMMAND, CommandName.BOOK_TICKET.get());
            requestWrapper.setParameter(ParamList.EVENT_NAME, eventName);
            requestWrapper.setParameter(ParamList.AMOUNT_OF_TICKETS, amountOfTickets);
            requestWrapper = controller.executeTask(requestWrapper);
            requestResult = (AttrList) requestWrapper.getAttr(AttrList.REQUEST_RESULT);
            if (null != requestResult && requestResult.equals(AttrList.SUCCESS_OP)) {
                log.info(requestWrapper.getAttr(AttrList.SUCCESS_MESSAGE));
            } else {
                log.info(FAILED_OP);
            }
        } else {
            log.info("To buy tickets you should register in the system");
        }

        requestWrapper.clearRequestStorage();
    }

    private void processViewEvents() {
        Set<Event> allEvents = null;
        AttrList requestResult = null;
        requestWrapper.setParameter(ParamList.COMMAND, CommandName.GET_ALL_EVENTS.get());
        requestWrapper = controller.executeTask(requestWrapper);
        requestResult = (AttrList) requestWrapper.getAttr(AttrList.REQUEST_RESULT);
        if (null != requestResult && requestResult.equals(AttrList.SUCCESS_OP)) {
            log.info("All next events: ");
            allEvents = (Set<Event>) requestWrapper.getSessionAttr(SessionAttrList.EVENT_LIST);
            allEvents.stream().forEach(System.out::println);
        } else {
            log.info(requestWrapper.getAttr(AttrList.ERROR_MESSAGE));
        }
        requestWrapper.clearRequestStorage();

    }

    private void processSaveUserChoice(Scanner terminal) {
        String userName = null;
        String userLastName = null;
        String userEmail = null;
        String userPassword = null;
        User toAdd = null;

        log.info("Enter your name:");
        userName = terminal.next();
        log.info("Enter your last name:");
        userLastName = terminal.next();
        log.info("Enter your password:");
        userPassword = terminal.next();
        log.info("Enter your email:");
        userEmail = terminal.next();
        toAdd = new User(userName, userLastName, userPassword, userEmail);
        AttrList requestResult = null;

        requestWrapper.setParameter(ParamList.COMMAND, CommandName.SAVE_USER.get());
        requestWrapper.setParameter(ParamList.USER, toAdd);
        requestWrapper = controller.executeTask(requestWrapper);
        requestResult = (AttrList) requestWrapper.getAttr(AttrList.REQUEST_RESULT);
        if (null != requestResult && requestResult.equals(AttrList.SUCCESS_OP)) {
            log.info(requestWrapper.getAttr(AttrList.SUCCESS_MESSAGE));
        } else {
            log.info(FAILED_OP);
        }
        requestWrapper.clearRequestStorage();
    }

    private void loginUserChoice(Scanner terminal) {
        String userName = null;
        String password = null;
        User userToLogin = null;

        log.info("Enter your first name:");
        userName = terminal.next();

        log.info("Enter your password:");

        password = terminal.next();

        userToLogin = new User(userName, password);
        AttrList requestResult = null;

        requestWrapper.setParameter(ParamList.COMMAND, CommandName.LOGIN.get());
        requestWrapper.setParameter(ParamList.USER, userToLogin);
        requestWrapper = controller.executeTask(requestWrapper);
        requestResult = (AttrList) requestWrapper.getAttr(AttrList.REQUEST_RESULT);
        if (null != requestResult && requestResult.equals(AttrList.SUCCESS_OP)) {
            log.info(requestWrapper.getAttr(AttrList.SUCCESS_MESSAGE));
        } else {
            log.info(FAILED_OP);
        }
        requestWrapper.clearRequestStorage();
    }

    private Set<Long> inputTicketsSeats(Scanner scanner) {
        Set<Long> ticketsSeats = new HashSet<>();
        String userInput = "";
        log.info("Enter ticket's seats. Enter '0' for exit:");

        while (!(userInput = scanner.next()).equals("0")) {
            try {
                ticketsSeats.add(Long.parseLong(userInput));
            } catch (NumberFormatException e) {
                log.info("Wrong input format. Make sure to enter only numbers.");
                log.info("Enter ticket's seat. Enter '0' for exit:");
            }
        }
        return ticketsSeats;
    }

    private LocalDateTime inputTicketsDateToRetvieve(Scanner scanner) {
        LocalDateTime ticketDate = null;
        String year, month, day, hour, minutes = "";
        log.info("Enter tickets to view. Enter '0' for exit:");

        while (ticketDate == null) {
            try {
                log.info("Enter event year:");
                year = scanner.next();
                log.info("Enter event month:");
                month = scanner.next();
                log.info("Enter event day:");
                day = scanner.next();
                log.info("Enter event hour:");
                hour = scanner.next();
                log.info("Enter event minutes:");
                minutes = scanner.next();
                ticketDate = LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(hour), Integer.parseInt(minutes));
            } catch (NumberFormatException e) {
                log.info("Wrong date format.");
                log.info("Enter tickets to view. Enter '0' for exit:");
            }
        }
        return ticketDate;
    }
}
