package com.test;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLDOM
{
	public static String getText(Element parent, String tagName)
	{
		String result = "";
		
		Node node = parent.getElementsByTagName(tagName).item(0);
		Element element = (Element)node;
		
		result = element.getChildNodes().item(0).getNodeValue();
		
		return result;
	}
}
