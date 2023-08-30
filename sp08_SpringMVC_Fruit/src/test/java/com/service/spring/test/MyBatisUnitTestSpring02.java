package com.service.spring.test;

import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.service.spring.domain.Item;
import com.service.spring.service.ItemCatalog;


public class MyBatisUnitTestSpring02 {
	@Test
	public void DITest() throws Exception {
		//주문서를 바탕으로 빈을 생성하는 DI컨테이너를 생성한다...PreLoading 방식의 컨테이너.. 클라이언트 요청시 getBean을 하는게 아니라, 주문서 
		//읽자마자 빈을 생성
		ApplicationContext factory = new ClassPathXmlApplicationContext("/bean/itemservice.xml");
		
		ItemCatalog service = (ItemCatalog)factory.getBean("itemCatalogImpl");
		System.out.println("======getItemList ========");
		
		
		List<com.service.spring.domain.Item> list = service.getItemList();
		
		for(com.service.spring.domain.Item i : list) {
			System.out.println(i);
		}
		
		System.out.println("=====getItem");
		com.service.spring.domain.Item item = service.getItem(2222);
		System.out.println(item);
	}
}
