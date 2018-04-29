package by.epam.spring.hometask.controller.request;

/**
 * <code>SessionAttrList</code> enum keeps allowed session attribute names
 */
public enum SessionAttrList {

    USER("user"),
    EVENT_LIST("event_list");

    /**
     * This is instance that keeps attribute names
     */
    private String value;

    private SessionAttrList(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
