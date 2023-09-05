package com.edu.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.edu.dao.EduDAO;
import com.edu.exception.AlreadyExistException;
import com.edu.exception.IdNotFoundException;
import com.edu.vo.Lecture;
import com.edu.vo.Student;
import com.edu.vo.Teacher;
import com.edu.vo.User;

import config.ServerInfo;

public class EduDAOImpl implements EduDAO {

	private static EduDAOImpl dao = new EduDAOImpl();
	
	private EduDAOImpl() {
		System.out.println("EduDAOImpl Creating...Using Singletone");
	}
	public static EduDAOImpl getInstance() {
		return dao;
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
		System.out.println("DB Connect...");
		return conn;
	}
	
	private boolean teacherIdExist(int id, Connection conn) throws SQLException {
		String query = "SELECT teacher_id FROM teacher WHERE teacher_id=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}
	
	private boolean lectureIdExist(int id, Connection conn) throws SQLException {
		String query = "SELECT lecture_id FROM lecture WHERE lecture_id=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}
	
	//가빈 코드
	private boolean lectureSubjectExist(String subject,Connection conn)throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT subject FROM lecture WHERE subject=?";
        ps = conn.prepareStatement(query);
        ps.setString(1, subject);
        rs = ps.executeQuery();
        return rs.next();
    }
	
	//송희 코드
	private boolean studentIdExist(int id, Connection conn) throws SQLException {
        String query = "SELECT STUDENT_ID FROM STUDENT WHERE STUDENT_ID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        boolean flag=  rs.next();
        System.out.println("수강자 아이디 조회결과 :  "+ flag);
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
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws Exception {
		if(rs!=null) rs.close();
		closeAll(ps, conn);
	}

	@Override
	public void closeAll(PreparedStatement ps, Connection conn) throws Exception {
		if(ps!=null) ps.close();
		if(conn!=null) conn.close();
	}

	/////////////////////////// 공통 기능 //////////////////////////////
	@Override
	public ArrayList<User> findAllTeacher() throws Exception{
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    ArrayList<User> list = new ArrayList<User>();
	    try {
	        conn = getConnection();
	        String query ="SELECT teacher_id, name, email,subject FROM teacher";
	        ps = conn.prepareStatement(query);
	        rs = ps.executeQuery();
	        
	        while(rs.next()){
	            list.add(new Teacher(rs.getInt("teacher_id"), 
	                    rs.getString("name"), 
	                    rs.getString("email"),
	                    rs.getString("subject")
	                    ));
	        }
	        
	    }finally {
	        closeAll(rs, ps, conn);
	    }
	    return list;
	}
	
	@Override
	public ArrayList<User> findTeacherBySubject(String subject) throws Exception {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    ArrayList<User> list = new ArrayList<User>();
	    try {
	        conn = getConnection();
	        if(lectureSubjectExist(subject,conn)) {
	            String query ="SELECT teacher_id, name, email,subject FROM teacher WHERE subject=?";
	            ps = conn.prepareStatement(query);
	            ps.setString(1, subject);
	            rs = ps.executeQuery();
	            
	            while(rs.next()){
	                list.add(new Teacher(rs.getInt("teacher_id"), 
	                        rs.getString("name"), 
	                        rs.getString("email"),
	                        rs.getString("subject")
	                        ));
	            }
	        }else {
	            throw new IdNotFoundException("해당 과목을 담당하는 선생님이 없습니다.");
	        }
	        
	    }finally {
	        closeAll(rs, ps, conn);
	    }
	    return list;
	}
	
	@Override
	public ArrayList<Lecture> findLectureByTeacher(int teacherId) throws Exception {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    ArrayList<Lecture> list = new ArrayList<Lecture>();
	    
	    try {
	        conn = getConnection();
	        if(teacherIdExist(teacherId,conn)) {
	            String query ="SELECT lecture_id,name,subject,likes,to_char(price,'9,999,999') FROM lecture WHERE lecture_id in (SELECT lecture_id FROM education where teacher_id=?)";
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, teacherId);
	            rs = ps.executeQuery();
	            
	            while(rs.next()) {
	                list.add(new Lecture(rs.getInt("lecture_id"), 
	                        rs.getString("name"), 
	                        rs.getString("subject"),
	                        rs.getInt("likes"),
	                        rs.getString("to_char(price,'9,999,999')")
	                        ));
	            }
	        }else {
	            throw new IdNotFoundException("등록된 선생님이 아닙니다.");
	        }

	    }finally {
	        closeAll(rs, ps, conn);
	    }
	    return list;
	}
	
	@Override
	public Lecture findLectureById(int lectureId) throws Exception  {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    Lecture lecture = new Lecture();
	    try {
	        conn = getConnection();
	        if(lectureIdExist(lectureId,conn)) {
	            String query ="SELECT lecture_id, name,subject,likes,to_char(price,'9,999,999') FROM lecture WHERE lecture_id=?";
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, lectureId);
	            rs = ps.executeQuery();
	            
	            if(rs.next()){
	                lecture.setId(lectureId);
	                lecture.setName(rs.getString("name"));
	                lecture.setSubject(rs.getString("subject"));
	                lecture.setLikes( rs.getInt("likes"));
	                lecture.setPrice(rs.getString("to_char(price,'9,999,999')"));
	            }
	        }else {
	            throw new IdNotFoundException("존재하지 않는 강의입니다.");
	        }
	        
	    }finally {
	        closeAll(rs, ps, conn);
	    }
	    return lecture;
	}
	
