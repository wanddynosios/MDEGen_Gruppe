package db.executer;

import db.connection.DBConnectionData;
import db.connection.NoConnectionException;

import java.sql.SQLException;

public interface PersistenceDDLExecuter {

	void openDBConnection(DBConnectionData connectionData)
			throws ClassNotFoundException, SQLException, NoConnectionException;

	/**	
	 * Extends the given connection to a concrete database with name <servicename>
	 */
	void establishConnectionToDatabase(String servicename)
			throws ClassNotFoundException, SQLException, NoConnectionException;

	void closeConnection() throws SQLException;

	void createDatabase(String servicename) throws SQLException, NoConnectionException;

	void createClassTable(String servicename, String classname, String attributeList)
			throws SQLException, NoConnectionException;

	void createRelationTable(String associationName, String p1Type, String p2Type)
			throws SQLException, NoConnectionException;

	boolean typeKeysTableExists(String servicename) throws SQLException, NoConnectionException;
	
	void createTypeKeyTable(String servicename) throws SQLException, NoConnectionException;

	void createTypeKeyTableEntry(String servicename, String relName) throws SQLException, NoConnectionException;

	boolean tableExists(String tableName) throws SQLException, NoConnectionException;

}