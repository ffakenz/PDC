package ar.edu.ubp.pdc.xml;

import java.io.IOException;
import java.io.Writer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class MyXML {

	private SchemaFactory schemaFactory;	   
	private DocumentBuilderFactory factory;
	
	private Document document;
	private XPath xPath;
	
	public MyXML(String xmlPath, String xsdPath) {
		schemaFactory   = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");	   
		factory  = DocumentBuilderFactory.newInstance();  
		parseWithXSD(xmlPath, xsdPath);
	}
	
	public XPath getXPath() {
		return this.xPath;
	}
	
	public XPathExpression compile(String selector) {
		try {
			return this.xPath.compile(selector);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return null; //non reachable statement
	}
	
	public Object evaluate(String selector, Object item, QName returnType) {
		try {
			XPathExpression ex = compile(selector);
			return ex.evaluate(item, returnType);
		} catch(XPathExpressionException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null; //non reachable statement
	}
	
	public Stream<Node> getNodes(String selector) {	
		
		
		NodeList nodes = NodeList.class.cast(evaluate(selector, this.document, XPathConstants.NODESET));
		Stream<Node> nodeStream = IntStream.range(0, nodes.getLength()).mapToObj(nodes::item);		
		return nodeStream;
	}
	
	private void parseWithXSD(String xmlPath, String xsdPath) {
		try {
			Schema schema = schemaFactory.newSchema(new Source[] { new StreamSource(xsdPath) });	
			factory.setValidating(false);
			//factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
			
			factory.setNamespaceAware(true);                              
			factory.setSchema(schema);
	        DocumentBuilder builder  = factory.newDocumentBuilder();
	        document = builder.parse(new InputSource(xmlPath));
	        
			xPath =  XPathFactory.newInstance().newXPath();				
		} catch(IOException | SAXException | ParserConfigurationException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}	
	}
	
	public void transformWith(String xslPath, Writer writer) {		
		try {
			StreamResult result = new StreamResult(writer);
	        
			TransformerFactory tFactory = TransformerFactory.newInstance();
	        StreamSource stylesource = new StreamSource(xslPath); 
		    Transformer transformer = tFactory.newTransformer(stylesource);
		    DOMSource doc = new DOMSource(this.document);
		    transformer.transform(doc, result);
		    
		    
		} catch(TransformerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}		              
	}	
}
