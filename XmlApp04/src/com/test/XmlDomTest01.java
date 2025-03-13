/*==============================================
	XmlDomTest01.java
	- 콘솔 기반 자바 프로그램
	- XML DOM 활용 → 로컬(local) XML 읽어내기
	  (VEHICLES.xml)
================================================*/

package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest01
{
	public static void main(String[] args)
	{
		// 1. XML 파일을 메모리에 로드
		//	  → XML DOM 형성(구축)
		// 2. 루트 엘리먼트 접근
		// 3. 특정 하위 엘리먼트 접근
		//    → 위치, 이름 등을 기준으로 접근
		// 4. 텍스트 노드(속성 노드) 접근
		// 5. 데이터 획득
		// 6. 결과 처리(출력)
		
		try
		{
			// XML 파일을 메모리에 로드시킬 수 있도록 준비
			// → XML DOM 형성을 위해 필요한 준비
			//	  (이를 위해 필요한 리소스 구성)
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlObj = null;
			
			// XML 파일을 메모리에 로드(적재) → XML DOM 형성(구축)
			String url = "VEHICLES.xml";
			xmlObj = builder.parse(url);
			
			// 루트 엘리먼트 접근
			Element root = xmlObj.getDocumentElement();
			
			// 확인
			//System.out.println(root.getNodeName());
			//--==>> VEHICLES
			
			// 루트 엘리먼트를 통해 특정 하위 엘리먼트 접근(VEHICLE)
			// → 위치, 이름을 기준으로 접근
			// 『getElementsByTagName()』메소드는 태그의 이름을 가지고
			// 자식(자손) 노드에 접근할 수 있도록 해주는 메소드
			NodeList vehicleNodeList = root.getElementsByTagName("VEHICLE");
			
			// ※ NodeList 객체에 들어있는 Node 의 개수를
			//	  『getLength()』 메소드를 통해 확인할 수 있다.
			
			// 확인
			//System.out.println(vehicleNodeList.getLength());
			//--==>> 10
			
			for (int i=0; i<vehicleNodeList.getLength(); i++)	//-- 10번 반복
			{
				// NodeList 객체의 『item()』 메소드는
				// 파라미터에 해당하는 인덱스에 위치한 노드 접근 메소드
				Node vehicleNode = vehicleNodeList.item(i);		//-- i → 0 ~ 9
				
				// 캐스트 연산자를 이용하면
				// Node 객체를 Element 객체로 변환하는 것이 가능하다.
				// Node 는 상위 자료형, Element 는 하위 자료형으로 다루고 있기 때문이다.
				Element vehicleElement = (Element)vehicleNode;
				
				/*
				NodeList makeNodeList = vehicleElement.getElementsByTagName("MAKE");
				Node makeNode = makeNodeList.item(0);
				Element makeElement = (Element)makeNode;
				System.out.printf("%s : %s%n", makeElement.getNodeName()
											  , makeElement.getTextContent());
				
				NodeList modelNodeList = vehicleElement.getElementsByTagName("MODEL");
				Node modelNode = modelNodeList.item(0);
				Element modelElement = (Element)modelNode;
				System.out.printf("%s : %s%n", modelElement.getNodeName()
											  , modelElement.getTextContent());
				*/
				
				// 특정 엘리먼트의 텍스트 데이터를 얻는
				// 사용자 정의 메소드(→ getText())	호출
				System.out.printf("%s %s %s %s %s%n", getText(vehicleElement, "MAKE")
													, getText(vehicleElement, "MODEL")
													, getText(vehicleElement, "YEAR")
													, getText(vehicleElement, "PICTURE")
													, getText(vehicleElement, "STYLE"));
			}
			
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	private static String getText(Element parent, String tagName)
	{
		// 반환할 결과값
		String result = "";
		
		// 특정 태그 이름을 가진 객체의 첫 번째 자식 노드를 얻어온 다음
		Node node = parent.getElementsByTagName(tagName).item(0);
		Element element = (Element)node;
		
		// 특정 엘리먼트의 자식 노드(Text Node)의 값(nodeValue)를 얻어올 수 있도록 처리
		result = element.getChildNodes().item(0).getNodeValue();
		
		// 결과값 반환
		return result;
	}
	
} 
