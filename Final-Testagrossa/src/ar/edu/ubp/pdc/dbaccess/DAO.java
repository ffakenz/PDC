package ar.edu.ubp.pdc.dbaccess;

import java.sql.Connection;

public abstract class DAO {
	protected Connection connection;

    protected DAO(Connection connection) {
        this.connection = connection;
    }
}