	@Override
	public ArrayList<Lecture> findBestLectures() throws Exception {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    ArrayList<Lecture> list = new ArrayList<Lecture>();
	    
	    try {
	            conn = getConnection();
	            String query ="SELECT lecture_id, name, subject, likes, to_char(price,'9,999,999')"
	                    + "FROM (SELECT  lecture_id, name, subject, likes, price, "
	                    + "ROW_NUMBER() OVER(ORDER BY likes DESC) AS ranking FROM lecture l) "
	                    + "WHERE ranking <= 3";
	            ps = conn.prepareStatement(query);
	            rs = ps.executeQuery();
	            
	            while(rs.next()) {
	                list.add(new Lecture(rs.getInt("lecture_id"), 
	                        rs.getString("name"), 
	                        rs.getString("subject"),
	                        rs.getInt("likes"),
	                        rs.getString("to_char(price,'9,999,999')")
	                        ));
	            }
	    }finally {
	        closeAll(rs, ps, conn);
	    }
	    return list;
	}

	/////////////////////////// 관리자 기능 (영욱) ///////////////////////
	@Override
	public void addTeacher(Teacher teacher) throws Exception {
		Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            if(!teacherIdExist(teacher.getId(), conn)) { //등록하려는 강의자가 아직 없으면
                String query = "INSERT INTO teacher(teacher_id, name, email, subject) VALUES(seq_teacher.nextVal,?,?,?)";
                ps=  conn.prepareStatement(query);
                ps.setString(1, teacher.getName());
                ps.setString(2, teacher.getEmail());
                ps.setString(3, teacher.getSubject());
                System.out.println(ps.executeUpdate()+" 명 INSERT 성공...addTeacher()..");
            }else {
                throw new AlreadyExistException();
            }
        }finally {
            closeAll(ps, conn);
        }
	}

	@Override
	public void updateTeacher(Teacher teacher) throws Exception {
		Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            String query = "UPDATE teacher SET name=?, email=?, subject=? WHERE teacher_id=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, teacher.getName());
            ps.setString(2, teacher.getEmail());
            ps.setString(3, teacher.getSubject());
            ps.setInt(4, teacher.getId());

            int row = ps.executeUpdate();
            if(row==1)System.out.println(row+" 명 UPDATE OK...updateTeacher()...");
            else throw new IdNotFoundException("수정할 강의자가 없습니다");
        }finally {
            closeAll(ps, conn);
        }
	}

	@Override
	public void deleteTeacher(int teacherId) throws Exception {
		Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            if(teacherIdExist(teacherId, conn)) { //해당 강의자 존재하면 삭제
                String query = "DELETE teacher WHERE teacher_id=?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, teacherId);

                System.out.println(ps.executeUpdate()+" 명 DELETE OK...deleteTeacher()..");
            }else {
                throw new IdNotFoundException("삭제할 강의자가 없습니다 ");
            }
        }finally {
            closeAll(ps, conn);
        }
	}
	/////////////////////////// 관리자 기능 (영욱) ///////////////////////

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
            if(lectureIdExist(lecture.getId(), conn)) {
                
                String query = "UPDATE lecture SET name = ?, subject = ?, price = ? WHERE lecture_id = ?";
                ps = conn.prepareStatement(query);
                ps.setString(1, lecture.getName());
                ps.setString(2, lecture.getSubject());
                ps.setString(3, lecture.getPrice());
                ps.setInt(4, lecture.getId());
                
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
                if(!learningIdExist(lecture.getId(), studentId, conn)) {
                    conn = getConnection();
                    String query = "INSERT INTO learning(student_id, lecture_id, flag) VALUES(?, ?, ?)";
                    ps = conn.prepareStatement(query);
                    ps.setInt(1, studentId);
                    ps.setInt(2, lecture.getId());
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
            if(learningIdExist(lecture.getId(),studentId, conn)) {
                conn = getConnection();
                String query = "DELETE learning WHERE student_id = ? AND lecture_id = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, studentId);
                ps.setInt(2, lecture.getId());
                
                System.out.println(ps.executeUpdate()+"개 강의가 취소되었습니다.");
                getAllMyLecture(studentId);
                
            }else {
                throw new IdNotFoundException("존재하는 student id가 없습니다.");
            }
            
        }finally {
            closeAll(ps, conn);
        }
        
    }

	/////////////////////////// 관리자 기능 (영욱) ///////////////////////
	@Override
	public boolean likePossible(int studentId, int lectureId) throws Exception {
		//강의-학생 -> learning
		//만약 learning의 flag 값이 0이면 -> 좋아요 누르기 가능, true 리턴
		//만약 learning의 flag 값이 1이면 -> 좋아요 누르기 불가능, false 리턴
		boolean flag = false;
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = getConnection();
        	String query = "SELECT flag FROM learning WHERE student_id=? and lecture_id=?";
        	ps = conn.prepareStatement(query);
        	ps.setInt(1, studentId);
        	ps.setInt(2, lectureId);
        	rs = ps.executeQuery();
        	if(rs.next()) {
        		if(rs.getInt("flag")==0)
        			flag = true;
        	}
        } finally {
        	closeAll(rs, ps, conn);
        }
		
		return flag;
	}
	// 수강자가 강의 좋아요 가능 여부 확인
	// throw IdNotFoundException( 수강자 id랑 강의 id 둘다 확인)

	private void setFlag(int studentId, int lectureId) throws Exception {
		//learning 테이블의 flag 0->1
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
		    String query = "UPDATE learning SET flag=? WHERE student_id=? and lecture_id=?";
		    ps = conn.prepareStatement(query);
		    ps.setInt(1, 1);
		    ps.setInt(2, studentId);
		    ps.setInt(3, lectureId);

		    int row = ps.executeUpdate();
		    if(row==1)System.out.println("flag 변경 성공");
		    else throw new IdNotFoundException("flag 변경할 수 없습니다");
	   }finally {
		   closeAll(ps, conn);
		   }
	}
	
	//특정 강의의 인기도 가져오기
	public int getLikes(int lectureId) throws Exception {
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int likes = 0;
        try {
        	conn = getConnection();
            if(lectureIdExist(lectureId, conn)) { //강의 존재하면 인기도 가져오기
            	String query = "SELECT likes FROM lecture WHERE lecture_id=?";
            	ps = conn.prepareStatement(query);
            	ps.setInt(1, lectureId);
            	rs = ps.executeQuery();
            	if(rs.next()) {
            		likes = rs.getInt("likes");
            	}
            }else {
                throw new IdNotFoundException("인기도를 가져올 강의가 없습니다 ");
            }
        } finally {
        	closeAll(rs, ps, conn);
        }
		return likes;
	}
	
	@Override
	public void likeLecture(int studentId, int lectureId) throws Exception {
		
		if(!likePossible(studentId, lectureId))
			return; //좋아요 누르기 불가능하면 끝
		
		int nowLikes = getLikes(lectureId);
		//현재 인기도+1 -> db에 업데이트
		Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            String query = "UPDATE lecture SET likes=? WHERE lecture_id=?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, nowLikes+1);
            ps.setInt(2, lectureId);

            int row = ps.executeUpdate();
            if(row==1)System.out.println("인기도 올리기 성공");
            else throw new IdNotFoundException("인기도 올릴 강의가 없습니다");
        }finally {
            closeAll(ps, conn);
        }
        
        //flag 0->1로
        setFlag(studentId, lectureId);
	}

	@Override
	public int discountLecture(String subject, int discount) throws Exception {
		//특정 subject의 가격 총 합에 할인율을 적용하여 패키지 가격을 알려준다.
		//SELECT SUM(price) FROM lecture WHERE subject='수학';
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;
        try {
        	conn = getConnection();
            String query = "SELECT SUM(price) FROM lecture WHERE subject=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, subject);
            rs = ps.executeQuery();
            if(rs.next()) {
            	total = rs.getInt("SUM(price)");
            }
        } finally {
        	closeAll(rs, ps, conn);
        }
		return total/discount;
	}

	
	/////////////////////////// 관리자 기능 (영욱) ///////////////////////
}
