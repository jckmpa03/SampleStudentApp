package com.jck.mpa.dao;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jck.mpa.model.Student;

@Repository
public class StudentRepository {

	@Autowired
    JdbcTemplate jdbcTemplate;
	
   /**
    * Retrieves all students from the repository
    * @return List<Student>
    */
	public List<Student> getAllStudents() {
		String query = "select id, name, school_year as schoolYr, campus, "
				+ "entry_date as entryDate,"
				+ "grade_level as gradeLevel,"
				+ "student_id as studentId "
				+ "from student";
		List<Student> students = jdbcTemplate.query(query, new BeanPropertyRowMapper(Student.class));
		return students;
	}
	
	/**
	 * Inserts new student
	 * @param student
	 * @return int
	 */
	public int insertStudent(Student student) {
		String insert = "INSERT INTO student "+
						"(name,school_year,grade_level, "+
						"campus,student_id,entry_date) "+
						"VALUES "+
						"(?,?,?,"+
						"?,?,?"+
						")"; 

		Object[] params = new Object[] { student.getName(), student.getSchoolYr(),student.getGradeLevel(), 
				student.getCampus(), student.getStudentId(), student.getEntryDate()	};
		int[] types = new int[] { 
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, 
				Types.VARCHAR, Types.VARCHAR, Types.DATE};

		int x = jdbcTemplate.update(insert,params,types);
		
		return x;
	}
	
	/**
	 * Updates student
	 * @param student
	 * @return int
	 */
	public int updateStudent(Student student) {

		String update = "UPDATE student " + 
				"SET " + 
				"name = ?, " + 
				"school_year = ?, " + 
				"grade_level = ?, " + 
				"campus = ?, " + 
				"entry_date = ?, " + 
				"student_id = ? " +
				"WHERE id = ?"; 

		Object[] params = new Object[] {
				student.getName(), student.getSchoolYr(), student.getGradeLevel(),
				student.getCampus(), student.getEntryDate(), student.getStudentId(),
				student.getId()};
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, 
				Types.VARCHAR, Types.DATE, Types.VARCHAR, 
				Types.INTEGER };
		
		int x = jdbcTemplate.update(update ,params,types);
		
		return x;
	}
	
	/**
	 * Retrieves student 
	 * @param id
	 * @return Student
	 */
	public Student getStudent(int id) {
		String query = "select id, name, school_year as schoolYr, campus, "
				+ "entry_date as entryDate,"
				+ "grade_level as gradeLevel,"
				+ "student_id as studentId "
				+ "from student where id = ?";
		
		Student student = (Student) jdbcTemplate.queryForObject(query, new Object[] { id } , new BeanPropertyRowMapper(Student.class));
		
		return student;
	}

}
