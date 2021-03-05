package db.executer;

public class DBExecuterFactory extends PersistenceExecuterFactory {
	@Override
	public PersistenceDDLExecuter getDBDDLExecuter() {
		return new DBDDLExecuter();
	}
	@Override
	public DBDMLExecuter getDBDMLExecuter() {
		return new DBDMLExecuter();
	}
}
