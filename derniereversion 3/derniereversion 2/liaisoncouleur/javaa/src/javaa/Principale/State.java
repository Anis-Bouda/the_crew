package Principale ;

public enum State {
        TRUE,
        FALSE,
        UNKNOWN,
        ERROR,;

	public static State fromString(String value) {
        switch (value.toUpperCase().replace("[", "").replace("]", "")) {
            case "TRUE":
                return State.TRUE;
            case "FALSE":
                return State.FALSE;
            case "UNKNOWN":
                return State.UNKNOWN;
            case "ERROR":
                return State.ERROR;
            default:
                throw new IllegalArgumentException("Unknown State: " + value);
        }
    }
    
}
