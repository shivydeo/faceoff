package org.webonise.automation.core;

import java.io.File;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ObjectRepository {

    private static Element root = null;
    private static NodeList tcNode = null;
    private static NodeList commonNode = null;
    File file = null;
    private static HashMap<String, String> hmap = new HashMap<String, String>();

    public void setTCNode(String tcName) {

        try {
            tcNode = root.getElementsByTagName(tcName);
        } catch (NullPointerException e) {
            System.err.println("TC or comon not defined.Please check Object Repo file");
        }
    }

    public void readCommonNode() {

        hmap.clear();

        for (int temp = 0; temp < commonNode.getLength(); temp++) {
            Node nNode = commonNode.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                for (int k = 1; k < eElement.getChildNodes().getLength(); k = k + 2) {
                    String key = eElement.getChildNodes().item(k).getNodeName();
                    System.err.println("Key is : " + key);
                    String value = eElement.getChildNodes().item(k).getTextContent();
                    System.err.println("Value is : " + value);

                    hmap.put(key, value);
                }
            }
        }
    }

    public String getObjID(String varName) {
        if (hmap.containsKey(varName)) {
            return hmap.get(varName);
        } else {
            return getObj(varName);
        }
    }

    public String getObj(String objName) {
        //System.out.println("VAR NAME IS " + objName);
        NodeList varList, varList2 = null;
        Node varNode;
        try {
            varList = tcNode.item(0).getChildNodes();
            varList2 = commonNode.item(0).getChildNodes();
            for (int j = 0; j < varList.getLength(); j++) {

                varNode = varList.item(j);
                if (varNode.getNodeName() != "#text") {
                    if (varNode.getNodeName().equals(objName)) {
                        //System.out.println("Return Value is - " + varNode.getTextContent());
                        return varNode.getTextContent();
                    }
                }
            }
            for (int j = 0; j < varList.getLength(); j++) {

                varNode = varList.item(j);
                if (varNode.getNodeName() != "#text") {
                    if (varNode.getNodeName().equals(objName)) {
                        //System.out.println("Return Value is - " + varNode.getTextContent());
                        return varNode.getTextContent();
                    }
                }
            }
        } catch (NullPointerException e) {
            System.err.println("objectRepository not defined.Please define Object Repo file");
        }
        System.out.println("Return Value is - Invalid tcName or var name");
        return "Invalid tcName or tag name in object repo or variable name";
    }
}