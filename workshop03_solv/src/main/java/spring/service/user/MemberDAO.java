package spring.service.user;

import java.util.List;

import spring.service.domain.MemberVO;

/* 
 * ~mapping.xml에서 쿼리문 아이디값이 해당 인터페이스의 기능의 이름이 된다.
 * 
 * */
public interface MemberDAO {
	int registerMember(MemberVO vo);
	int deleteMember(String id);
	int updateMember(MemberVO vo);
	List<MemberVO>showAllMember();
	MemberVO getMember(String id);
	String idExist(String id);
}
