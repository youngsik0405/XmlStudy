/*======================================
	XmlDomTest05.java
	- 콘솔 기반 자바 프로그램
	- XML DOM 활용 → 로컬(local) XML 읽어내기
	  (rss.xml)
	  ※ 기상청 날씨누리로부터 얻어낸 데이터
	     
=======================================*/

package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest05
{
	public static void main(String[] args)
	{
		try
		{
			// 1. XML 파일을 메모리에 로드
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			String url = "rss.xml";
			Document xmlObj = builder.parse(url);
			
			// 2. 루트 엘리먼트 접근
			Element root = xmlObj.getDocumentElement();
			
			// 3-1. 타이틀 추출하기
			Node itemNode = root.getElementsByTagName("item").item(0);
			Element itemElement = (Element)itemNode;
			
			System.out.printf("%s%n%n", XMLDOM.getText(itemElement, "title"));
			
			// 3-2. 예보요약 추출하기
			Node summaryNode = root.getElementsByTagName("summary").item(0);
			Element summaryElement = (Element)summaryNode;
			
			System.out.println("[예보요약] ---------------------------------------------------");
			System.out.printf("%s%n%n", summaryElement.getTextContent().replaceAll("<br>", "\n"));
			
			
			// 3-3. 주차별 전망 추출하기
			System.out.println("\n[주자별 전망]------------------------------------------------");
			NodeList weekNodeList = root.getElementsByTagName("week");
			for (int i = 0; i < weekNodeList.getLength(); i++)
			{
				Node weekNode = weekNodeList.item(i);
				Element weekElement = (Element)weekNode;
				
				System.out.printf("\n - 기간 : %s%n"
						, XMLDOM.getText(weekElement, "week" + (i+1) + "_period"));
				System.out.printf("\n - 내용 : %s%n"
						, XMLDOM.getText(weekElement, "week" + (i+1) + "_weather_review").replaceAll("<br>", "\n"));
				System.out.println("------------------------------------------------------------");
			}
			
			
			// 3-4. 지역별 예보 추출하기
			System.out.println("[지역별 날씨] ------------------------------------------------");
			NodeList local_taNodeList = root.getElementsByTagName("local_ta");
			
			// 확인
			//System.out.println(local_taNodeList.getLength());
			//--==>> 13
			
			for (int i = 0; i < local_taNodeList.getLength(); i++)
			{
				Node local_taNode = local_taNodeList.item(i);
				Element local_taElement = (Element)local_taNode;
				
				System.out.printf("지역 : %s%n", XMLDOM.getText(local_taElement, "local_ta_name").replaceAll(" ", " ").replaceAll("\n", ""));
				System.out.println("------------------------------------------------------------");
				NodeList week_local_taNodeList = local_taElement.getElementsByTagName("week_local_ta");
				
				for (int j = 0; j < week_local_taNodeList.getLength(); j++)
				{
					Node week_local_taNode = week_local_taNodeList.item(j);
					Element week_local_taElement = (Element)week_local_taNode;
					
					System.out.printf("\n- %d 주차 평년 : %s", (j+1)
							, XMLDOM.getText(week_local_taElement, "week" + (j+1) + "_local_ta_normalYear")
								.replaceAll(" ", "").replaceAll("\n", ""));
					System.out.printf("\n- %d 주차 평년비슷범위 : %s", (j+1)
							, XMLDOM.getText(week_local_taElement, "week" + (j+1) + "_local_ta_similarRange")
								.replaceAll(" ", "").replaceAll("\n", ""));
					System.out.printf("\n- %d 주차 예보 확률(낮음) : %s", (j+1)
							, XMLDOM.getText(week_local_taElement, "week" + (j+1) + "_local_ta_minVal")
								.replaceAll(" ", "").replaceAll("\n", ""));
					System.out.printf("\n- %d 주차 예보 확률(비슷) : %s", (j+1)
							, XMLDOM.getText(week_local_taElement, "week" + (j+1) + "_local_ta_similarVal")
								.replaceAll(" ", "").replaceAll("\n", ""));
					System.out.printf("\n- %d 주차 예보 확률(높음) : %s", (j+1)
							, XMLDOM.getText(week_local_taElement, "week" + (j+1) + "_local_ta_maxVal")
								.replaceAll(" ", "").replaceAll("\n", ""));
					
				}
			}
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
