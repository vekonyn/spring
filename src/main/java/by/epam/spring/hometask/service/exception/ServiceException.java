package by.epam.spring.hometask.service.exception;

import by.epam.spring.hometask.exception.AbstractApplicationException;

/**
 * Class to create specific <code>ServiceException</code> exception
 * objects
 * 
 */
public class ServiceException extends AbstractApplicationException {


	/**
	 * @param message
	 *            exception message
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * @param message
	 *            exception message
	 * @param innerEx
	 *            Throwable inner exception instance, which is the cause of this
	 *            exception
	 */
	public ServiceException(String message, Throwable innerEx) {
		super(message, innerEx);
		initCause(innerEx);
	}

	/**
	 * @param e
	 *            exception
	 */
	public ServiceException(Exception e) {
		super(e);
	}
	
}
