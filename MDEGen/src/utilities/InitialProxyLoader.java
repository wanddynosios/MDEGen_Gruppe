package utilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.management.monitor.StringMonitor;

import db.connection.NoConnectionException;
import db.connection.TypeKeyManager;
import db.executer.DBDMLExecuter;
import db.executer.PersistenceExecuterFactory;
import db.executer.PersistenceException;
import idManagement.Identifiable;

/**
 * Responsible for the initial creation of proxy objects from database contents
 * T must always be a Proxy Class
 * REQUIRES valid database connection to this' service database
 */
public class InitialProxyLoader<T extends Identifiable> {
	private DBDMLExecuter dmlExecuter;
	private final String typeName; // String Representation of T 
	private final String serviceName; // Current service name 
	private final String packageName; // Service Name with lower case first letter
	private final String packageGenerated; // usually = "generated" 
	private final String tableName; // The table, from which to select (usually <> typeName, if typeName has superclasses)
	public InitialProxyLoader(String packageGenerated, String serviceName, String packageName, String typeName, String tableName) {
		this.dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
		this.typeName = typeName;
		this.serviceName = serviceName;
		this.packageName = packageName;
		this.packageGenerated = packageGenerated;
		this.tableName = tableName;
	}
	public Map<Integer,T> perform() throws PersistenceException{
		Map<Integer,T> result = new TreeMap<Integer,T>();
		Constructor constructor = null;
		ResultSet resultSet = null;
		String completeProxyClassName = this.packageGenerated + "." + this.packageName + "." + "proxies." + this.typeName + "Proxy";
		try {
			Integer typeKey = TypeKeyManager.getTheInstance().getTypeKey(this.serviceName, this.typeName);
			constructor = Class.forName(completeProxyClassName).getConstructor(new Class[]{java.lang.Integer.class});
			resultSet = this.dmlExecuter.selectIdsOfEntriesOfTable(tableName, typeKey);
			while(resultSet.next()) {
				T t = (T)constructor.newInstance(new Object[] {resultSet.getInt(1)});
				result.put(t.getId(), t);
			}
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | NoConnectionException | SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new PersistenceException("Error when retrieving objects during initial load: " + e.getClass().getName() + ": " + e.getMessage());
		}
		return result;
	}
}
