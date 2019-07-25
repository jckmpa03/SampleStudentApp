package com.jck.mpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jck.mpa.dao.StudentRepository;
import com.jck.mpa.model.Student;

@Service
public class StudentServiceImpl {

	
	@Autowired
	private StudentRepository studentRepository;
	
	
	/**
	 * Retrieves all students
	 * @return List<Student>
	 */
	public List<Student> getAllStudents() {
		return studentRepository.getAllStudents();
	}
	
	/**
	 * Inserts new student record
	 * @param student
	 * @return int
	 */
	public int insertStudent(Student student) {
		return studentRepository.insertStudent(student);
	}
	
	/**
	 * Updates student record
	 * @param student
	 * @return int
	 */
	public int updateStudent(Student student) {
		return studentRepository.updateStudent(student);
	}
	
	/**
	 * Retrieves student 
	 * @param id
	 * @return Student
	 */
	public Student getStudent(int id) {
		return studentRepository.getStudent(id);

	}
}