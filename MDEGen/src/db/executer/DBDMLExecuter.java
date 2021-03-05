package db.executer;

import db.connection.DBConnectionManager;
import db.connection.NoConnectionException;
import db.connection.TypeKeyManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Execution of DML Commands
 */
public class DBDMLExecuter {
	private DMLStatementTextCreator dml;
	private DBConnectionManager mgr;
	public DBDMLExecuter() {
		super();
		this.mgr = DBConnectionManager.getTheInstance();
		this.dml = new DMLStatementTextCreator();
	}
	public Integer getNextId() throws PersistenceException {
		try {return mgr.getNextId();}catch(NoConnectionException | SQLException e) {throw new PersistenceException(e.getMessage());}
	}
	public boolean isEmpty(String tableName) throws SQLException, NoConnectionException {
		ResultSet rs = stmt().executeQuery(this.dml.selectCount(tableName));
		rs.next();
		return Integer.valueOf(rs.getInt(1)).equals(0);
	}
	public void insertInto(String tableName, String columnNames, String values) throws SQLException, NoConnectionException {
		stmt().executeUpdate(this.dml.insertInto(tableName, columnNames, values));
	}
	public void deleteFromRelationTable(String tableName, Integer p1, Integer p2) throws SQLException, NoConnectionException {
		stmt().executeUpdate(this.dml.deleteFromRelationTable(tableName, p1, p2));
	}
	public ResultSet selectIdsOfEntriesOfTable(String tableName, Integer typeKey) throws SQLException, NoConnectionException {
		return stmt().executeQuery(this.dml.selectIdsOfEntriesOfTable(tableName, typeKey));
	}
	public ResultSet selectEntriesOfTable(String tableName, Integer typeKey) throws SQLException, NoConnectionException {
		return stmt().executeQuery(this.dml.selectEntriesOfTable(tableName, typeKey));
	}
	public ResultSet selectAllEntriesOfRelationTable(String tableName) throws SQLException, NoConnectionException {
		return stmt().executeQuery(this.dml.selectAllEntriesOfRelationTable(tableName));
	}
	public ResultSet selectAllEntriesOfTypeKeyTable(String serviceName) throws SQLException, NoConnectionException {
		return stmt().executeQuery(this.dml.selectAllEntriesOfTypeKeyTable(serviceName));
	}
	public ResultSet selectIdSpecifiedCursorAleadyAtFirstRow(String tableName, Integer id) throws SQLException, NoConnectionException {
		ResultSet rs = stmt().executeQuery(this.dml.selectIdSpecifiedCursorAleadyAtFirstRow(tableName, id));
		boolean hasRow = rs.next();
		if(!hasRow) throw new SQLException("Object with id = " + id + " not found in table " + tableName);
		return rs;
	}
	public void update(String tableName, String columnName, String value, Integer id) throws SQLException, NoConnectionException {
		stmt().executeUpdate(this.dml.update(tableName, columnName, value, id));
	}
	
/**	
 * Retrieves the typename of the object with <id> stored in table <tableName> 
 * @throws PersistenceException 
 */
	public String getNameOfConcreteType(Integer id, String tableName, String serviceName) throws PersistenceException {
		ResultSet rs1,rs2 = null;
		try {
			rs1 = stmt().executeQuery("SELECT typeKey FROM " + tableName + " WHERE id = " + id);
			if(!rs1.next()) throw new PersistenceException("Retrieving typekey for id " + id + ", but found no entry in table " + tableName);
			Integer typeKey = rs1.getInt("typeKey"); // Type Key is in column 2
			return TypeKeyManager.getTheInstance().getTypeName(serviceName, typeKey);
		} catch (SQLException | NoConnectionException e) {
			throw new PersistenceException(e.getMessage());
		}
	}
	
	private Statement stmt() throws SQLException, NoConnectionException {
		return mgr.get().createStatement();
	}
	
}
