package com.service.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.service.spring.domain.MyProduct;

@Repository
public class MyProductDAOImpl implements MyProductDAO{

	@Autowired
	private SqlSession sqlSession;
	public static final String NS = "ns.sql.MyProductMapper.";
	
	@Override
	public int addProduct(MyProduct vo) throws Exception {
		int result = sqlSession.insert(NS+"addProduct", vo);
		return result;
	}

	@Override
	public List<MyProduct> findProducts(String str) throws Exception {
		return sqlSession.selectList(NS+"findProducts", str);
	}

	

//	@Override
//	public List<MyProduct> findProductByName(MyProduct vo) throws Exception {
//		return sqlSession.selectList(NS+"findProduct", vo);
//	}
//
//	@Override
//	public List<MyProduct> findProductByMaker(String maker) throws Exception {
//		return sqlSession.selectOne(NS+"findProduct", maker);
//	}
//
//	@Override
//	public List<MyProduct> findProducts() throws Exception {
//		return sqlSession.selectList(NS+"findProduct");
//	}
	
	

}
