/*===============================================
	XmlDomTest02.java
	- 콘솔 기반 자바 프로그램
	- XML DOM 활용 → 로컬(local) XML 읽어내기
	  (memberList.xml)
================================================*/

package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest02
{
	public static void main(String[] args)
	{
		/*
		김상기 010-1213-4546
		김민성 010-5678-6789
		*/
		
		// 1. XML 파일(memberList.xml)을 메모리에 로드
		// 	  → 이 과정을 통해 XML DOM 생성
		// 2. 루트 엘리먼트 접근
		// 3. 루트 엘리먼트 특정 하위 엘리먼트 접근
		//    → 이름, 위치 등을 기준으로 접근
		// 		 (사실상 문법적으로 다양한 접근 방법 지원)
		// 4. 텍스트 노드(속성 노드) 접근
		//	  → 이 과정을 통해 원하는 데이터 얻어내기
		// 5. 결과 처리
		// 	  → 출력
		
		try
		{
			// 1.
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlObj = null;
			
			String url = "memberList.xml";
			xmlObj = builder.parse(url);
			
			// 2.
			Element root = xmlObj.getDocumentElement();
			//					  get + documentElement
			//-- 문서의 대표 엘리먼트(루트 엘리먼트)를 얻어내는 과정
			
			// 3.
			NodeList memberInfoNodeList = root.getElementsByTagName("memberInfo");
			//-- 이 때, 『getElementsByTagName()』 메소드는
			// 	 태그의 이름을 가지고 자식이나 자손 노드에 접근을 수행하는 메소드
			
			// ※ check~!!!
			// 	  XML 의 모든 노드는 루트 엘리먼트 하위에 존재~!!!
			
			// 확인
			//System.out.println(memberInfoNodeList.getLength());
			//--==>> 2
			
			for (int i = 0; i < memberInfoNodeList.getLength(); i++)	//-- 0 ~ 1
			{
				Node memberInfoNode = memberInfoNodeList.item(i);
				//-- 『getElementsByTagName()』 메소드가 이름을 통해 대상을 획득했다면
				// 	 『item()』 메소드는 위치(인덱스)를 통해 대상을 획득하게 된다.
				
				// 캐스팅
				Element memberInfoElement = (Element)memberInfoNode;
				//-- 엘리먼트가 노드의 하위 개념이기 때문에 가능한 구문
				
				System.out.printf("%s %s%n", getText(memberInfoElement, "name")
										   , getText(memberInfoElement, "telephone"));
				
				// 커리큘럼에 대한 처리 추가------------------------------------------------
				
				// memberInfoElement 로 부터 curriculumn NodeList 얻어오기
				NodeList curriculumnNodeList = memberInfoElement.getElementsByTagName("curriculumn");
				
				// check~!!!
				
				if (curriculumnNodeList.getLength() > 0)
				{
					
					Node curriculumnNode = curriculumnNodeList.item(0);
					Element curriculumnElement = (Element)curriculumnNode;
					
					// 방법 1.
					/*
					NodeList subNodeList = curriculumnElement.getElementsByTagName("sub");
					for (int m = 0; m < subNodeList.getLength(); m++)
					{
						Node subNode = subNodeList.item(m);
						Element subElement = (Element)subNode;
						System.out.printf("- %s%n", subElement.getTextContent());
					}
					
					// 개행
					System.out.println();
					*/
					
					// 방법 2.
					/*
					--------------------------------------------------
					Node Type		Named Constant
					--------------------------------------------------
					 1				ELEMENT_NODE
					 2				ATTRUBUTE_NODE
					 3				TEXT_NODE
					 4 				CDATA_SECTION_NODE
					 5				ENTITY_REFERENCE_NODE
					 6 				ENTITY_NODE
					 7				PROCESSING_INSTRUCTION_NODE
					 8 				COMMENT_NODE
					 9				DOCUMENT_NODE
					10				DOCUMENT_TYPE_NODE
					11				DOCUMENT_FRAGMENT_NODE
					12				NOTATION_NODE
					--------------------------------------------------
					*/
					
					NodeList subNodeList = curriculumnElement.getChildNodes();		// check~!!!
					for (int m = 0; m < subNodeList.getLength(); m++)
					{
						Node subNode = subNodeList.item(m);
						if (subNode.getNodeType()==1)	//-- ELEMENT_NODE			// check~!!!
						{
							Element subElement = (Element)subNode;
							System.out.printf("- %s%n", subElement.getTextContent());
						}
					}
					System.out.println();
				}
				
				
				
				// ----------------------------------------------- 커리큘럼에 대한 처리 추가
				
			}
			
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}

	}// end main()
	
	private static String getText(Element parent, String tagName)
	{
		String result = "";
		
		Node node = parent.getElementsByTagName(tagName).item(0);
		Element element = (Element)node;
		
		result = element.getChildNodes().item(0).getNodeValue();
		
		return result;
	}
}
