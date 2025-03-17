/*================================================================
	XmlDomTest06.java
	- 콘솔 기반 자바 프로그램
	- XML DOM 활용 → 다운로드 받은 로컬(local) XML 읽어내기
	  ·rss.xml → 기상청 날씨누리 육상 중기 예보
================================================================*/

package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest06
{
	public static void main(String[] args)
	{
		try
		{
			// 1. XML 파일 메모리에 로드하기
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			String url = "rss.xml";
			Document xmlObj = builder.parse(url);
			
			
			// 2. 루트 엘리먼트 접근하기
			Element root = xmlObj.getDocumentElement();
			
			
			// 3. 아이템(item) 하위의 타이틀(title) 추출하기
			Node itemNode = root.getElementsByTagName("item").item(0);
			Element itemElement = (Element)itemNode;
			
			System.out.printf("%s%n%n", XMLDOM.getText(itemElement, "title"));
			//--==>> 전국 육상 중기예보 - 2025년 03월 17일 (월)요일 06:00 발표

			// 4. 기상전망(wf) 추출하기
			Node wfNode = root.getElementsByTagName("wf").item(0);
			Element wfElement = (Element)wfNode;
			
			System.out.println("[기상전망] -------------------------------------------------");
			System.out.printf("%s%n%n", wfElement.getTextContent().replaceAll("<br />", "\n"));
			
			
			// 5. 도시별 기상예보 추출하기
			System.out.println("[육상날씨] -------------------------------------------------");
			NodeList locationNodeList = root.getElementsByTagName("location");
			
			// 확인
			//System.out.println(locationNodeList.getLength());
			//--==>> 41
			
			for (int i = 0; i < locationNodeList.getLength(); i++)
			{
				Node locationNode = locationNodeList.item(i);
				Element locationElement = (Element)locationNode;
				
				System.out.printf("도시 : %s%n", XMLDOM.getText(locationElement, "city"));
				System.out.println("---------------------------------------------------");
				
				NodeList dataNodeList = locationElement.getElementsByTagName("data");
				
				// 확인
				//System.out.println(dataNodeList.getLength());
				//--==>> 11
				
				for (int j = 0; j < dataNodeList.getLength(); j++)
				{
					Node dataNode = dataNodeList.item(j);
					Element dataElement = (Element)dataNode;
					
					System.out.printf("%s / %s / %s ~ %s / %s%n"
									, XMLDOM.getText(dataElement, "tmEf")	// 날짜 시간
									, XMLDOM.getText(dataElement, "wf")		// 날씨 예보
									, XMLDOM.getText(dataElement, "tmn")	// 최저 온도
									, XMLDOM.getText(dataElement, "tmx")	// 최고 온도
									, XMLDOM.getText(dataElement, "rnSt")); // 강수 확률
				}
				System.out.println("---------------------------------------------------");
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
