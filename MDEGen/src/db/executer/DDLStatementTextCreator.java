package db.executer;

import db.DBConstants;

/**
 * Textkonserven f�r DDL-Befehle 
 */
public class DDLStatementTextCreator {
	private static final String createTable = "CREATE TABLE ";
	private static final String dropDatabase = "DROP DATABASE ";
	private static final String id = "id";
	private static final String p1 = "p1";
	private static final String p2 = "p2";
	private static final String references = "REFERENCES ";
	private static final String notNull = "NOT NULL";
	private static final String onDeleteCascade = "ON DELETE CASCADE";
	private static final String primaryKey = "PRIMARY KEY(" + id + ")";
	private static final String foreignKey = "FOREIGN KEY";
	private static final String foreignKeyP1 = "FOREIGN KEY(" + p1 + ") " + references;
	private static final String foreignKeyP2 = "FOREIGN KEY(" + p2 + ") "  + references;
	
	public String createDatabase(String serviceName) {
		return "CREATE DATABASE IF NOT EXISTS " + serviceName + ";";
	}
	public String createTypeKeyTable(String serviceName) {
		return "CREATE TABLE IF NOT EXISTS " + serviceName + DBConstants.typeKeys + " (" + id + " INT " + notNull + ", typeName VARCHAR(255), " + primaryKey +  ", INDEX typeName_index (typeName))";
	}
	public String createServiceTable(String serviceName) {
		return createTable + serviceName + " (" + id + " INT " + notNull + ", " + DBConstants.objectSequencer + " INT, " + primaryKey + ")";
	}
	public String createClassTable(String serviceName, String className, String attributes) {
		return createTable 	+ className + " (" + id + " INT " + notNull + ", typeKey INT " + notNull + ", " 
							+ attributes + primaryKey + ", " 
							+ foreignKey + "(typeKey) " + references + serviceName + DBConstants.typeKeys + "(id) ON DELETE CASCADE)"; 
	}
	public String createRelationTable(String relationName, String p1Type, String p2Type) {
		return createTable + relationName + " (" + id + " INT " + notNull + ", " + p1 + " INT " + notNull + ", " + p2 + " INT " + notNull + ", " + 
				primaryKey + ", " +
				foreignKeyP1 + p1Type + "(" + id + ") " + onDeleteCascade + ", " +
				foreignKeyP2 + p2Type + "(" + id + ") " + onDeleteCascade + 
				")";
	}
}
