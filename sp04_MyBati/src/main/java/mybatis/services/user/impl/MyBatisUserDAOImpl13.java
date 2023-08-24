package mybatis.services.user.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import mybatis.services.domain.User;
import mybatis.services.user.UserDAO;

/*
 * Annotation 기반으로 작성
 * xml 빈 설정 문서에서 bean 태그를 생략할 수 있다.
 * 
 * @Component가 <bean></bean>을 대신한다. 
 * 
 * @Autowired 하면 setter로 주입하지 않아도 된다.
*/
@Repository
public class MyBatisUserDAOImpl13 implements UserDAO{

	@Autowired
	private SqlSession sqlSession;
	public static final String MAPPER_NAME = "UserMapper10.";
	
	@Override
	public int addUser(User user) throws Exception {
		int result = sqlSession.insert(MAPPER_NAME+"addUser", user);
		//sqlSession.commit();
		return result;
	}

	@Override
	public int updateUser(User user) throws Exception {
		int result = sqlSession.update(MAPPER_NAME+"updateUser", user);
		//sqlSession.commit();
		return result;
	}

	@Override
	public int removeUser(String userId) throws Exception {
		int result = sqlSession.update(MAPPER_NAME+"removeUser", userId);
		//sqlSession.commit();
		return result;
	}

	@Override
	public User getUser(String userId) throws Exception {
		return sqlSession.selectOne(MAPPER_NAME+"getUser", userId);
	}

	@Override
	public List<User> getUserList(User user) throws Exception {
		return sqlSession.selectList(MAPPER_NAME+"getUserList", user);
	}

}
