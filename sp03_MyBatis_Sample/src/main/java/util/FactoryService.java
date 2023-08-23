package util;
 //SqlSession만 받아오는 기능을 가지고 있다.

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class FactoryService {
	private static SqlSessionFactory factory = null;
	static {
		try {
			Reader r = Resources.getResourceAsReader("config/SqlMapConfig.xml");
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
		}catch(Exception e) {
			
		}
	}
	
	public static SqlSessionFactory getFactory() {
		return factory;
	}
}
