package com.service.spring.test;
//JUnit 사용한 단위테스트..
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.service.spring.domain.MyProduct;


public class MyBatisFrameworkJUnitTest1 {	
	@Test
	public void unit() throws Exception{
		Reader r=Resources.getResourceAsReader("config/SqlMapConfig.xml");
		
		//1. SqlSessionFactory -- SqlSessionFactoryBean
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
		
		//2. SqlSession -- SqlSessionTemplate
		SqlSession ssession=factory.openSession();
		
		//3. 쿼리문 실행
		System.out.println("========1. addProduct ==============");
		MyProduct vo =new MyProduct("파워펑펑 세탁기", "대우", 600000);
		// id값은 시퀀스이므로 추가를 못했다.
		
		 ssession.insert("ns.sql.MyProductMapper.addProduct", vo); 
		 ssession.commit();
		 
		
		System.out.println("\n========2. findProducts ==============");
		List<MyProduct> list = ssession.selectList("ns.sql.MyProductMapper.findProducts");
		for(MyProduct prduct : list)
			System.out.println(prduct);
		
		
		System.out.println("\n========3. findProductByName ==============");
		List<MyProduct> list2 = ssession.selectList("ns.sql.MyProductMapper.findProductByName","세탁기");
		for(MyProduct prduct : list2)
			System.out.println(prduct);
		
		System.out.println("\n========4. findProductByMaker ==============");
		List<MyProduct> list3 = ssession.selectList("ns.sql.MyProductMapper.findProductByMaker","대우");
		for(MyProduct prduct : list3)
			System.out.println(prduct);
		
	}
}











