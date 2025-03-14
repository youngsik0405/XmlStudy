/*==================
	XMLDOM.java
===================*/

package com.test;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLDOM
{
	public static String getText(Element parent, String tagName)
	{
		// 반환할 결과값
		String result = "";
		
		// 대상 태그(tagName) 객체의 첫 번째 자식 노드 얻어오기
		Node node = parent.getElementsByTagName(tagName).item(0);
		Element element = (Element)node;
		
		// 대상 엘리먼트(element)의 자식 노드(텍스트 노드)의 값 얻어오기
		//result = element.getChildNodes().item(0).getNodeValue();
		//result = element.getTextContent().replaceAll("<br>", "\n");
		result = element.getTextContent();
		
		
		// 최종 결과값 반환
		return result;
	}
}
