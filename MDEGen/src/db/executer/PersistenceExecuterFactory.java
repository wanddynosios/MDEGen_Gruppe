package db.executer;
/**
 * Implementations decide whether we work with or without a database 
 */
public abstract class PersistenceExecuterFactory {
	public static PersistenceExecuterFactory getConfiguredFactory() {
		return new DBExecuterFactory();
	}
	public abstract PersistenceDDLExecuter getDBDDLExecuter();
	public abstract DBDMLExecuter getDBDMLExecuter();
}