package spring.aop.solv;

//Target 클래스
public class MemberService {
	
	public void register(String name) {
		
		System.out.println("register....logic...회원등록 성공"); //일종의 Core Concern
	}
	
	public void findMember(String id) {
		System.out.println("register....logic...회원검색 성공");
	}
}
