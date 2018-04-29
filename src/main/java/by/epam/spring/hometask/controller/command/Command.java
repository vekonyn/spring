package by.epam.spring.hometask.controller.command;

import by.epam.spring.hometask.controller.request.ArtificialRequestWrapper;

/**
 * <code>Command</code> interface define behavior for command implementation instances
 */
public interface Command {

	ArtificialRequestWrapper execute(ArtificialRequestWrapper requestWrapper);
}
