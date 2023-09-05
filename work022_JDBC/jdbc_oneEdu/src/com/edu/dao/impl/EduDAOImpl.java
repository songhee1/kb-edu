package com.edu.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.edu.dao.EduDAO;
import com.edu.exception.IdNotFoundException;
import com.edu.vo.Lecture;
import com.edu.vo.Student;
import com.edu.vo.Teacher;
import com.edu.vo.User;

import config.ServerInfo;

public class EduDAOImpl implements EduDAO{
	
	private static EduDAOImpl dao = new EduDAOImpl();
	
	
	private EduDAOImpl() {
		System.out.println("EduDAOImpl Creating... Using Singleton");
	}
	

	public EduDAOImpl(String serverIp) throws ClassNotFoundException{
		Class.forName(ServerInfo.DRIVER_NAME);
		System.out.println("오라클 드라이버 로딩 성공...");
	}
	
	
	@Override
	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASS_WORD);
		System.out.println("DB Connect Success...");
		return conn;
	}

	
	private boolean studentIdExist(int id, Connection conn) throws SQLException {
		String query = "SELECT STUDENT_ID FROM STUDENT WHERE STUDENT_ID = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		boolean flag=  rs.next();
		System.out.println("수강자 아이디 조회결과 :  "+ flag);
		return flag;
	}
	

	private boolean teacherIdExist(int id, Connection conn) throws SQLException {
		String query = "SELECT teacher_id FROM teacher WHERE teacher_id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		boolean flag=  rs.next();
		System.out.println("강사 아이디 조회결과 :  "+ flag);
		return flag;
	}

	
	private boolean lectureIdExist(int id, Connection conn) throws SQLException {
		String query = "SELECT lecture_id FROM lecture WHERE lecture_id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		boolean flag=  rs.next();
		System.out.println("강의 아이디 조회결과 :  "+ flag);
		return flag;
	}
	
	private boolean learningIdExist(int lectureId, int studentId, Connection conn) throws SQLException {
		String query = "SELECT DISTINCT student_id FROM learning WHERE student_id = ? AND lecture_id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, studentId);
		ps.setInt(2, lectureId);
		ResultSet rs = ps.executeQuery();
		
		boolean flag=  rs.next();
		System.out.println("교육 테이블 아이디 조회결과 :  "+ flag);
		return flag;
	}
	
	private boolean learningIdExist(int studentId, Connection conn) throws SQLException {
		String query = "SELECT DISTINCT student_id FROM learning WHERE student_id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, studentId);
		ResultSet rs = ps.executeQuery();
		
		boolean flag=  rs.next();
		System.out.println("교육 테이블 아이디 조회결과 :  "+ flag);
		return flag;
	}
	
	
	@Override
	public void closeAll(PreparedStatement ps, Connection conn) throws Exception {
		if(ps != null) ps.close();
		if(conn != null) conn.close();
	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws Exception {
		closeAll(ps, conn);
		if(rs != null) rs.close();
	}

	@Override
	public ArrayList<User> FindAllTeacher() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> FindTeacherBySubject(String subject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Lecture> FindLectureByTeacher(int teacherId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lecture FindLectureById(int lectureId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Lecture> FindBestLectures() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTeacher(int teacherId) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void addLecture(Lecture lecture) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
			conn = getConnection();
			String query = "INSERT INTO lecture(lecture_id, name, subject, likes, price) VALUES (seq_lecture.nextVal, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, lecture.getName());
			ps.setString(2, lecture.getSubject());
			ps.setInt(3, lecture.getLikes());
			ps.setString(4, lecture.getPrice());
			
			System.out.println(ps.executeUpdate()+ "개 강의 등록되었습니다.");
			
		}finally {
			closeAll(ps, conn);
		}
		
	}

	
	@Override
	public void updateLecture(Lecture lecture) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = getConnection();
			if(lectureIdExist(lecture.getLecture_id(), conn)) {
				
				String query = "UPDATE lecture SET name = ?, subject = ?, price = ? WHERE lecture_id = ?";
				ps = conn.prepareStatement(query);
				ps.setString(1, lecture.getName());
				ps.setString(2, lecture.getSubject());
				ps.setString(3, lecture.getPrice());
				ps.setInt(4, lecture.getLecture_id());
				
				System.out.println(ps.executeUpdate()+ "개 강의가 수정되었습니다.");
				
			}else {
				throw new IdNotFoundException("존재하는 lecture id가 없습니다.");
			}
			
		}finally {
			closeAll(ps, conn);
		}
		
	}
	
	
	@Override
	public ArrayList<Student> getAllStudents() throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<Student> list = new ArrayList<Student>();
		
		try {
			conn = getConnection();
			
			String query = "SELECT student_id, name, address, phone, email" +
					" FROM student";
			ps = conn.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Student(rs.getInt("student_id"), 
						rs.getString("name"), 
						rs.getString("address"), 
						rs.getString("phone"),
						rs.getString("email")
						));
			}
			
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return list;
	}
	
	@Override
	public String getStudentLectures(int studentId) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sumPrice = "";
		
		try {
			conn = getConnection();
			
			
			if(studentIdExist(studentId, conn)){
				if(learningIdExist(studentId, conn)) {
					conn = getConnection();
					String query = "SELECT TO_CHAR(SUM(price), 'FM999,999,999,999') AS price, id"
							+ " FROM" 
							+ " (SELECT lecture.name AS name, lecture.subject AS subject, lecture.likes AS likes, lecture.price AS price, learning.student_id AS id"
							+ " FROM learning, lecture" 
							+ " WHERE learning.lecture_id = lecture.lecture_id AND learning.student_id = ?)" 
							+ " GROUP BY id";
					ps = conn.prepareStatement(query);
					ps.setInt(1, studentId);
					
					rs = ps.executeQuery();
					
					if(rs.next()) {
						sumPrice = rs.getString("price");
					}
				}
			}else{
				throw new IdNotFoundException("존재하는 student id가 없습니다.");
			}
			
			
		}finally {
			closeAll(rs, ps, conn);
		}
		
		if(sumPrice.equals("")) {
			sumPrice = "0";
		}
		
		return sumPrice;
	}
	
	@Override
	public ArrayList<Lecture> getAllMyLecture(int studentId) throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<Lecture> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			
			
			if(studentIdExist(studentId, conn)){
				if(learningIdExist(studentId, conn)) {
					conn = getConnection();
					String query = "SELECT lecture.lecture_id AS id, lecture.name AS name, lecture.subject AS subject, lecture.likes AS likes, lecture.price AS price" +
							" FROM learning, lecture" +
							" WHERE learning.lecture_id = lecture.lecture_id AND learning.student_id = ?";
					ps = conn.prepareStatement(query);
					ps.setInt(1, studentId);
					
					rs = ps.executeQuery();
					
					while(rs.next()) {
						list.add(new Lecture(
								rs.getInt("id"),
								rs.getString("name"), 
								rs.getString("subject"), 
								rs.getInt("likes"), 
								rs.getString("price")));
					}
				}
			}else{
				throw new IdNotFoundException("존재하는 student id가 없습니다.");
			}
			
			
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return list;
	}

	@Override
	public void applyLecture(int studentId, Lecture lecture) throws Exception  {
		Connection conn = null;
		PreparedStatement ps = null;
		System.out.println("학생아이디 "+studentId);
		try {
			conn = getConnection();
			if(studentIdExist(studentId, conn)) {
				if(!learningIdExist(lecture.getLecture_id(), studentId, conn)) {
					conn = getConnection();
					String query = "INSERT INTO learning(student_id, lecture_id, flag) VALUES(?, ?, ?)";
					ps = conn.prepareStatement(query);
					ps.setInt(1, studentId);
					ps.setInt(2, lecture.getLecture_id());
					ps.setInt(3, 0);
					
					System.out.println(ps.executeUpdate()+"개 강의가 지원되었습니다.");
				}else {
					System.out.println("이미 수강신청한 이력이 존재합니다.");
				}
			}else {
				throw new IdNotFoundException("존재하는 student id가 없습니다.");
			}
			
		}finally {
			closeAll(ps, conn);
		}
		
		
	}

	@Override
	public void cancleLecture(int studentId, Lecture lecture) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = getConnection();
			if(learningIdExist(lecture.getLecture_id(),studentId, conn)) {
				conn = getConnection();
				String query = "DELETE learning WHERE student_id = ? AND lecture_id = ?";
				ps = conn.prepareStatement(query);
				ps.setInt(1, studentId);
				ps.setInt(2, lecture.getLecture_id());
				
				System.out.println(ps.executeUpdate()+"개 강의가 취소되었습니다.");
				getAllMyLecture(studentId);
				
			}else {
				throw new IdNotFoundException("존재하는 student id가 없습니다.");
			}
			
		}finally {
			closeAll(ps, conn);
		}
		
	}

	@Override
	public boolean likePossible(int studentId, int lectureId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void likeLecture(int lectureId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean discountPossible(int studentId, int teacherId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}


}
