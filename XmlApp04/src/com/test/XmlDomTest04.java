/*===============================
	XmlDomTest04.java
	- 콘솔 기반 자바 프로그램
	- XML DOM 활용 → 로컬(local) XML 읽어내기
	  (VEHICLES.xml)
===============================*/

package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest04
{
	public static void main(String[] args)
	{
		/*
		--------------------------------------------------------
		NO	MAKE	MODEL		YEAR	STYLE			PRICE
		--------------------------------------------------------
		1	Dodge	Durango		1998	Sport Utility 	18000
		--------------------------------------------------------
		2	Honda	Civic		1997	Sedan 			8000
		--------------------------------------------------------
		3	Dodge	Neon		1996	Sedan 			7000
		--------------------------------------------------------
								:
		*/
		
		//						↓
		
		/*
		--------------------------------------------------------
		NO	MAKE	MODEL		YEAR	STYLE			PRICE
		--------------------------------------------------------
		1	Dodge	Durango		1998	Sport Utility 	18000
		Opsitons ------------------------------------------------
				Power_Locks : Yes
				Power_Window : Yes
				Stereo : Radio/Cassette/CD
				Air_Conditioning : Yes
				Automatic : Yes
				Four_Wheel_Drive : Full/Partial
				Note : Very clean
		--------------------------------------------------------
		2	Honda	Civic		1997	Sedan 			8000
		Opsitons ------------------------------------------------
				Power_Locks : Yes
				Power_Window : Yes
				Stereo : Radio/Cassette
				Automatic : Yes
				Note : Like New
		--------------------------------------------------------
		3	Dodge	Neon		1996	Sedan 			7000
		Opsitons ------------------------------------------------
				Stereo : Radio/Cassette
				Automatic : Yes
				Note : Need minor body works
		--------------------------------------------------------
								: 
		*/
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlObj = null;
			
			String url = "VEHICLES.xml";
			xmlObj = builder.parse(url);
			
			Element root = xmlObj.getDocumentElement();
			
			NodeList vehicleNodeList = root.getElementsByTagName("VEHICLE");
			
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("NO	MAKE		MODEL	YEAR	STYLE			PRICE");
			System.out.println("---------------------------------------------------------------------------");
			
			for (int i = 0; i < vehicleNodeList.getLength(); i++)
			{
				Node vehicleNode = vehicleNodeList.item(i);
				
				Element vehicleElement = (Element)vehicleNode;
				
				
				System.out.printf("%2s 	%7s   %10s  %5s	%13s  %7s%n", getText(vehicleElement, "INVENTORY_NUMBER")
																	 , getText(vehicleElement, "MAKE")
																	 , getText(vehicleElement, "MODEL")
																	 , getText(vehicleElement, "YEAR")
																	 , getText(vehicleElement, "STYLE")
																	 , getText(vehicleElement, "PRICE"));
				
				// OPTIONS 추가 ---------------------------------------------------------------------------------------
				System.out.println("Opsitons ------------------------------------------------------------------");
				
				NodeList options = vehicleElement.getElementsByTagName("OPTIONS");
				Node option = options.item(0);
				Element optionElement = (Element)option;
				
				NodeList childNodes = optionElement.getChildNodes();		// check~!!!
				
				for (int k = 0; k < childNodes.getLength(); k++)
				{
					Node childNode = childNodes.item(k);
					if (childNode.getNodeType()==1)		// ELEMENT_NODE		// check~!!!
					{
						System.out.printf("\t%s : %s%n", childNode.getNodeName()
														, childNode.getTextContent());
					}
				
				}
				System.out.println("------------------------------------------------------------------");
				
				//--------------------------------------------------------------------------------------- OPTIONS 추가
				
			}
			
			
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	private static String getText(Element parent, String tagName)
	{
		String result = "";
		
		Node node = parent.getElementsByTagName(tagName).item(0);
		Element element = (Element)node;
		
		result = element.getChildNodes().item(0).getNodeValue();

		return result;
	}
	
}
