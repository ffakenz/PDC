<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<%@ tag import="ar.edu.ubp.pdc.dbaccess.SQLServerDAOManager" %>
<%@ tag import="ar.edu.ubp.pdc.dao.DAOType" %>
<%@ tag import="ar.edu.ubp.pdc.xml.MyXML" %>
<%@ tag import="javax.xml.xpath.XPathConstants" %>
<%@ tag import="java.util.List" %>
<%@ tag import="java.util.stream.*" %>

<%@ attribute name="nombre" required="true" %>
<%@ attribute name="presentacion_datos" required="false" %> 
<%@ attribute name="todos" required="false" %>
<%@ attribute name="fun_js" required="true" %>


<%
	String path = ((PageContext)this.getJspContext()).getServletContext()
													 .getRealPath("/WEB-INF/xml/");
	String xsdPath = path + "grupos.xsd";
	String xmlPath = path + "grupos.xml";
	    
	
    MyXML xml = new MyXML(xmlPath, xsdPath);   
   
    String result = "";
    if(presentacion_datos != null && presentacion_datos.equals("L")) {
    	String options = xml.getNodes("/grupos/grupo")
    			.map( node -> {
    				String codigo = (String) 
    						xml.evaluate("@codigo", node, XPathConstants.STRING);
    				String valor = (String) 
    						xml.evaluate("text()", node, XPathConstants.STRING);
    				
    				if(todos != null && todos.equals("N") && codigo.equals("T"))
    					return "";
   					else
   						return "<option value=\"" + codigo + "\"> " + valor + "</option>";
    				
    			})
    			.collect(Collectors.joining());
    	result = "<select name=\"" + nombre + "\" onChange=\"" + fun_js  + "\"> " + options + "</select>";
    } else {
    	result = xml.getNodes("/grupos/grupo")
    			.map( node -> {
    				String codigo = (String) 
    						xml.evaluate("@codigo", node, XPathConstants.STRING);
    				String valor = (String) 
    						xml.evaluate("text()", node, XPathConstants.STRING);
    				
    				if(todos != null && todos.equals("N") && codigo.equals("T"))
    					return "";
    				else
    					return "<input type=\"radio\" name=\"" + nombre + "\" value=\"" + codigo + "\" onClick=\"" + fun_js  + "\"> " + valor + "</input>";
    				
    			})
    			.collect(Collectors.joining());
    }
    
%>
<%= result %>