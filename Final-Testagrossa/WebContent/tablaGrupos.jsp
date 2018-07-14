<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp"
    import="java.util.List, java.util.stream.*,ar.edu.ubp.pdc.beans.GrupoBean"%>
<%
	@SuppressWarnings("unchecked")
	List<GrupoBean> grupos = (List<GrupoBean>) request.getAttribute("grupos");
	String nroCodigoGrupo = (String) request.getAttribute("nroCodigoGrupo");

	String result = "";
	if(grupos.size() == 0) {
		result = "<h2>No existe tipos de certificados para el grupo elegido</h2>";
	} else {		
		String options = grupos.stream().map( g -> {
			String descTipoCertificado = g.getDescTipoCertificado();
			Short nroTipoCertificado = g.getNroTipoCertificado();
			return "<tr>"
						+ "<td> " + descTipoCertificado + " </td>" 
						+ "<td> " + String.valueOf(nroTipoCertificado) + " </td>" 
						+ "<td> <input type=\"button\" value=\"Eliminar\"" 
						+ "data-nrocertificado=\"" + String.valueOf(nroTipoCertificado) + "\""
						+ "data-nrocodigogrupo=\"" + nroCodigoGrupo + "\""
						+ "</td>" 
					+ "</tr>";
		}).collect(Collectors.joining());
		
		result = "<table>" +
					"<thead>" +
						"<tr>" +
							"<th>DescTipoCertificado</th>" +
							"<th>NroTipoCertificado</th>" +
							"<th>Eliminar</th>" +
						"</tr>" +
					"</thead>" +
					"<tbody>" +
					options +
				 	"</tbody>" +
				"</table>";
		
	}
%>
<%= result %>
