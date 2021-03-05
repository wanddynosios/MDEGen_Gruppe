package utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import db.connection.NoConnectionException;
import db.executer.DBDMLExecuter;
import db.executer.PersistenceException;
import db.executer.PersistenceExecuterFactory;
/**
 * Performs initial load of all relations of type <tableName>
 */
public class InitialRelationLoader {
	private String tableName;
	private DBDMLExecuter dmlExecuter;
	public InitialRelationLoader(String tableName) {
		super();
		this.tableName = tableName;
		this.dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
	}
	public Map<Integer, IntegerPair> perform() throws PersistenceException{
		Map<Integer, IntegerPair> result = new HashMap<>();
		try {
			ResultSet rs = this.dmlExecuter.selectAllEntriesOfRelationTable(tableName);
			while(rs.next()) result.put(rs.getInt(1), new IntegerPair(rs.getInt(2), rs.getInt(3)));
		} catch (SQLException | NoConnectionException e) {
			throw new PersistenceException("Error when retrieving relation objects during initial load: " + e.getClass().getName() + ": " + e.getMessage());
		}
		return result;
	}
	public class IntegerPair{
		private Integer p1;
		private Integer p2;
		public IntegerPair(Integer p1, Integer p2) {
			super();
			this.p1 = p1;
			this.p2 = p2;
		}
		public Integer getP1() {
			return this.p1;
		}
		public Integer getP2() {
			return this.p2;
		}
	}
}
