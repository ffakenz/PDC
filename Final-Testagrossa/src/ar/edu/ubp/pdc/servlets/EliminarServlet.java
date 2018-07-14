package ar.edu.ubp.pdc.servlets;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.pdc.dao.DAOType;
import ar.edu.ubp.pdc.dao.GruposDAO;
import ar.edu.ubp.pdc.dbaccess.SQLServerDAOManager;
import ar.edu.ubp.pdc.utilities.ServletsUtil;

/**
 * Servlet implementation class GuardarServlet
 */
@WebServlet("/eliminar.jsp")
public class EliminarServlet extends HttpServlet implements ServletsUtil{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie [] cookies = request.getCookies();
		if(cookies != null) {
			GruposDAO impl = (GruposDAO) 
	        		new SQLServerDAOManager().getDAO(DAOType.GRUPOS);
			
			Stream.of(cookies)
				.filter(c -> !c.getName().equals("JSESSIONID"))
				.forEach(c -> {
					String codGrupo = c.getValue();    		
					Short nroTipoCertificado = Short.parseShort(c.getName());
					impl.deleteCertificado(codGrupo, nroTipoCertificado);				
			});
			
			String codGrupo = Stream.of(cookies)
					.filter(c -> !c.getName().equals("JSESSIONID"))
					.map(c -> c.getName())
					.collect(Collectors.joining());
			request.setAttribute("codigo_grupo", codGrupo);
		}
		this.gotoPage(this.getServletContext(), "/grupos.jsp", request, response);
	}

}
