package db.executer;

public class PersistenceException extends Exception {
	public PersistenceException(String message) {
		super("Exception when trying to communicate with database: " + message);
	}
}
