package ar.edu.ubp.pdc.dao;

public interface ConnectionConfig {
	public default String getDriver() { return "com.microsoft.sqlserver.jdbc.SQLServerDriver"; }
	public default String getDBName() { return "pdc"; }
	public default String getDBUrl() { return "jdbc:sqlserver://bilbo;databaseName=" + getDBName() + ";"; }
	public default String getUsername() { return "frtestagrossa"; }
	public default String getPassword() { return "93337511"; }
}
