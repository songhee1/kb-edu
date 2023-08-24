package mybatis.services.user.impl;

import java.util.List;

import mybatis.services.domain.User;
import mybatis.services.user.UserDAO;
import mybatis.services.user.UserService;

public class MyBatisUserServiceImpl11 implements UserService {

	private UserDAO userDAO;
	

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
		System.out.println("::"+getClass().getName()+".setUserDAO() calling");
	}

	@Override
	public void addUser(User user) throws Exception {
		userDAO.addUser(user);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) throws Exception {
		// TODO Auto-generated method stub
		userDAO.updateUser(user);
	}

	@Override
	public User getUser(String userId) throws Exception {
		return userDAO.getUser(userId);
	}

	@Override
	public List<User> getUserList(User user) throws Exception {
		return userDAO.getUserList(user);
	}


	

}
