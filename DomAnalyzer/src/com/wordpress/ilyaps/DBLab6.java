package com.wordpress.ilyaps;

import org.w3c.dom.*;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DBLab6 {
    private Scanner sc;
    private PrintStream ps;
    private Node node;
    private DomAnalyzer da;
    private int choice;
    private boolean isDefault;
    private String defInputFile = "Team.xml";
    private String defOutputFile = "C:\\Prog\\xml\\NewTeam.xml";
//    private String defOutputFile = "NewTeam.xml";

    public DBLab6() {
        this.sc  = new Scanner(System.in);
        this.ps = System.out;
        this.node  = null;
        this.da  = new DomAnalyzer();
        this.choice = -1;
        this.isDefault = true;
    }

    public boolean isFinish() {
        return choice == 0;
    }

    public int scanChoice() {
        ps.print("->> ");
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            choice = -1;
        }
        return choice;
    }

    private void launchGetAttr() {
        ps.print("Input id player: ");
        String id = sc.nextLine();

        Element element = da.getElementById("Player7");
        if (element == null) {
            return;
        }
        Map<String, String> map = da.getAttributes(element);
        System.out.println(map);
    }

    private void launchGetElementsById() {
        ps.print("Input id player: ");
        String id = sc.nextLine();

        Element element = da.getElementById(id);
        System.out.println(element.getTagName() + ": " +
                element.getElementsByTagName("Name").item(0).getTextContent());
    }

    private void launchGetElementByXPath() {
        ps.print("Input XPath: ");
        //String xPath = sc.nextLine();
        String xPath = "/Team/Player/Name";

        Node node = da.getNodeByXPath(xPath);
        System.out.println(node.getChildNodes().item(0).getNodeValue());
    }

    private void launchGetElementsByXPath() {
        ps.print("Input XPath: ");
        //String xPath = sc.nextLine();
        String xPath = "/Team/Player";

        NodeList nodeList = da.getNodesByXPath(xPath);
        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node node = nodeList.item(temp);
            Element element = (Element) node;
            System.out.print(element.getAttribute("Num") + " ");
            System.out.println(element.getAttribute("Position"));
        }
    }

    private void launchGetProcessingInstruction() {
        ps.print("Input name tag: ");
        String tagName = sc.nextLine();

        List<ProcessingInstruction> processingInstructions = da.getProcessingInstructionsByTegName(tagName);
        for (ProcessingInstruction processingInstruction: processingInstructions) {
            System.out.print(processingInstruction.getTarget() + ": ");
            System.out.println(processingInstruction.getData());
        }
    }

    private void launchGetComment() {
        ps.print("Input name tag: ");
        String tagName = sc.nextLine();

        List<Comment> comments = da.getCommentsByTegName(tagName);
        for (Comment comment: comments) {
            System.out.println(comment.getData());
        }
    }

    private void launchSetValueNode() {
        ps.print("Input value node: ");
        String strValNode = sc.nextLine();

         if (node != null) {
            da.setValueNode(node, strValNode);
        }
    }

    private void launchAddNode() {
        if (node != null) {
            da.addNode(node);
        }
    }

    private void launchAddNewAttr() {
        ps.print("Input id player: ");
        String strIdPlayer = sc.nextLine();
        Node node = da.getNodeByXPath("/Team/Player[@PlayerID='" + strIdPlayer + "']");
        if (node == null) {
            return;
        }
        ps.print("Input name attr: ");
        String nameAttr = sc.nextLine();
        ps.print("Input value attr: ");
        String valAttr = sc.nextLine();
        da.addAttributeToNode(node, nameAttr, valAttr);
    }

    private void launchCreatingNewNode() {
        ps.print("Input name tag: ");
        String tagName = sc.nextLine();
        node = da.createNewNode(tagName);
    }

    private void launchDeleteNode() {
        ps.print("Input deleting id player: ");
        String strIdPlayer = sc.nextLine();
        Node node = da.getNodeByXPath("/Team/Player[@PlayerID='" + strIdPlayer + "']");
        if (node != null) {
            da.deleteNode(node);
        }
    }

    private void launchGetElementsByTegName() {
        ps.print("Input tagname ");
        String tagname = sc.nextLine();

        NodeList nodeList = da.getNodesByTegName(tagname);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println(node.getChildNodes().item(0).getNodeValue());
        }
    }

    private void launchSaveToFile() {
        String path;

        if (isDefault) {
            path = defOutputFile;
            ps.println("Input name file: " + path);
        } else {
            ps.print("Input name file: ");
            path = sc.nextLine();
        }

        if (da.saveToFile(path)) {
            ps.println("Root element: " + da.getDoc().getDocumentElement().getNodeName());
        } else {
            ps.println("Error");
        }
    }

    private void launchLoadFile() {
        String path;

        if (isDefault) {
            path = defInputFile;
            ps.println("Input name file: " + path);
        } else {
            ps.print("Input name file: ");
            path = sc.nextLine();
        }

        if (da.loadDocument(path)) {
            ps.println("Root element: " + da.getDoc().getDocumentElement().getNodeName());
        } else {
            ps.println("Error");
        }
    }

    public void run() {
        while (!isFinish()) {
            printMenu();

            switch (scanChoice()) {
                case 1:
                    launchLoadFile();
                    break;
                case 2:
                    launchSaveToFile();
                    break;
                case 31:
                    launchGetElementsByTegName();
                    break;
                case 32:
                    launchGetElementsById();
                    break;
                case 33:
                    launchGetElementsByXPath();
                    break;
                case 34:
                    launchGetElementByXPath();
                    break;
                case 41:
                    //launchGetElementsByTegName();
                    break;
                case 42:
                    //launchGetElementsByTegName();
                    break;
                case 43:
                    launchGetComment();
                    break;
                case 44:
                    launchGetProcessingInstruction();
                    break;
                case 45:
                    launchGetAttr();
                    break;
                case 51:
                    launchDeleteNode();
                    break;
                case 52:
                    launchSetValueNode();
                    break;
                case 53:
                    launchCreatingNewNode();
                    break;
                case 54:
                    launchAddNode();
                    break;
                case 55:
                    launchAddNewAttr();
                    break;
            }
        }
        System.out.print("The end.");
    }


    public void printMenu() {
        ps.println("\t\t0 = exit");
        ps.println("\t\t1 = load file");
        ps.println("\t\t2 = save file");
        ps.println("\t\t-------");
        ps.println("\t\t31 = 2.1 с помощью метода GetElementsByTegName");
        ps.println("\t\t32 = 2.2 с помощью метода GetElementsById");
        ps.println("\t\t33 = 2.3 с помощью метода SelectNodes");
        ps.println("\t\t34 = 2.4 с помощью метода SelectSingleNodes");
        ps.println("\t\t-------");
        //ps.println("\t\t41 = 3.1 к узлам типа XmlElement");
        //ps.println("\t\t42 = 3.2 к узлам типа XmlText");
        ps.println("\t\t43 = 3.3 к узлам типа XmlComment");
        ps.println("\t\t44 = 3.4 к узлам типа XmlProcessingInstruction");
        ps.println("\t\t45 = 3.5 к атрибутам узлов");
        ps.println("\t\t-------");
        ps.println("\t\t51 = 4.1 удаление содержимого");
        ps.println("\t\t52 = 4.2 внесение изменений в содержимое IN NEW");
        ps.println("\t\t53 = 4.3 создание нового содержимого IN NEW");
        ps.println("\t\t54 = 4.4 вставка содержимого IN NEW");
        ps.println("\t\t55 = 4.5 добавление атрибутов");
    }

    public static void main(String[] args) {
        (new DBLab6()).run();
    }
}
