package by.epam.spring.hometask.controller.command;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * <code>CommandProvider</code> class return the command instance that matches String input
 */
public class CommandProvider {

	@Autowired
	private LinkedHashMap<CommandName, Command> repository;

	private final static Logger log = Logger.getLogger(CommandProvider.class);

	public LinkedHashMap<CommandName, Command> getRepository() {
		return repository;
	}

	public void setRepository(LinkedHashMap<CommandName, Command> repository) {
		this.repository = repository;
	}

	public Command getCommand(String commandString) {

		CommandName commandName = null;
		Command command = null;
		try {
			commandName = CommandName.valueOf(commandString.toUpperCase());
			command = repository.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			log.error(e);
			command = repository.get(CommandName.WRONG_REQUEST);
		}
		return command;
	}
}
