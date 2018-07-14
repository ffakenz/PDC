package ar.edu.ubp.pdc.dao;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.pdc.beans.GrupoBean;
import ar.edu.ubp.pdc.dbaccess.SQLServerDAOManager;

public class Test {
	//Testing
	public static void main(String[] args) throws SQLException {
		
		String nroCodigoGrupo = "ALU";
		GruposDAO impl = (GruposDAO) 
        		new SQLServerDAOManager().getDAO(DAOType.GRUPOS);
        
        List<GrupoBean> grupos = impl.getGrupos(nroCodigoGrupo);
        
        grupos.stream().forEach(System.out::println);
        
		
        
        
		String nroCodigoGrupoToDelete = "ALU";
		Short nroCertificado = 10;
		impl.deleteCertificado(nroCodigoGrupoToDelete, nroCertificado);
		
		
		
		grupos.stream().forEach(System.out::println);
	}
}