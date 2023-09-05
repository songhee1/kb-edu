package com.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.edu.vo.Lecture;
import com.edu.vo.Student;
import com.edu.vo.Teacher;
import com.edu.vo.User;

public interface EduDAO {

	//공통 커넥션 연결부
	public Connection getConnection() throws SQLException;
	
	// 존재여부 확인
	//private boolean studentIdExist(int id);
	//private boolean teacherIdExist(int id);
	//private boolean lectureIdExist(int id);

	//close 메서드
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws Exception;
	public void closeAll(PreparedStatement ps, Connection conn) throws Exception;


	//**공통 기능** (가빈)
	public ArrayList<User> findAllTeacher() throws Exception;  //선생님 전체 목록 조회
	public ArrayList<User> findTeacherBySubject(String subject) throws Exception; //과목별 선생님 전체 목록 조회
	public ArrayList<Lecture> findLectureByTeacher(int teacherId) throws Exception; //선생님 id로 강의 목록 조회
	public Lecture findLectureById(int lectureId) throws Exception; // 강의명 id로 강의 조회
	public ArrayList<Lecture> findBestLectures() throws Exception; // 인기 강의 목록 조회
	//공통 기능 전부 : throw IdNotFoundException -> IdExist 메서드 호출 후 존재하지 않으면 예외 


	//**관리자** (영욱)
	public void addTeacher(Teacher teacher) throws Exception; 
	// 선생님 등록 -> 이메일이 동일하면 throw AlreadyExistException 
	
	public void updateTeacher(Teacher teacher) throws Exception; // 선생님 변경
	public void deleteTeacher(int teacherId) throws Exception; // 선생님 삭제
	//위 두개 메서드 전부 : throw IdNotFoundException -> IdExist 메서드 호출 후 존재하지 않으면 예외 


	//**강의자** (송희)
	public void addLecture(Lecture lecture) throws Exception; 
	
	public void updateLecture(Lecture lecture) throws Exception;
	//throw IdNotFoundException
	


	//**수강자**
	//(송희)
	public ArrayList<Student> getAllStudents() throws Exception;
	public String getStudentLectures(int studentId) throws Exception;
	public ArrayList<Lecture> getAllMyLecture(int studentId) throws Exception; // id로 수강자 강의 목록조회
	// throw IdNotFoundException

	public void applyLecture(int studentId, Lecture lecture) throws Exception; 
	//수강자 강의 목록에 강의 추가 -> getAllMyLecture 메서드 호출해서 강의 목록 출력
	// throw AlreadyExistException 

	public void cancleLecture(int studentId, Lecture lecture) throws Exception; // 수강자 강의 목록에서 강의 삭제
	// throw IdNotFoundException

	
	
	//(영욱)
	public boolean likePossible(int studentId, int lectureId) throws Exception; // 수강자가 강의 좋아요 가능 여부 확인
	// throw IdNotFoundException( 수강자 id랑 강의 id 둘다 확인)

	public void likeLecture(int studentId, int lectureId) throws Exception; // 강의 id로 좋아요 표시
	// likePossible 호출해서 true 이면 likeLecture 호출
	// likePossible 호출해서 false이면 메서드 나가기

	//public boolean discountPossible(int studentId, int teacherId);
	// 선생님 강의를 수강자가 3개 이상 수강 중이면 강의 할인
	// throw IdNotFoundException
	
	public int discountLecture(String subject, int discount) throws Exception;
	//특정 subject의 가격 총 합에 할인율을 적용하여 패키지 가격을 알려준다.
	//SELECT SUM(price) FROM lecture WHERE subject='수학';
	
}
