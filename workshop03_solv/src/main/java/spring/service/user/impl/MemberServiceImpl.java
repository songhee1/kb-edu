package spring.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.domain.MemberVO;
import spring.service.user.MemberDAO;
import spring.service.user.MemberService;
@Service("memberServiceImpl")
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public List<MemberVO> showAllMember() {
		return memberDAO.showAllMember();
	}

	@Override
	public MemberVO getMember(String id) {
		return memberDAO.getMember(id);
	}

	@Override
	public String idExist(String id) {
		return memberDAO.idExist(id);
	}

}
