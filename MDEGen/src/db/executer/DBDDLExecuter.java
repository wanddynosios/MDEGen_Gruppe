package db.executer;

import db.connection.DBConnectionData;
import db.connection.DBConnectionManager;
import db.connection.NoConnectionException;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Executes DDL Statements - Stateless!
 */
public class DBDDLExecuter implements PersistenceDDLExecuter {
	private DDLStatementTextCreator ddl;
	private DMLStatementTextCreator dml;
	private DBConnectionManager mgr;
	public DBDDLExecuter() {
		super();
		this.mgr = DBConnectionManager.getTheInstance();
		this.ddl = new DDLStatementTextCreator();
		this.dml = new DMLStatementTextCreator();
	}
	@Override
	public void openDBConnection(DBConnectionData connectionData) throws ClassNotFoundException, SQLException, NoConnectionException {
		mgr.openDBConnection(connectionData);
	}
/**	
 * Extends the given connection to a concrete database with name <servicename>
 */
	@Override
	public void establishConnectionToDatabase(String servicename) throws ClassNotFoundException, SQLException, NoConnectionException {
		mgr.openDBConnection(this.extendWithServiceName(mgr.getConnectionData().get(), servicename));
	}
	@Override
	public void closeConnection() throws SQLException {
		this.mgr.close();
	}
	@Override
	public void createDatabase(String servicename) throws SQLException, NoConnectionException {
		stmt().executeUpdate(ddl.createDatabase(servicename));
	}
	@Override
	public void createClassTable(String servicename, String classname, String attributeList) throws SQLException, NoConnectionException {
		stmt().executeUpdate(ddl.createClassTable(servicename, classname, attributeList));
	}
	@Override
	public void createRelationTable(String associationName, String p1Type, String p2Type) throws SQLException, NoConnectionException {
		stmt().executeUpdate(ddl.createRelationTable(associationName, p1Type, p2Type));
	}
	@Override
	public void createTypeKeyTable(String servicename) throws SQLException, NoConnectionException {
		stmt().executeUpdate(ddl.createTypeKeyTable(servicename));
	}
	@Override
	public void createTypeKeyTableEntry(String servicename, String relName) throws SQLException, NoConnectionException {
		stmt().executeUpdate(dml.createTypeKeyTableEntry(servicename, mgr.getNextId(), relName));
	}
	@Override
	public boolean tableExists(String tableName) throws SQLException, NoConnectionException {
		return stmt().executeQuery(dml.existsTable(tableName)).next();
	}
// ====================== Auxiliaries ===============================	
	private DBConnectionData extendWithServiceName(DBConnectionData current, String serviceName) {
		return new DBConnectionData(current.getUrl(), serviceName, current.getUser(), current.getPassword());
	}
	public boolean typeKeysTableExists(String servicename) throws SQLException, NoConnectionException {
		return stmt().executeQuery(dml.existsTypeKeysTableIn(servicename)).next();
	}
	private Statement stmt() throws SQLException, NoConnectionException {
		return mgr.get().createStatement();
	}
}
