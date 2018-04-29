package by.epam.spring.hometask.controller;

import by.epam.spring.hometask.controller.command.Command;
import by.epam.spring.hometask.controller.command.CommandProvider;
import by.epam.spring.hometask.controller.request.ArtificialRequestWrapper;
import by.epam.spring.hometask.controller.request.ParamList;
import by.epam.spring.hometask.util.NullCheckUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Controller {

    private Controller() {
    }
    private static Controller instance;

    @Autowired
    private CommandProvider provider;

//    public CommandProvider getProvider() {
//        return provider;
//    }

    public void setProvider(CommandProvider provider) {
        this.provider = provider;
    }

    private final static Logger log = Logger.getLogger(Controller.class);
    private static final String GENERAL_FAILURE = "Failed to execute a task";

    public static synchronized Controller getInstance() {
        if (null == instance) {
            instance = new Controller();
        }
        return instance;
    }

    public ArtificialRequestWrapper executeTask(ArtificialRequestWrapper requestWrapper) {
        try {
            Command command = null;
            if (null != requestWrapper) {
                String commandName = (String) requestWrapper.getParameter(ParamList.COMMAND);

                if (NullCheckUtil.notNullCheck(commandName)) {

                    // get an appropriate executor for request
                    command = provider.getCommand(commandName);

                    // get a response from a specific executor
                    requestWrapper = command.execute(requestWrapper);
                }
            }
        } catch (Exception e) {
            log.error(GENERAL_FAILURE, e);
        }
        return requestWrapper;
    }
}
