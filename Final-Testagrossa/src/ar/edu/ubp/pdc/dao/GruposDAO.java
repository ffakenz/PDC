package ar.edu.ubp.pdc.dao;

import ar.edu.ubp.pdc.beans.GrupoBean;
import ar.edu.ubp.pdc.dbaccess.DAO;
import ar.edu.ubp.pdc.utilities.MyResultSet;

import java.sql.*;
import java.util.List;

public class GruposDAO extends DAO {

    private final String getQuery =
            "select desc_tipo_certificado,"
                    + "			       nro_tipo_certificado"
                    + "			  from dbo.tipos_certificados (nolock)"
                    + "			 where cod_grupo = ?";

    private final String deleteProcedure = "{ CALL dbo.del_tipo_certificado(?, ?) }";

    protected GruposDAO(final Connection connection) {
        super(connection);
    }

    public void deleteCertificado(final String codGrupo, final Short nroTipoCertificado) {
        try (final CallableStatement cs = this.connection.prepareCall(deleteProcedure)) {
            this.connection.setAutoCommit(false);

            cs.setString(1, codGrupo);
            cs.setShort(2, nroTipoCertificado);
            cs.execute();

            this.connection.commit();
            this.connection.setAutoCommit(true);

        } catch (final SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public List<GrupoBean> getGrupos(final String codigoGrupo) {

        try (final PreparedStatement ps = this.connection.prepareStatement(getQuery)) {


            ps.setString(1, codigoGrupo);

            final ResultSet rs = ps.executeQuery();

            final List<GrupoBean> grupos =
                    new MyResultSet<>(rs, GrupoBean.class).mapToObjectList();

            return grupos;

        } catch (final SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null; //non reacheable
    }

}
