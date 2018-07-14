package ar.edu.ubp.pdc.xml;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.xml.xpath.XPathConstants;

public class Test {
	public static void main(String[] args) throws IOException {
		
		
		String xsdPath = "../Final-Testagrossa/WebContent/WEB-INF/xml/grupos.xsd";
	    String xmlPath = "../Final-Testagrossa/WebContent/WEB-INF/xml/grupos.xml";
	    MyXML xml = new MyXML(xmlPath, xsdPath);   
	   
	    String options = xml.getNodes("/grupos/grupo")
	    		.map( node -> {
	    			String codigo = (String) 
	    					xml.evaluate("@codigo", node, XPathConstants.STRING);
	    			String valor = (String) 
	    					xml.evaluate("text()", node, XPathConstants.STRING);
	    			System.out.println(codigo + valor);
	    			return "<option val=\"" + codigo + "\"> " + valor + "</option>";
	    			
	    		})
	    		.collect(Collectors.joining());
		
	    System.out.println(options);

	}
}
