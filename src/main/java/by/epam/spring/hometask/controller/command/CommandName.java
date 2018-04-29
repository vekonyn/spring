package by.epam.spring.hometask.controller.command;

/**
 * <code>CommandName</code> enum keeps allowed command names
 */
public enum CommandName {

	SAVE_USER("save_user"),
	LOGIN("login"),
	REMOVE_USER("remove_user"),
	GET_USER_BY_ID("get_user_by_id"),
	GET_USER_BY_EMAIL("get_user_by_email"),
	GET_ALL_USERS("get_all_users"),

	SAVE_EVENT("save_event"),
	REMOVE_eVENT("remove_event"),
	GET_EVENT_BY_ID("get_event_by_id"),
	GET_EVENT_BY_NAME("get_event_by_name"),
	GET_ALL_EVENTS("get_all_events"),
	VIEW_TICKETS_PRICE_FOR_EVENT("view_tickets_price_for_event"),

	GET_ALL_AUDITORIUM("get_all_auditorium"),
	GET_AUDITORIUM_BY_NAME("get_auditorium_by_name"),

	BOOK_TICKET("book_ticket"),
	GET_PURCHASED_TICKETS_FOREVENT("get_purchased_tickets_for_event"),

	WRONG_REQUEST("wrong_request");

	private String value;

	private CommandName(String value) {
		this.value = value;
	}

	public String get() {
		return value;
	}
}
