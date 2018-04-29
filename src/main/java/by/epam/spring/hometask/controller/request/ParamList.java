package by.epam.spring.hometask.controller.request;

/**
 * <code>ParamList</code> enum keeps allowed request parameter names
 */
public enum ParamList {

	COMMAND("command"),
	USER("user"),
	EVENT("event"),
	DATE_TIME("date_time"),
	SEATS("tickets_seats"),
	USER_TICKETS("user_tickets"),
	TICKETS_DATE_TIME("tickets_date_time"),
	TICKETS_SEATS("tickets_seats"),
	EVENT_NAME("event_name"),
	AMOUNT_OF_TICKETS("amount_of_tickets"),
	;
	
	/**
	 * This is instance that keeps parameter names
	 */
	private String value;

	private ParamList(String value) {
		this.value = value;
	}

	public String get() {
		return value;
	}
	
}
