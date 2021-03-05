package db.connection;

public class NoConnectionException extends Exception {
	public NoConnectionException(String message) {
		super(message);
	}
}
