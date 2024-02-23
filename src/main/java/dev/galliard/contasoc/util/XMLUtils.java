package dev.galliard.contasoc.util;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtils {
    
    private Document document;
    private String filePath;
    
    public XMLUtils(String filePath) {
        this.filePath = filePath;
        loadDocument();
    }
    
    private void loadDocument() {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void saveDocument() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addNode(String parentNodeName, String nodeName, String nodeValue) {
        Element parentNode = (Element) document.getElementsByTagName(parentNodeName).item(0);
        Element newNode = document.createElement(nodeName);
        newNode.appendChild(document.createTextNode(nodeValue));
        parentNode.appendChild(newNode);
    }
    
    public void updateNodeValue(String nodeName, String newValue) {
        NodeList nodeList = document.getElementsByTagName(nodeName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            node.setTextContent(newValue);
        }
    }
    
    public void removeNode(String nodeName) {
        NodeList nodeList = document.getElementsByTagName(nodeName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            node.getParentNode().removeChild(node);
        }
    }
    
    public void printDocument() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        XMLUtils xmlUtils = new XMLUtils("example.xml");
        
        // Añadir un nuevo nodo
        xmlUtils.addNode("books", "book", "Nuevo Libro");
        
        // Actualizar el valor de un nodo existente
        xmlUtils.updateNodeValue("author", "Nuevo Autor");
        
        // Eliminar un nodo
        xmlUtils.removeNode("publisher");
        
        // Guardar los cambios en el archivo XML
        xmlUtils.saveDocument();
        
        // Imprimir el documento XML
        xmlUtils.printDocument();
    }
}
