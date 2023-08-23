package com.service.mybatis.test;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.service.mybatis.vo.MySawon;

public class MySawonAppTest01 {

	public static void main(String[] args) throws Exception {
		// 1. 폼으로 가입 사원의 정보를 받는다.
		MySawon pvo = new MySawon();
		/*
		pvo.setId("kblife");
		pvo.setPwd("1234");
		pvo.setName("김국민");
		pvo.setAge(34);
		*/
		/*
		pvo.setId("hahash");
		pvo.setPwd("9006");
		pvo.setName("김연아");
		pvo.setAge(33);
		*/
		pvo.setId("zero");
		pvo.setPwd("1111");
		pvo.setName("공유");
		pvo.setAge(42);
		
		//2. SqlMapConfig.xml 파일을 읽어들인다.
		Reader r = Resources.getResourceAsReader("config/SqlMapConfig.xml");
		
		//3. sqlSessionFactory 생성
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
		
		//4. sqlsession 생성
		SqlSession session = factory.openSession();
		System.out.println("sql 세션이 드디어 만들어졌습니다");
		
		/*
		 * SqlSession 쿼리문을 수행하는 모든 기능을 다 가지고 있다.
		 * int insert(), 
		 * int delete(), 
		 * int update()
		 * 
		 * List selectList()
		 * Object selectOne() : 하나만 가지고 온다
		*/
		
		//sawonAdd 쿼리문 단위 테스트
		session.insert("SawonMapper.sawonAdd", pvo);//디비 입력
		System.out.println(pvo.getName()+"님이 회원 등록 성공 ^^");
		
		//반드시 커밋
		session.commit();
		session.close();
	}

}
