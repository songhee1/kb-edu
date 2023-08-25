package spring.service.user;

import java.util.List;

import spring.service.domain.MemberVO;
// 데이터 가공과 관련된 layer
// 
public interface MemberService {
	List<MemberVO>showAllMember();
	MemberVO getMember(String id);
	
	String idExist(String id);
}
