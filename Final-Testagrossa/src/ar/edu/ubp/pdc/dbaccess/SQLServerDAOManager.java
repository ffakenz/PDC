package ar.edu.ubp.pdc.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ar.edu.ubp.pdc.dao.ConnectionConfig;
import ar.edu.ubp.pdc.dao.DAOType;

public class SQLServerDAOManager implements ConnectionConfig{	
	
	private Connection connection;
	private String DRIVER;
	private String URL;
	private String USERNAME;
	private String PASSWORD;	
	
	public SQLServerDAOManager() {
		//Dependencies injected via ConnectionConfig
		DRIVER = getDriver();
		URL = getDBUrl();
		USERNAME = getUsername();
		PASSWORD = getPassword();
		
		try {			
			Class.forName(DRIVER).newInstance();
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	//FACTORY METHOD
	public DAO getDAO(DAOType type) { return type.getInstance(connection); }	
	

	
}