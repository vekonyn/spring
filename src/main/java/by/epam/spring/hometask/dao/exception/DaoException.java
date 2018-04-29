package by.epam.spring.hometask.dao.exception;

import by.epam.spring.hometask.exception.AbstractApplicationException;

/**
 * error class inherits <code>AbstractParkingException<code>
 * Class to create <code>DaoException<code>
 * exception objects on dao application level
 * 
 */
public class DaoException extends AbstractApplicationException {

	/**
	 * @param message exception message
	 */
	public DaoException(String message) {
		super(message);
	}

	/**
	 * @param message exception message
	 * @param innerEx Throwable inner 
	 * exception instance, which is 
	 * the cause of this exception
	 */
	public DaoException(String message, Exception innerEx) {
		super(message);
		/* Initializes the cause of this exception to the specified value */
		initCause(innerEx);
	}

	/**
	 * @param e
	 *            exception
	 */
	public DaoException(Exception e) {
		super(e);
	}
	
}
