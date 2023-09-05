package com.edu.test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.edu.dao.EduDAO;
import com.edu.dao.impl.EduDAOImpl;
import com.edu.vo.Lecture;
import com.edu.vo.Student;
import com.edu.vo.User;

public class EduTest {

	public static void main(String[] args) throws Exception {
		EduDAO service = new EduDAOImpl("127.0.0.1"); 
		
		System.out.println();
		
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());

		System.out.println("oneEdu 시스템에 접속하였습니다. 접속시간 : "+ formatter.format(date));
		
		
//		System.out.println("================= addLecture 강의 추가 =================");
//		Lecture lec = new Lecture(0, "미분", "수학", 0, "1000000");
//		Lecture lec1 = new Lecture(1, "확통", "수학", 0, "800000");
//		Lecture lec2 = new Lecture(2, "기하와 벡터", "수학", 0, "53000");
//		
//		Lecture lec3 = new Lecture(0, "화법과 작문", "국어", 0, "124000");
//		Lecture lec4 = new Lecture(1, "문법", "국어", 0, "90000");
//		service.addLecture(lec);
//		service.addLecture(lec1);
//		service.addLecture(lec2);
//		service.addLecture(lec3);
//		service.addLecture(lec4);
//		System.out.println();
		
		
		
		
//		
//		
//		System.out.println("================= updateLecture 강의 수정 =================");
//		service.updateLecture(new Lecture(1, "확통", "수학", 0, "1000000"));
//		service.updateLecture(new Lecture(2, "기화와 벡터", "수학", 5, "900000"));
//		service.updateLecture(new Lecture(3, "미분", "수학", 2, "568000"));
//		service.updateLecture(new Lecture(4, "물리2", "과탐", 0, "180000"));
//		System.out.println();
		
		
		
		
		/*
		System.out.println("================= getAllLectures 강의 목록 조회 =================");
		
		System.out.println("온라인 서비스 oneEdu 시스템에 등록된 수강자들을 조회합니다.");
		ArrayList<Student> list = service.getAllStudents();
		for(Student s : list) {
			System.out.println(s);
		}
		System.out.println();
		System.out.println();
		
		
		System.out.print("id 8번 수강자의 수강목록을 조회합니다. || ");
		
		ArrayList<Lecture> listL = service.getAllMyLecture(8);
		System.out.println("목록의 아이템 갯수는 "+ listL.size());
		for(Lecture lec : listL) {
			System.out.println(lec);
		}
		System.out.println(service.getStudentLectures(8)+"원의 강의 목록을 갖고 계십니다.");
		System.out.println();
		
		
		System.out.println("id 2번 수강자의 수강목록을 조회합니다.");
		ArrayList<Lecture> listL2 = service.getAllMyLecture(2);
		System.out.println("목록의 아이템 갯수는 "+ listL2.size());
		for(Lecture lec : listL2) {
			System.out.println(lec);
		}
		System.out.println(service.getStudentLectures(2)+"원의 강의 목록을 갖고 계십니다.");
		System.out.println();
		
		
		System.out.println("id 4번 수강자의 수강목록을 조회합니다.");
		ArrayList<Lecture> listL3 = service.getAllMyLecture(4);
		System.out.println("목록의 아이템 갯수는 "+ listL3.size());
		for(Lecture lec : listL3) {
			System.out.println(lec);
		}
		System.out.println(service.getStudentLectures(4)+"원의 강의 목록을 갖고 계십니다.");
		System.out.println();
		
		
		System.out.println("id 6번 수강자의 수강목록을 조회합니다.");
		ArrayList<Lecture> listL4 = service.getAllMyLecture(6);
		System.out.println("목록의 아이템 갯수는 "+ listL4.size());
		for(Lecture lec : listL4) {
			System.out.println(lec);
		}
		System.out.println(service.getStudentLectures(6)+"원의 강의 목록을 갖고 계십니다.");
		System.out.println();
		
		
		System.out.println("id 7번 수강자의 수강목록을 조회합니다.");
		ArrayList<Lecture> listL5 = service.getAllMyLecture(7);
		System.out.println("목록의 아이템 갯수는 "+ listL5.size());
		for(Lecture lec : listL5) {
			System.out.println(lec);
		}
		System.out.println(service.getStudentLectures(7)+"원의 강의 목록을 갖고 계십니다.");
		System.out.println();
		*/
		
		
		
		
		
		System.out.println("================= applyLecture 수강 신청 =================");
		int stdId;
		Lecture lec = new Lecture(6, "미분", "수학", 0, "1000000");
		Lecture lec1 = new Lecture(7, "사회", "사탐", 0, "1000000");
		Lecture lec2 = new Lecture(8, "물리1", "과탐", 0, "19900");
		Lecture lec3 = new Lecture(9, "미분", "수학", 0, "56600");
		
		stdId = 2;
		service.applyLecture(stdId, lec);
		System.out.println(stdId + "번 수강생의 강의 수강신청 완료되었습니다.");
		
		service.applyLecture(stdId, lec1);
		System.out.println(stdId + "번 수강생의 강의 수강신청 완료되었습니다.");
		service.applyLecture(stdId, lec2);
		
		System.out.println(stdId + "번 수강생의 강의 수강신청 완료되었습니다.");
		service.applyLecture(stdId, lec3);
		
		System.out.println(stdId + "번 수강생의 강의 수강신청 완료되었습니다.");
		System.out.println();
		
		
		
		stdId = 6;
		service.applyLecture(stdId, lec);
		System.out.println(stdId + "번 수강생의 강의 수강신청 완료되었습니다.");
		
		service.applyLecture(stdId, lec1);
		System.out.println(stdId + "번 수강생의 강의 수강신청 완료되었습니다.");
		
		service.applyLecture(stdId, lec2);
		System.out.println(stdId + "번 수강생의 강의 수강신청 완료되었습니다.");
		
		service.applyLecture(stdId, lec3);
		System.out.println(stdId + "번 수강생의 강의 수강신청 완료되었습니다.");
		System.out.println();
		
		
//		System.out.println("================= calcelLecture 수강 철회 =================");
//		Lecture lec = new Lecture(7, "미분", "수학", 0, "1000000");
//		service.cancleLecture(1, lec);
		
	}

}