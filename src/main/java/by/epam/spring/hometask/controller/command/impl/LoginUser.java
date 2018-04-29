package by.epam.spring.hometask.controller.command.impl;

import by.epam.spring.hometask.controller.command.Command;
import by.epam.spring.hometask.controller.request.ArtificialRequestWrapper;
import by.epam.spring.hometask.controller.request.AttrList;
import by.epam.spring.hometask.controller.request.ParamList;
import by.epam.spring.hometask.controller.request.SessionAttrList;
import by.epam.spring.hometask.domain.User;
import by.epam.spring.hometask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginUser implements Command{

    private final static String SUCCESS_USER_ADDED = "Welcome, %s";

    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ArtificialRequestWrapper execute(ArtificialRequestWrapper requestWrapper) {

        User userToLogin = (User) requestWrapper.getParameter(ParamList.USER);

        User loggedUser = userService.logIn(userToLogin);
        requestWrapper.setSessionAttr(SessionAttrList.USER, loggedUser);
        requestWrapper.setAttr(AttrList.REQUEST_RESULT, AttrList.SUCCESS_OP);
        requestWrapper.setAttr(AttrList.SUCCESS_MESSAGE, String.format(SUCCESS_USER_ADDED,loggedUser.getFirstName()));
        return requestWrapper;
    }
}
