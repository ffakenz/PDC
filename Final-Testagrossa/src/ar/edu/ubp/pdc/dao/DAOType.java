package ar.edu.ubp.pdc.dao;

import java.sql.Connection;

import ar.edu.ubp.pdc.dbaccess.DAO;

public enum DAOType {
	
	//EACH TYPE SHOULD BE COMMA SEPARATED
	GRUPOS {
		@Override
		public DAO getInstance(Connection con) {
			return new GruposDAO(con);
		}
	};
    
    public abstract DAO getInstance(Connection con);
    
	    
}
