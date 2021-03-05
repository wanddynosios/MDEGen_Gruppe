package db.executer;

import db.DBConstants;
import db.connection.NoConnectionException;

import java.sql.SQLException;

/**
 * Textkonserven f�r DML-Befehle 
 */
public class DMLStatementTextCreator {
	private static final String insertInto = "INSERT INTO ";
	private static final String deleteFrom = "DELETE FROM ";
	private static final String update = "UPDATE ";
	public String createTheServiceTableEntry(String serviceName) {
		return insertInto + serviceName + " VALUES (1,1)";
	}
	public String createTypeKeyTableEntry(String serviceName, Integer id, String relTypeName) throws NoConnectionException, SQLException {
		return insertInto + serviceName + DBConstants.typeKeys + " VALUES (" + id + "," + "'" + relTypeName + "')";
	}
	public String existsTable(String tableName) {
		return "SHOW TABLES LIKE '" + tableName + "'";
	}
	public String existsTypeKeysTableIn(String databaseName) {
		return "SHOW TABLES FROM " + databaseName + " LIKE '" + databaseName + DBConstants.typeKeys + "'";
	}
	public String selectIdsOfEntriesOfTable(String tableName, Integer typeKey) {
		return "SELECT id FROM " + tableName + " WHERE " + DBConstants.typeKey + " = " + typeKey;
	}
	public String selectEntriesOfTable(String tableName, Integer typeKey) {
		return "SELECT * FROM " + tableName + " WHERE " + DBConstants.typeKey + " = " + typeKey;
	}
	public String selectAllEntriesOfRelationTable(String tableName) {
		return "SELECT * FROM " + tableName;
	}
	public String insertInto(String tableName, String columnNames, String values) {
		return insertInto + tableName + " (" + columnNames + ") VALUES (" + values + ")";
	}
	public String deleteFromRelationTable(String tableName, Integer p1, Integer p2) {
		return deleteFrom + tableName + " WHERE p1 = " + p1 + " AND p2 = " + p2 + " LIMIT 1";
	}
	public String selectAllEntriesOfTypeKeyTable(String serviceName) {
		return "SELECT id, typeName FROM " + serviceName + DBConstants.typeKeys;
	}
	public String selectIdSpecifiedCursorAleadyAtFirstRow(String tableName, Integer id) {
		return "SELECT * FROM " + tableName + " WHERE id = " + id.toString();
	}
	public String update(String tableName, String columnName, String value, Integer id) {
		return update + tableName + " SET " + columnName + " = " + value + " WHERE id = " + id.toString();
	}
	public String selectCount(String tableName) {
		return "SELECT COUNT(*) FROM " + tableName;
	}
}
