package com.wordpress.ilyaps;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilyap on 15.11.2015.
 */
public class DomAnalyzer {
    Document doc;

    public DomAnalyzer() {
        this.doc = null;
    }

    public Document getDoc() {
        return doc;
    }

    public boolean loadDocument(String path) {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setCoalescing(true);
        f.setValidating(false);
        f.setIgnoringComments(false);
        //f.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = null;
        try {
            builder = f.newDocumentBuilder();
            doc = builder.parse(new File(path));
            doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException e) {
            return false;
        } catch (SAXException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public NodeList getNodesByTegName(String tagName) {
        return doc.getElementsByTagName(tagName);
    }

    public List<Element> getElementsByTegName(String tagName) {
        List<Element> list = new ArrayList<>();
        NodeList nodeList = getNodesByTegName(tagName);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            list.add((Element) node);
        }

        return list;
    }

    public List<Comment> getCommentsByTegName(String tagName) {
        List<Comment> list = new ArrayList<>();
        NodeList nodeList = getNodesByTegName(tagName);

        for (int j = 0; j < nodeList.getLength(); j++) {
            NodeList internalNodeList = nodeList.item(j).getChildNodes();
            for (int i = 0; i < internalNodeList.getLength(); i++) {
                Node node = internalNodeList.item(i);
                if (node.getNodeType() == Element.COMMENT_NODE) {
                    list.add((Comment) node);
                }
            }
        }
        return list;
    }

    public List<ProcessingInstruction> getProcessingInstructionsByTegName(String tagName) {
        List<ProcessingInstruction> list = new ArrayList<>();
        NodeList nodeList = getNodesByTegName(tagName);

        for (int j = 0; j < nodeList.getLength(); j++) {
            NodeList internalNodeList = nodeList.item(j).getChildNodes();
            for (int i = 0; i < internalNodeList.getLength(); i++) {
                Node node = internalNodeList.item(i);
                if (node.getNodeType() == Element.PROCESSING_INSTRUCTION_NODE) {
                    list.add((ProcessingInstruction) node);
                }
            }
        }
        return list;
    }

    public Element getElementById(String id) {
        return doc.getElementById(id);
    }

    public NodeList getNodesByXPath(String expression) {
        NodeList nodeList = null;
        XPath xPath =  XPathFactory.newInstance().newXPath();
        try {
            nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return nodeList;
    }

    public Node getNodeByXPath(String expression) {
        Node node = null;
        XPath xPath =  XPathFactory.newInstance().newXPath();
        try {
            node = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return node;
    }

    public static Map<String, String> getAttributes(Element element) {
        Map<String, String> map = new HashMap<>();
        NamedNodeMap nodeMap = element.getAttributes();

        for(int i = 0 ; i < nodeMap.getLength() ; i++) {
            Attr attribute = (Attr) nodeMap.item(i);
            map.put(attribute.getName(), attribute.getValue());
        }

        return map;
    }

    public static String getAttribute(Element element, String nameAttribute) {
        return element.getAttribute(nameAttribute);
    }

    public Node deleteNode(Node node) {
        node.getParentNode().removeChild(node);
        return node;
    }

    public Node addAttributeToNode(Node node, String nameAttr, String valueAttr) {
        ((Element)node).setAttribute(nameAttr, valueAttr);
        return node;
    }

    public Node addNode(Node childNode) {
        doc.getDocumentElement().appendChild(childNode);
        return childNode;
    }

    public Node addNodeToNode(Node parentNode, Node childNode) {
        parentNode.appendChild(childNode);
        return childNode;
    }


    public Node setValueNode(Node node, String value) {
        node.setTextContent(value);
        return node;
    }

    public Node createNewNode(String tagName) {
        return doc.createElement(tagName);
    }

    public boolean saveToFile(String path) {
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(path));
            Source input = new DOMSource(doc);
            transformer.transform(input, output);
        } catch (TransformerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String path = "team.xml";
        DomAnalyzer da = new DomAnalyzer();
        da.loadDocument(path);
    }
}
