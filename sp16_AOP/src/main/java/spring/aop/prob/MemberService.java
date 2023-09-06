package spring.aop.prob;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.spi.LoggerFactory;

/* 비즈니스 로직을 호출하고 있는 서비스 클래스
 * 핵심 로직(Core Concern) 사이사이에 
 * 부수적인 로직(Cross Cutting Concern)이 산발적으로 혼재되어져 있다. 
 * 
 * :: 결론적으로 oop 본질을 흐리고 있다.
 * ::
 * 해당 코드에서는 부수적인 관심사에 해당되는 로직을 로그파일 출력으로 정해 작성하겠다.
 * */
public class MemberService {
	
	private Log log = LogFactory.getLog(getClass());
	
	public void register(String name) {
		log.info(name + " :: 님 회원 등록 시도 합니다.");//일종의 Cross Cutting Concern
		System.out.println("register....logic...회원등록 성공"); //일종의 Core Concern
		log.info(name + " :: 님 회원 등록 시도 성공하셨습니다.");//일종의 Cross Cutting Concern
	}
	
	public void findMember(String id) {
		System.out.println("register....logic...회원검색 성공");
	}
}
