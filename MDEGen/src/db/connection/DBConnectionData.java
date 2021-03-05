package db.connection;
/**
 * Connection Data for a database connection
 * DatabaseName maybe empty if we connect to mySQL Server only (e.g. before creating the database for a service)
 */
public class DBConnectionData {
	private String url;
	private String databaseName;
	private String user; 
	private String password;
	public DBConnectionData(String url, String user, String password) {
		super();
		this.url = url;
		this.databaseName = "";
		this.user = user;
		this.password = password;
	}
	public DBConnectionData(String url, String databaseName, String user, String password) {
		this(url, user, password);
		this.databaseName = databaseName;
	}
	public boolean isDatabaseNamePresent() {
		return !this.databaseName.equals("");
	}
	public String getUrl() {
		return this.url;
	}
	public String getDatabaseName() {
		return this.databaseName;
	}
	public String getUser() {
		return this.user;
	}
	public String getPassword() {
		return this.password;
	}
}
