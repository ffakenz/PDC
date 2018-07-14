package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.pdc.beans.GrupoBean;
import ar.edu.ubp.pdc.dao.DAOType;
import ar.edu.ubp.pdc.dao.GruposDAO;
import ar.edu.ubp.pdc.dbaccess.SQLServerDAOManager;
import ar.edu.ubp.pdc.utilities.ServletsUtil;

/**
 * Servlet implementation class GruposServlet
 */
@WebServlet("/grupos.jsp")
public class GruposServlet extends HttpServlet implements ServletsUtil {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GruposServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nroCodigoGrupo = request.getParameter("codigo_grupo") != null ?
				request.getParameter("codigo_grupo") : 
					(String) request.getAttribute("codigo_grupo");   

        		
		GruposDAO impl = (GruposDAO) 
        		new SQLServerDAOManager().getDAO(DAOType.GRUPOS);
        
        List<GrupoBean> grupos = impl.getGrupos(nroCodigoGrupo);
        
        request.setAttribute("grupos", grupos);
        request.setAttribute("nroCodigoGrupo", nroCodigoGrupo);
        
        gotoPage(this.getServletContext(), "/tablaGrupos.jsp", request, response);
	}

}
