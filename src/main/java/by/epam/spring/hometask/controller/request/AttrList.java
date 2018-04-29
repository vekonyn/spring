package by.epam.spring.hometask.controller.request;

/**
 * <code>AttrList</code> enum keeps allowed request attribute names
 */
public enum AttrList {


	REQUEST_RESULT("requestResult"),
	ERROR_MESSAGE("errorMessage"),
	SUCCESS_MESSAGE("successMessage"),
	SUCCESS_OP("success"),
	FAILED_OP("failed"),
	TICKETS_PRICE("tickets_price")
	;
	
	/**
	 * This is instance that keeps attribute names
	 */	
	private String value;

	private AttrList(String value) {
		this.value = value;
	}

	public String get() {
		return value;
	}

}
