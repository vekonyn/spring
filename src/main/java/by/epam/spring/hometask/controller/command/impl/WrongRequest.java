package by.epam.spring.hometask.controller.command.impl;

import by.epam.spring.hometask.controller.command.Command;
import by.epam.spring.hometask.controller.request.ArtificialRequestWrapper;
import by.epam.spring.hometask.controller.request.AttrList;

public class WrongRequest implements Command {

    @Override
    public ArtificialRequestWrapper execute(ArtificialRequestWrapper requestWrapper) {

        requestWrapper.setAttr(AttrList.REQUEST_RESULT, AttrList.FAILED_OP);
        requestWrapper.setAttr(AttrList.ERROR_MESSAGE, "Wrong request.");

        return requestWrapper;
    }

}
