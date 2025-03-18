/*=======================================================
	XmlDomTest08.java
	- 콘솔 기반 자바 프로그램
	- XMLDOM 활용 → 원격(remote) XML 읽어내기
	  → https://news-ex.jtbc.co.kr/v1/get/rss/issue
=======================================================*/

/*
title> JTBC News
link> http://news-ex.jtbc.co.kr/v1/get/rss/issue
description> 이슈 TOP 10 RSS
copyright> Copyright(C) JTBC All rights reserved.

주요기사 ------------------------------------------
title> 계엄 직전 우려가 현실로…민감국가 지정된 한국, 70년 동맹 '역행'
link> https://news.jtbc.co.kr/article/NB12239149
description> [앵커] 미국이 민감국가 리스트에 한국을 포함시킨 건 70년 한&middot;미 동맹을 역행하는 조치여서 더 심각합니다. &quot;대한민국이 지난 70년간 쌓아 올린 모든 성취를 한꺼번에 무너뜨릴 수 있다&quo ]]>
pubDate> 2025.03.18

<title>"대통령이라 안 부른다"...성공회신부도 "파면 촉구"
<link>https://news.jtbc.co.kr/article/NB12239161
<description>[대한성공회 서울주교좌성당/어제(17일) 오전] 1987년 '6.10 민주항쟁' 시작된 장소, 성공회 서울주교좌성당. 대통령 탄핵 선고 임박한 가운데 '꽃샘추위' 찬 바람 맞으며 전국서 모인 성직자들의 절박한 외 ]]>
<pubDate>2025.03.18

*/

package com.test;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;



public class XmlDomTest08
{
	public static void main(String[] args)
	{
		try
		{
			// ※ DOM(Document Object Model)
			// 	  - XML 이나 HTML Document(문서)를 응용프로그램에서 사용하기 위한 API 규격
			// 	  - DOM 은 Document(문서)의 각 부분들을 객체(Object)로 표현한 API
			
			// ※ DOM(Document Object Model) 파서(Parser)
			//	  - XML 문서를 읽고, 해석한 후,
			//	    해석한 결과를 메모리에 DOM 객체 트리 구조로 생성시키는 파서(Parser)
			//	  - 원하는 데이터에 접근할 수 있도록 해주어
			//	    검색, 수정, 삭제할 수 있도록 지원
			
			// ※ 주로 사용되는 DOM(Document Object Model) 인터페이스
			//	  - Node
			//	    : 모든 객체의 부모 인터페이스로서 공통적으로 기능하는 함수를 가진다.
			//    - NodeList (NODESET)
			//	    : 노드들을 리스트로 받아 처리하기 쉽도록 한 것(일괄 처리)
			// 	  - Document
			//	    : DOM(Document Object Model) 트리 구조의 최상위 노드로
			//		  XML 문서 자체를 의미한다.
			//	  - Element
			//	    : XML 의 엘리먼트에 해당하는 객체 유형
			//	  - Attr
			//	    : XML 의 Attribute 에 해당하는 객체 유형
			// 	  - CharacterData
			//	    : XML 의 데이터에 해당하는 객체 유형
			//	  - Text
			//	    : 문자 데이터(내용)에 해당하는 객체 유형
			
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlObj = null;
			
			String str = "https://news-ex.jtbc.co.kr/v1/get/rss/issue";
			URL url = new URL(str);
			InputSource is = new InputSource(url.openStream());
			xmlObj = builder.parse(is);
			
			// 루트 엘리먼트 접근
			Element root = xmlObj.getDocumentElement();
			
			Node channelNode = root.getElementsByTagName("channel").item(0);
			Element channelElement = (Element)channelNode;
			
			System.out.printf("title> %s%nlink> %s%ndescription> %s%ncopyright> %s%n"
							, XMLDOM.getText(channelElement, "title")
							, XMLDOM.getText(channelElement, "link")
							, XMLDOM.getText(channelElement, "description")
							, XMLDOM.getText(channelElement, "copyright"));
			System.out.println();
			System.out.println("주요 기사 -----------------------------------------------");
			
			NodeList itemNodeList = root.getElementsByTagName("item");
			
			//System.out.println(itemNodeList.getLength());
			
			for (int i = 0; i < itemNodeList.getLength(); i++)
			{
				Node itemNode = itemNodeList.item(i);
				Element itemElement = (Element)itemNode;
				
				System.out.println();
				System.out.println("---------------------------------------------------------------");
				System.out.printf("title> %s%nlink> %s%ndescription> %s%npubDate> %s%n"
						       		, XMLDOM.getText(itemElement, "title")
						       		, XMLDOM.getText(itemElement, "link")
						       		, XMLDOM.getText(itemElement, "description")
						       			.replaceAll("\n", "")
						       			.replaceAll("&#39;", "'")
						       			.replaceAll("&quot;", "\"")
						       			.replaceAll("&middot;", "·")
						       		, XMLDOM.getText(itemElement, "pubDate"));
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
